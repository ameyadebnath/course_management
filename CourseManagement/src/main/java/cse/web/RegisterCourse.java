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
 * Servlet implementation class RegisterCourse
 */
@WebServlet("/RegisterCourse")
public class RegisterCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checking if the correct person type is signed in
		//if yes showing webpage to createRegistration
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		int studentid = (int)session.getAttribute("id");//id may be in string format
		if(type.compareTo("1")==0) {
			
			//sending all registered courses data to front-end registerCourse.jsp
			List < Course > listRegisteredCourses = DBControlModule.getRegisteredCourses(studentid);
	        request.setAttribute("listRegisteredCourses", listRegisteredCourses);
	        
	       //sending all non registered courses data to front-end registerCourse.jsp
			List < Course > listNonRegisteredCourses = DBControlModule.getNonRegisteredCourses(studentid);
	        request.setAttribute("listNonRegisteredCourses", listNonRegisteredCourses);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("registerCourse.jsp");
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
		// checking if the correct person type student is signed in
		//if yes register for the course
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		if(type.compareTo("1")==0) {
			//fetching the data and creating course
			String code = request.getParameter("code");
			int studentid = (int)session.getAttribute("id");
			DBControlModule.createCourseRegistration(code, studentid);
			
			//redirecting to the current page with updated data
			
			//sending all registered courses data to front-end registerCourse.jsp
			List < Course > listRegisteredCourses = DBControlModule.getRegisteredCourses(studentid);
	        request.setAttribute("listRegisteredCourses", listRegisteredCourses);
	        
	       //sending all non registered courses data to front-end registerCourse.jsp
			List < Course > listNonRegisteredCourses = DBControlModule.getNonRegisteredCourses(studentid);
	        request.setAttribute("listNonRegisteredCourses", listNonRegisteredCourses);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("registerCourse.jsp");
	        dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			      dispatcher.forward(request, response);
		}
	}

}
