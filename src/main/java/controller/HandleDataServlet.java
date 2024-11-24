package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleDataServlet
 */
@WebServlet("/handleData")
public class HandleDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleDataServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			System.out.println("da duoc goi tai doPÓT");    
			String user = (String) request.getParameter("user");

	        String functionName = request.getParameter("function");

	        String data = request.getParameter("data");
	        
	        
	        
	        request.setAttribute("user", user);
	        request.setAttribute("functionName", functionName);
	        request.setAttribute("data", data);
	        
	        // request.getRequestDispatcher("/result.jsp") .forward(request, response);
	       
	}

}
