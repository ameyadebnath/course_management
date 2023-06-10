package cse.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = DBControlModule.getUser(username, password);
			if(user!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("id",user.id);
				session.setAttribute("type",user.type);
				if(user.type.compareTo("3")==0) {//redirecting to create course page
					List < Course > listCourses = DBControlModule.getAllCourses();
			        request.setAttribute("listCourses", listCourses);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("createCourse.jsp");
			        dispatcher.forward(request, response);
				}else if(user.type.compareTo("1")==0) {//redirecting to student page
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
				}else if(user.type.compareTo("2")==0) {//redirecting to teacher page
					//fetching the data and creating course
					String code = request.getParameter("code");
					int teacherid = (int)session.getAttribute("id");
					
					//redirecting to the current page with updated data
					
					//sending all assigned courses data to front-end registerCourse.jsp
					List < Course > listAssignedCourses = DBControlModule.getTeacherAssignedCourses(teacherid);
				    request.setAttribute("listAssignedCourses", listAssignedCourses);
					      
				    //sending all assigned courses data to front-end registerCourse.jsp
					List < Student > listRegisteredStudents = DBControlModule.getRegisteredStudents(code);
				    request.setAttribute("listRegisteredStudents", listRegisteredStudents);
					      
				    RequestDispatcher dispatcher = request.getRequestDispatcher("registeredStudents.jsp");
					dispatcher.forward(request, response);
				}
			}
			else out.println("Nope");
		} finally {
			out.close();
		}
	}

}
