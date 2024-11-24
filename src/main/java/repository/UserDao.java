package repository;

import java.sql.Connection;
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
	public static void main(String[] args) {
		UserDao a = new UserDao(); 
		System.out.println(a.getAllUser());
	}
}

