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

    <title>Create Course</title>
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
	
	<div style = "height:1px; background-color: rgba(0,0,0,0)">
	</div>
	
	<div style = "height:30px; background-color: rgba(0,0,0,0)">
	</div>
		
<!-- to give bg-color -->

	 <div class="h-30 d-inline-block" style=" width: 100%; background-color: rgba(0,0,0,0)">
	 	 
	<div style ="padding: 25px 50px 25px 50px;height: 300px; overflow: auto;">
<!-- course list -->
	<table class="table table-striped table-blue table-bordered  shadow ">
                        <thead>
                            <tr>
                                <th>Course Code</th>
                                <th>Course Title</th>
                                <th>Course Credit</th>
                                <th>Assigned Teacher ID</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!--   showig course lists {  -->
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
                                    
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                    
                     </div>
			 <div style = "height:1px; background-color: rgba(0,0,0,0)">
			</div>
			
			<div style = "height:30px; background-color: rgba(0,0,0,0)">
			</div>
			
			<div class="h-30 d-inline-block" style=" width: 100%; background-color: rgba(0,0,0,0)">
			 	<div class="container">
			 	
			<!-- Showing Alert -->
    		<div id="alert-container"></div>
	 	 
<!-- form  -->

	 	
 	<div class="container">
 	<h2 >  Create course:</h2>
 	<div class="card">
  	<div class="card-body">
   	<form action="/CourseManagement/CreateCourse" method="post" onSubmit="return validateForm()">


    <div class="form-group row">
     <label for="firstName" class="col-sm-3 col-form-label"> Course Title</label>
     <div class="col-sm-7">
     <input type="text" class="form-control" name="title" id="title"
       placeholder="Enter Course Title">
    </div>
    </div>

    <div class="form-group row">
     <label for="lastName" class="col-sm-3 col-form-label">Course Code</label>
     <div class="col-sm-7">
      <input type="text" class="form-control" name="code" id="code"
       placeholder="Enter Course Code">
     </div>
    </div>

    <div class="form-group row">
     <label for="lastName" class="col-sm-3 col-form-label">Course Credit</label>
     <div class="col-sm-7">
      <input type="number" class="form-control" id="credit" name="credit"
       placeholder="Enter Course Credit">
     </div>
    </div>
    
    

<!-- submit button -->

	<div class="text-center ">
    <button type="submit" class="btn btn-primary">Submit</button>
    </div>
   </form>
  </div>
 </div>
</div>
</div>

			 <!-- second portion -->
			 
		
	
  </body>
</html>

<!-- Scripts to show alerts when input fields are empty -->
<script>
  //validating form so that non od the fields remain empty
  function validateForm() {
    var codeInput = document.getElementsByName("code")[0];
    var passInput = document.getElementsByName("title")[0];
    var creditInput = document.getElementsByName("credit")[0];
    
    if (codeInput.value == "") {
    	codeInput.style.borderColor="red";
    	var alertContainer = document.getElementById("alert-container");
        var alertHTML = `
	        <div class="alert alert-warning alert-dismissible d-flex align-items-center fade show">
	          	<i class="bi-exclamation-triangle-fill"></i>
	            <strong class="mx-2">Warning!</strong> Code cannot be empty.
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
	            <strong class="mx-2">Warning!</strong> Title cannot be empty.
	            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	        </div>
        `;
        alertContainer.innerHTML = alertHTML;
      return false;
    }
    if (creditInput.value == "") {
    	creditInput.style.borderColor="red";
    	var alertContainer = document.getElementById("alert-container");
        var alertHTML = `
	        <div class="alert alert-warning alert-dismissible d-flex align-items-center fade show">
	          	<i class="bi-exclamation-triangle-fill"></i>
	            <strong class="mx-2">Warning!</strong> Credit cannot be empty.
	            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	        </div>
        `;
        alertContainer.innerHTML = alertHTML;
      return false;
    }
    return true;
  }
</script>