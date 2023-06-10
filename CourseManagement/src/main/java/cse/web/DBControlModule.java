package cse.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBControlModule {
	/*Class which handles connection to database and interact with the database with database*/
	
	//variables relating to database setup
	private static String dbName = "courseManagement";
	private static String jdbcURL = "jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";
    
    //private static final String SELECT_USER_BY_ID = "select id,username,password,type from users where id =?";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD = "select id,username,password,type from users where username =? and password = ?";
    private static final String SELECT_COURSE_BY_CODE = "select * from courses where code=?";
    private static final String SELECT_ALL_COURSES = "select * from courses";
    private static final String UPDATE_COURSE_TEACHER = "update courses set teacherid=? where code=?";
    private static final String CREATE_COURSE = "insert into courses (code,title,teacherid,credit) values (?,?,?,?)";
    private static final String SELECT_ALL_TEACHERS = "select * from teachers";
    private static final String CREATE_REGISTRATION = "insert into registrations (coursecode,studentid) values (?,?)";
    private static final String SELECT_REGISTERED_COURSES = "select * from users where studentid =?";
    private static final String NON_REGISTERED_COURSES_STUDENT = "SELECT * FROM courses WHERE code NOT IN ("+
    			"SELECT coursecode FROM registrations WHERE studentid = ?"+
    		")";
    private static final String REGISTERED_COURSES_STUDENT = "SELECT * FROM courses WHERE code IN ("+
			"SELECT coursecode FROM registrations WHERE studentid = ?"+
		")";
    private static final String TEACHER_ASSIGNED_COURSES = "select * from courses where teacherid =?";
    private static final String STUDENTS_REGISTERED = "SELECT * FROM students WHERE id IN ("+
    			"SELECT studentid FROM registrations WHERE coursecode = ?"+
    		")";

	
	public static Connection getDbConnection() {
		//create an new instance of connection
		Connection dbConnection=null;
		try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return dbConnection;
	}
	
	public static User getUser(String username, String password) {
        User user = null;
        //Establishing a Connection
        try (Connection connection = getDbConnection();
            //Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            //Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            //Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                user = new User(id, username, password, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
	
	public static Course getCourse(String code) {
        Course course = null;
        //Establishing a Connection
        try (Connection connection = getDbConnection();
            //Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_CODE);) {
            preparedStatement.setString(1, code);
            System.out.println(preparedStatement);
            //Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            //Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("title");
                int teacherid = rs.getInt("teacherid");
                float credit = rs.getFloat("credit");
                course = new Course(code, title, teacherid, credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
	
	public static List < Course > getAllCourses() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String code = rs.getString("code");
                String title = rs.getString("title");
                int teacherid = rs.getInt("teacherid");
                float credit = rs.getFloat("credit");
                courses.add(new Course(code, title, teacherid,credit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
	
	public static boolean updateCourseTeacherid(String code, int teacherid) {
        boolean rowUpdated=false;
        try (Connection connection = getDbConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE_TEACHER);) {
            statement.setInt(1, teacherid);
            statement.setString(2, code);

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return rowUpdated;
    }
	
	public static boolean insertCourse(Course course) {
		boolean dbUpdated=false;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COURSE)) {
            preparedStatement.setString(1, course.getCode());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setInt(3, course.getTeacherid());
            preparedStatement.setFloat(4, course.getCredit());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            dbUpdated=true;
        }catch (SQLException e) {
        	e.printStackTrace();
        }
        return dbUpdated;
    }
	
	public static List < Teacher > getAllTeachers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Teacher > teachers = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEACHERS);) {
        	System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                teachers.add(new Teacher(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
	
	public static List < Course > getRegisteredCourses(int studentid) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTERED_COURSES_STUDENT);) {
        	preparedStatement.setInt(1,studentid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String code = rs.getString("code");
                String title = rs.getString("title");
                int teacherid = rs.getInt("teacherid");
                float credit = rs.getFloat("credit");
                courses.add(new Course(code, title, teacherid, credit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
	
	public static List < Course > getNonRegisteredCourses(int studentid) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(NON_REGISTERED_COURSES_STUDENT);) {
        	preparedStatement.setInt(1,studentid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String code = rs.getString("code");
                String title = rs.getString("title");
                int teacherid = rs.getInt("teacherid");
                float credit = rs.getFloat("credit");
                courses.add(new Course(code, title, teacherid, credit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
	
	public static boolean createCourseRegistration(String coursecode, int studentid) {
		boolean dbUpdated=false;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_REGISTRATION)) {
            preparedStatement.setString(1, coursecode);
            preparedStatement.setInt(2, studentid);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            dbUpdated=true;
        }catch (SQLException e) {
        	e.printStackTrace();
        }
        return dbUpdated;
    }
	
	public static List < Course > getTeacherAssignedCourses(int teacherid) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(TEACHER_ASSIGNED_COURSES);) {
        	preparedStatement.setInt(1,teacherid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String code = rs.getString("code");
                String title = rs.getString("title");
                float credit = rs.getFloat("credit");
                courses.add(new Course(code, title, teacherid, credit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
	
	public static List < Student > getRegisteredStudents(String coursecode) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Student > students = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(STUDENTS_REGISTERED);) {
        	System.out.println(coursecode);
        	preparedStatement.setString(1,coursecode);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String reg = rs.getString("reg");
                int id = rs.getInt("id");
                students.add(new Student(id, name, reg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
	
	public static String getFirstAssignedCourseCode(int teacherid) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        System.out.println("called getFirstAssignedCourseCode");
        // Step 1: Establishing a Connection
        String code="";
        try (Connection connection = getDbConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(TEACHER_ASSIGNED_COURSES);) {
        	preparedStatement.setInt(1,teacherid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                code = rs.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("course code resulted: "+code);
        return code;
    }

}
