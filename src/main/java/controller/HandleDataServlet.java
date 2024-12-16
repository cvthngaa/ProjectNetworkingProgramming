package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Task;
import model.User;
import repository.TaskDao;
import repository.UserDao;

@WebServlet("/upload")
@MultipartConfig
public class HandleDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private static final List<Task> completedTasks = new ArrayList<>();

    static {
        Thread workerThread = new Thread(() -> {
            while (true) {
                try {
                    Task task = taskQueue.take();
                    processTask(task); 
                    synchronized (completedTasks) {
                        completedTasks.add(task); 
                        completedTasks.notifyAll();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        workerThread.setDaemon(true);
        workerThread.start();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("user");

    if (username == null) {
        response.getWriter().println("Missing parameters.");
        return;
    }

    User user = UserDao.getUserByName(username);
    if (user == null) {
        response.getWriter().println("User not found.");
        return;
    }

    List<Task> tasks = new ArrayList<>();
    int fileCount = Integer.parseInt(request.getParameter("fileCount"));
    System.out.println("fileCount: " + fileCount);

    for (Part part : request.getParts()) {
        if (part.getName().startsWith("file-")) { 
            String sourceLanguage = request.getParameter("sourceLanguage-" + part.getName().substring(5)); 
            String targetLanguage = request.getParameter("targetLanguage-" + part.getName().substring(5)); 

            if (sourceLanguage != null && targetLanguage != null) {
                File tempFile = File.createTempFile("uploaded-", ".txt");
                try (InputStream fileContent = part.getInputStream();
                     FileOutputStream fos = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }

                String text = new String(Files.readAllBytes(tempFile.toPath()), StandardCharsets.UTF_8);
                Task task = new Task(user.getId(), text, sourceLanguage, targetLanguage, "", "PENDING");
                tasks.add(task);
                taskQueue.offer(task); 
            }
        }
    }

    synchronized (completedTasks) {
        while (completedTasks.size() < tasks.size()) {
            try {
                completedTasks.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    request.getSession().setAttribute("tasks", new ArrayList<>(completedTasks));
    completedTasks.clear();

    response.sendRedirect("result.jsp");
}


    private static void processTask(Task task) {
        try {
            String translatedText = Translator.translate(task.getSourceLanguage(), task.getTargetLanguage(), task.getOriginalText());
            task.setResult(translatedText);
            task.setStatus("COMPLETED");

            TaskDao.InsertTask(task.getUserId(), task.getOriginalText(), task.getSourceLanguage(), task.getTargetLanguage(), task.getStatus(), task.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            task.setStatus("FAILED");
            TaskDao.InsertTask(task.getUserId(), task.getOriginalText(), task.getSourceLanguage(), task.getTargetLanguage(), task.getStatus(), "");
        }
    }
}
