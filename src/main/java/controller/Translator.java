package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Translator {
	
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    
    public static void main(String[] args) {
    	 String inputFilePath = "input.txt";
        
         String fromLang = "en";
         String toLang = "vi";
         try {
             String text = Translator.translate(fromLang, toLang, "On weekends, my family and I enjoy spending time together. Saturday mornings usually start with a big breakfast at home. My mom makes pancakes or waffles, and we all sit around the table chatting about our plans for the day. Sometimes we decide to go hiking in the nearby forest. It’s peaceful and beautiful there, with tall trees and birds chirping.\r\n"
             		+ "\r\n"
             		+ "In the afternoons, if the weather is nice, we might have a picnic in the park. We bring sandwiches, fruit, and lemonade. We play games like Frisbee or soccer. It’s a great way to relax and enjoy each other’s company.\r\n"
             		+ "\r\n"
             		+ "On Sundays, we often visit my grandparents. They live in a cozy house by the lake. My grandma is an amazing cook, and she always prepares a delicious lunch for us. We help her set the table and then enjoy a hearty meal together. After lunch, we take a walk around the lake or sit on the porch chatting and watching the sunset.\r\n"
             		+ "\r\n"
             		+ "Evenings are for winding down. We might watch a movie together or play board games. It’s these simple moments that make our weekends special. I cherish the time I get to spend with my family—it’s when we create lasting memories and strengthen our bond.");
             System.out.println(text);
             Translator.WriteToFile(text);
         } catch (IOException e) {
             System.err.println("Lỗi khi đọc/ghi file: " + e.getMessage());
             e.printStackTrace();
         } catch (Exception e) {
             System.err.println("Lỗi khi dịch: " + e.getMessage());
             e.printStackTrace();
         }
    }
    
    public static void WriteToFile(String translatedContent ) throws IOException {
    	 String currentDir = System.getProperty("user.dir");
    	 System.out.println(currentDir);
    	 String outputFilePath = currentDir + "/output_translated.txt";
         Path outputPath = Paths.get(outputFilePath);
         Files.writeString(outputPath, translatedContent);
         System.out.println("Dịch thành công\n File đã được lưu tại: " + outputPath.toAbsolutePath());
    }

    public static String translate(String fromLang, String toLang, String text) throws Exception {
    	HashMap<String, String> payload = new HashMap<String, String>();
    	ObjectMapper mapper = new ObjectMapper();
        payload.put("fromLang", fromLang);
        payload.put("toLang", toLang);
        payload.put("text", text);
        String jsonPayload = mapper.writeValueAsString(payload);

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonPayload.getBytes());
        }

        int statusCode = conn.getResponseCode();
        System.out.println("HTTP Status Code: " + statusCode);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader((statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            if (statusCode == 200) {
                return response.toString();
            } else {
                throw new IOException("Failed to translate: " + response.toString());
            }
        } finally {
            conn.disconnect();
        }
    }
}