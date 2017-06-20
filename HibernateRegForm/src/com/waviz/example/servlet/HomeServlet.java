package com.waviz.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.waviz.example.dao.Main;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		String add = request.getParameter("add");
		String hobbies = "hobbies-->";
		String hobby[] = request.getParameterValues("hobby");
		for(int i = 0; i<hobby.length; i++){
			hobbies += hobby+" ";  
		}
		String gender = request.getParameter("gender");
	
			Main main = new Main();
	//		int rowUpdated = main.setValues(name, mobile, mail, repass, add, hobbies, gender);
			
			int rowUpdated = main.getValues(mail, pass);
			
			if(rowUpdated>0){
				HttpSession session = request.getSession();
				session.setAttribute("user", name);
				response.sendRedirect("welcome");
			}
		
	
	}
}
	
	

	
	
