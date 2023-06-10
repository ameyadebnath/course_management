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
 * Servlet implementation class AssignCourseTeacher
 */
@WebServlet("/AssignCourseTeacher")
public class AssignCourseTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignCourseTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checking if the correct person type is signed in
		//if yes showing webpage to assign course teacher
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		if(type.compareTo("3")==0) {
			
			//sending all course list to front-end
			List < Course > listCourses = DBControlModule.getAllCourses();
			request.setAttribute("listCourses", listCourses);
			
			//sending all teacher list to front-end
			List < Teacher > listTeachers = DBControlModule.getAllTeachers();
			request.setAttribute("listTeachers", listTeachers);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("assignCourseTeacher.jsp");
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
		//if yes assign the new teacher to the course
		//if no taking the user to login screen
		HttpSession session = request.getSession();
		String type = (String)session.getAttribute("type");
		if(type.compareTo("3")==0) {
			//fetching the data from front-end AssignCourseTeacher.jsp and updating teacher assignment to course
			String code = request.getParameter("code");//course code
			int teacherid = Integer.parseInt(request.getParameter("teacherid"));
			DBControlModule.updateCourseTeacherid(code, teacherid);
			
			//sending all course list to front-end
			List < Course > listCourses = DBControlModule.getAllCourses();
			request.setAttribute("listCourses", listCourses);
			
			//sending all teacher list to front-end
			List < Teacher > listTeachers = DBControlModule.getAllTeachers();
			request.setAttribute("listTeachers", listTeachers);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("assignCourseTeacher.jsp");
			      dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			      dispatcher.forward(request, response);
		}
	}

}
