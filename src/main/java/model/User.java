package model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	 private int id;
	 private String username;
	 private String password;
	 private Date createdAt;

	public User(int id, String username, String password, Date timestamp) {
	    this.id = id;
	    this.username = username;
	    this.password = password;
	    this.createdAt = timestamp;
	}
	public User( String username, String password) {
	    this.username = username;
	    this.password = password;
	}
	
	public int getId() {
	    return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	
	public String getUsername() {
	    return username;
	}
	
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public Date getCreatedAt() {
	    return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
	    this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createdAt=" + createdAt
				+ "]";
	}
	
}
