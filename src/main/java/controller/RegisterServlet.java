package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import repository.UserDao;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	String confirm_password = req.getParameter("confirm_password");
    	String message = "";
    	//Nếu mật khẩu và xác nhận mật khẩu không trùng khớp
    	if(!password.equals(confirm_password))
    	{
    		message = "Mật khẩu xác nhận không trùng khớp";
    	}
    	else
    	{
    		UserDao dao = new UserDao();
    		//Nếuusername đã tồn tại
    		if(dao.isUsernameExists(username))
    		{
    			message = "Tên đăng nhập đã tồn tại";
    		}
    		else
    		{
    			User user = new User( username,password);
    			if(dao.InsertUser(user))
    			{
    				message = "Đã đăng kí thành công, vui lòng đăng nhập.";
    			}
    			else
    			{
    				message = "Đăng kí không thành công";
    			}
    		}
    	}
    	req.setAttribute("message",message );
    	RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
		dispatcher.forward(req, resp);
    }
}
