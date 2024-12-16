package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.User;

public class UserDao {
	public List<User> getAllUser(){
		List<User> list = new ArrayList<>();
		String sql = "select * from users";
		Connection connection = DBContext.getConnection();
		try (PreparedStatement st = connection.prepareStatement(sql)){
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("id"),rs.getString("username") , rs.getString("password") , rs.getTimestamp("created_at"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static User getUserByName(String name) {
		String sql = "select * from users where username like ?";
		Connection connection = DBContext.getConnection();
		try (PreparedStatement st = connection.prepareStatement(sql)){
			st.setString(1, "%" + name + "%");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
	            User user = new User(rs.getInt("id"), 
	                                 rs.getString("username"), 
	                                 rs.getString("password"), 
	                                 rs.getTimestamp("created_at"));
	            return user;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean InsertUser(User user)
	{

		Connection conn = DBContext.getConnection();

        	 String sql = "INSERT INTO USERS (username, password) VALUES (?, ?)";
     	    try (PreparedStatement st = conn.prepareStatement(sql)) {
     	        // Gán giá trị cho các tham số
     	        st.setString(1, user.getUsername());
     	        st.setString(2, user.getPassword());
     	        int res = st.executeUpdate();
     	        if(res > 0)
     	        {
     	        	return true;
     	        }
     	    } catch (Exception e) {
     	        e.printStackTrace();
     	    }
     	    return false;
	}
    //Phương thức kiểm tra Username có tồn tại chưa
	public boolean isUsernameExists(String username) {

		Connection conn = DBContext.getConnection();
		
	    String sql = "SELECT COUNT(*) FROM USERS WHERE username = ?";
	    try (PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setString(1, username);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1); 
	            if(count > 0)
	            {
	            	return true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false; 
	}
	public static void main(String[] args) {
		UserDao a = new UserDao(); 
		//System.out.println(a.getAllUser());
		System.out.println(UserDao.getUserByName("khoa"));
	}
}

