package com.waviz.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waviz.example.dao.Main;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private int counter;
	@Override
	public void init(){
		counter = 3;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		
			Main main = new Main();
			int passMatched = 0;
		    passMatched = main.getValues(email, pass);
	
			try{
			if(passMatched == 1){
				System.out.println("login success");
				request.setAttribute("msg", email);
				RequestDispatcher rd = request.getRequestDispatcher("welcome");
				rd.forward(request, response);
			}
			else{
				System.out.println("number of attempts left "+(--counter));
				if(counter < 1){
					request.setAttribute("captcha", LoginServlet.generateCaptcha());
					RequestDispatcher rd = request.getRequestDispatcher("loginerror.jsp");
					rd.forward(request, response);
				}
				else{
				request.setAttribute("counter", counter);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				}
				
		}
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("some error occured");
			}
				
			
		
	}

	private static String generateCaptcha() {
		
		Random random = new Random();
		int length = 5 ;

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int baseCharNumber = Math.abs(random.nextInt()) % 62;
			int charNumber = 0;
			if (baseCharNumber < 26) {
				charNumber = 65 + baseCharNumber;
			}
			else if (baseCharNumber < 52){
				charNumber = 97 + (baseCharNumber - 26);
			}
			else {
				charNumber = 48 + (baseCharNumber - 52);
			}
			buffer.append((char)charNumber);
		}

		return buffer.toString();
	}

	

}
