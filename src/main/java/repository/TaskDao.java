package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import context.DBContext;
import model.Task;

public class TaskDao {

    public static boolean InsertTask(int userId, String originalText, String sourceLanguage, String targetLanguage, String status, String result) {
        Connection connection = DBContext.getConnection();
        String sql = "INSERT INTO tasks (user_id, input_data, source_language, target_language, status, result) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            st.setString(2, originalText);
            st.setString(3, sourceLanguage);
            st.setString(4, targetLanguage);
            st.setString(5, status != null ? status : "PENDING");
            st.setString(6, result);
            int rs = st.executeUpdate();
            System.out.println("Insert into task successful");
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Task selectByIdUser(int userId) {
        Connection connection = DBContext.getConnection();
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND status = 'COMPLETE'";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String originalText = rs.getString("original_text");
                String sourceLanguage = rs.getString("source_language");
                String targetLanguage = rs.getString("target_language");
                String status = rs.getString("status");
                String result = rs.getString("result");
                return new Task(userId, originalText, sourceLanguage, targetLanguage, status, result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
