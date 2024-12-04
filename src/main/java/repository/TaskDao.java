package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import context.DBContext;

public class TaskDao {
	public static boolean InsertTask(int userId, String inputData, String status, String result) {
		Connection connection = DBContext.getConnection();
		String sql = "insert into tasks (user_id, input_data, status, result) values( ? , ? , ? , ?)";
		try (PreparedStatement st = connection.prepareStatement(sql)){
			  	st.setInt(1, userId);       
	            st.setString(2, inputData); 
	            if (status == null) {
	                st.setString(3, "PENDING");
	            } else {
	                st.setString(3, status);
	            }
	            st.setString(4, result);    
	            int rs = st.executeUpdate();
	            System.out.println("Inser into task successful");
			return rs > 0 ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
