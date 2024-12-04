package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import repository.TaskDao;
import repository.UserDao;

/**
 * Servlet implementation class HandleDataServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class HandleDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleDataServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String user = (String) request.getParameter("user");
        String functionName = request.getParameter("function");
        String data = request.getParameter("data");
        String sourceLanguage = request.getParameter("sourceLanguage");
        String targetLanguage = request.getParameter("targetLanguage");

        System.out.println("User: " + user);
        System.out.println("Function: " + functionName);
        System.out.println("Data: " + data);
        System.out.println("Source Language: " + sourceLanguage);
        System.out.println("Target Language: " + targetLanguage);

        if (sourceLanguage == null || targetLanguage == null) {
            response.getWriter().println("Source or Target language is missing.");
            return;
        }

        Part filePart = request.getPart("file");
        File tempFile = File.createTempFile("uploaded-", ".txt");
        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        String text = new String(Files.readAllBytes(tempFile.toPath()), StandardCharsets.UTF_8);
        System.out.println("File content: " + text);

        String translatedText = "";
        try {
            translatedText = Translator.translate(sourceLanguage, targetLanguage, text);
            System.out.println("File Translate : " + translatedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
        	 User dataUser = UserDao.getUserByName(user);
             TaskDao.InsertTask(dataUser.getId(), text, "COMPLETED", translatedText);
        }
        System.out.println("User empty!");
        // Translator.WriteToFile(translatedText);
        
        System.out.println("COMPLETED");
    }
}
