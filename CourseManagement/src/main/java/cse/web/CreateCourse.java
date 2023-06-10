package cse.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateCourse
 */
//@WebServlet("/CreateCourse")
public class CreateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checking if the correct person type is signed in
		//if yes showing webpage to create course
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		if(type.compareTo("3")==0) {
			List < Course > listCourses = DBControlModule.getAllCourses();
			
	        request.setAttribute("listCourses", listCourses);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("createCourse.jsp");
	        dispatcher.forward(request, response);
	        
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
	        dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checking if the correct person type is signed in
		//if yes create the course
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		if(type.compareTo("3")==0) {
			//fetching the data and creating course
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			int teacherid = -1;
			float credit = Float.parseFloat(request.getParameter("credit"));
			DBControlModule.insertCourse(new Course(code, title, teacherid,credit));
			
			//redirecting to the current page with updated data
			List < Course > listCourses = DBControlModule.getAllCourses();
	        request.setAttribute("listCourses", listCourses);
			RequestDispatcher dispatcher = request.getRequestDispatcher("createCourse.jsp");
	        dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
	        dispatcher.forward(request, response);
		}
	}

}
