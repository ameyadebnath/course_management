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
 * Servlet implementation class RegisteredStudents
 */
@WebServlet("/RegisteredStudents")
public class RegisteredStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteredStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checking if the correct person type is signed in
		//if yes showing webpage to registeredStudents.jsp
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		int teacherid = (int)session.getAttribute("id");//id may be in string format
		if(type.compareTo("2")==0) {
			
			//sending all assigned courses data to front-end registerCourse.jsp
			List < Course > listAssignedCourses = DBControlModule.getTeacherAssignedCourses(teacherid);
		    request.setAttribute("listAssignedCourses", listAssignedCourses);
		    
		    //By default gives the data of first course registered students to front end
//		    	String firstCourseCode = DBControlModule.getFirstAssignedCourseCode(teacherid);
//	            //sending all assigned courses data to front-end registerCourse.jsp
//				List < Student > listRegisteredStudents = DBControlModule.getRegisteredStudents(firstCourseCode);
//			    request.setAttribute("listRegisteredStudents", listRegisteredStudents);
		    
		    
		    if (listAssignedCourses != null && !listAssignedCourses.isEmpty()) {
		        Course firstCourse = listAssignedCourses.get(1);
		        if (firstCourse != null) {
		            String firstCourseCode = firstCourse.getCode();
		            //sending all assigned courses data to front-end registerCourse.jsp
					List < Student > listRegisteredStudents = DBControlModule.getRegisteredStudents(firstCourseCode);
				    request.setAttribute("listRegisteredStudents", listRegisteredStudents);
		        }
		    }
		    
		    request.setAttribute("scode", "");
		    request.setAttribute("stitle", "");

				      
			RequestDispatcher dispatcher = request.getRequestDispatcher("registeredStudents.jsp");
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
		if(type.compareTo("2")==0) {
			//fetching the data and creating course
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			int teacherid = (int)session.getAttribute("id");
			
			//redirecting to the current page with updated data
			
			//sending all assigned courses data to front-end registerCourse.jsp
			List < Course > listAssignedCourses = DBControlModule.getTeacherAssignedCourses(teacherid);
		    request.setAttribute("listAssignedCourses", listAssignedCourses);
			      
		    //sending all assigned courses data to front-end registerCourse.jsp
			List < Student > listRegisteredStudents = DBControlModule.getRegisteredStudents(code);
		    request.setAttribute("listRegisteredStudents", listRegisteredStudents);
			
		    request.setAttribute("scode", code);
		    request.setAttribute("stitle", title);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("registeredStudents.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			      dispatcher.forward(request, response);
		}
	}

}
