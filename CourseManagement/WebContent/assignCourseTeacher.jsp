<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
  
	<!-- for table -->  
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Assign Course</title>
  </head>
  <body>
  
    <!-- Optional JavaScript for bootstrap-->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
    <!-- Bootstrap Navbar -->
    
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style=" height:100px; font-size:20px">
	  <a class="navbar-brand" href="#" style="font-size:28px">
	    		<!-- img from favpng -->
	    	      <img src="https://img.favpng.com/23/7/4/computer-icons-educational-technology-learning-training-course-png-favpng-j5t2UTpdMx23LZhscuTVqAJGb.jpg" alt="" width="50" height="40" class="d-inline-block align-text-top">
	    CourseManagement</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link active" aria-current="page" href="/CourseManagement/CreateCourse">Create and View Courses<span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item ">
	        <a class="nav-link active" href="/CourseManagement/AssignCourseTeacher">View Teacher List and Assign Course <span class="sr-only">(current)</span></a>
	      </li>
	      
	    </ul>
	    
	       <form class="d-flex" method="get" action="/CourseManagement/Logout">
	        <button class="btn btn-outline-secondary" type="submit">LogOut</button>
	      </form>
	    
	  </div>
	</nav>
	
	
	
	<!-- AMEYA -->
	
	
	
	<div style = "height:15px; background-color: rgba(0,0,0,0)">
	</div>
		
<!-- to give bg-color -->

	 <div class="h-35 d-inline-block" style=" width: 100%; background-color: rgba(0,0,0,0)">


<!-- to do padding -->
<div style ="padding: 25px 50px 10px 50px;height: 300px; overflow: auto;">
<h2 >Course List:</h2>
<!-- course list -->
	<table class="table table-striped shadow table-bordered">
                        <thead>
                            <tr>
                                <th>Course Code</th>
                                <th>Course Title</th>
                                <th>Course Credit</th>
                                <th>Assigned Teacher ID</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!--   showing course lists {  -->
                            <c:forEach var="user" items="${listCourses}">

                                <tr>
                                    <td>
                                        <c:out value="${user.code}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.title}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.credit}" />
                                    </td>
                                    <td>
    									<c:choose>
        									<c:when test="${user.teacherid == -1}">
            									No Teacher assigned
        									</c:when>
        									<c:otherwise>
            									<c:out value="${user.teacherid}" />
        									</c:otherwise>
   										 </c:choose>
									</td>
									
									<td>
    									<c:choose>
        									<c:when test="${user.teacherid == -1}">
            									
            									<button class="btn btn-outline-secondary" onclick="setCourseCode('${user.code}')">SELECT TEACHER</button>
        									</c:when>
        									<c:otherwise>
            									
            									
            									<button class="btn btn-outline-secondary" onclick="setCourseCode('${user.code}')"">SELECT TO CHANGE TEACHER</button>
        									</c:otherwise>
   										 </c:choose>
									</td>
                                    
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                    
                    
    </div>
	 	 
	<!-- to give some space --> 	
	<div style = "height:5px; background-color: rgba(0,0,0,0)">
	</div> 
	
	
<!-- Teacher list -->
<div style ="padding: 25px 50px 25px 50px;height: 300px; overflow: auto;">

<h2 >Teacher List:</h2>
	<table class="table table-striped  shadow table-bordered" >
                        <thead>
                            <tr>
                                <th>Teacher ID</th>
                                <th>Teacher's Name</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="teacher" items="${listTeachers}">

                                <tr>
                                    <td>
                                        <c:out value="${teacher.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${teacher.name}" />
                                    </td>
                                    
                                    <td>
                                   <button class="btn btn-outline-secondary" onclick="setTeacherId(${teacher.id})">SELECT</button>

                                    </td>
                                    
                                    
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                    
    </div>
	 	 
<!-- form  -->

	 	
 	




<!-- second portion -->
			 	 
			 </div>
			 
			
			<div style = "height:31px; background-color: rgba(0,0,0,0)">
			</div>
			  
			 <div class="h-35 d-inline-block" style="height: 400px; width: 100%; background-color: rgba(0,0,0,0)">
			 	<div class="container">
			 	
			 	<!-- Showing Alert -->
    		<div id="alert-container"></div>
			 	
 	<h2 >  Assign Course Teacher:</h2>
 	<div class="card">
  	<div class="card-body">
   	<form action="/CourseManagement//AssignCourseTeacher" method="post" onSubmit="return validateForm()">


    <div class="form-group row">
     <label for="lastName" class="col-sm-3 col-form-label">Course Code</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="code"
       placeholder="Enter Course Code">
     </div>
    </div>


    <div class="form-group row">
     <label for="lastName" class="col-sm-3 col-form-label"> Teacher ID Number</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="teacherid"
       placeholder="Enter Teacher ID Number">
     </div>
    </div>
<!-- submit button -->

	<div class="text-center ">
    <button type="submit" class="btn btn-primary">Done</button>
    </div>
   </form>
  </div>
 </div>
</div>
			 </div>

	
  </body>
</html>

<script>
  //validating form so that non od the fields remain empty
  function validateForm() {
    var userInput = document.getElementsByName("code")[0];
    var passInput = document.getElementsByName("teacherid")[0];
    if (userInput.value == "") {
    	userInput.style.borderColor="red";
    	var alertContainer = document.getElementById("alert-container");
        var alertHTML = `
	        <div class="alert alert-warning alert-dismissible d-flex align-items-center fade show">
	          	<i class="bi-exclamation-triangle-fill"></i>
	            <strong class="mx-2">Warning!</strong> Course code cannot be empty.
	            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	        </div>
        `;
        alertContainer.innerHTML = alertHTML;
      return false;
    }
    if (passInput.value == "") {
    	passInput.style.borderColor="red";
    	var alertContainer = document.getElementById("alert-container");
        var alertHTML = `
	        <div class="alert alert-warning alert-dismissible d-flex align-items-center fade show">
	          	<i class="bi-exclamation-triangle-fill"></i>
	            <strong class="mx-2">Warning!</strong> Teacher id cannot be empty.
	            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	        </div>
        `;
        alertContainer.innerHTML = alertHTML;
      return false;
    }
    return true;
  }
  //setting the input field of teacher's id on select
  function setTeacherId(teacherId) {
	 document.getElementsByName("teacherid")[0].value = teacherId;
  }
  //setting the input field of course code on select
  function setCourseCode(courseCode) {
	 document.getElementsByName("code")[0].value = courseCode;
  }
</script>