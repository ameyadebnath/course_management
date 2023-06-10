<!-- Teacher page -->

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

    <title>Teacher Page</title>
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
	  

	</nav>
	
	
	
	<!-- AMEYA -->
	
	<div style = "height:1px; background-color: rgba(0,0,0,0)">
	</div>
	
	<div style = "height:30px; background-color: rgba(0,0,240,.1)">
	</div>
		
<!-- to give bg-color -->

	 <div class="h-30 d-inline-block" style=" width: 100%; background-color: rgba(0,0,255,.1)">
	 	
	 	 
	 	 	<div style = "height:30px; background-color: rgba(0,0,0,0)"> </div>
	 <div class="container">
 	 <div class="row">
   	 <div class="col-md-6 text-center"style="border-right: 1px solid black;height: 850px;">
      <h2>Course List </h2>
      
      
<!-- Course list -->
	<table class="table table-bordered table-success table-striped" >
                        <thead>
                            <tr>
                                <th>Assigned Course Code</th>
                                <th>Assigned Course Title</th>
                                <th></th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="course" items="${listAssignedCourses}">

                                <tr>
                                    <td>
                                        <c:out value="${course.code}" />
                                    </td>
                                    <td>
                                        <c:out value="${course.title}" />
                                    </td>
                                    <td>
										<form method="post" action="/CourseManagement/RegisteredStudents">
                        					<input type="hidden" name="code" id="code" value="${course.code}" />
                        					<input type="hidden" name="title" id="title" value="${course.title}" />
                        					<button type="submit" class="btn btn-outline-secondary">See Student List</button>
                    					</form>
                                    </td>
                                    
                                    
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
       
      
   		</div>
    	<div class="col-md-6 text-center">
      <h2>Registered Student List</h2>
      <!-- to increase space -->
      	<div style = "height:30px; background-color: rgba(0,0,0,0)"> </div>
      
      <div class="container">
     <div class="row">
    <div class="col-md-6">
       <h4>Course code</h4>
       
       <!-- to insert course code -->
       <table class="table table-bordered " style="border-color: black; border-width: 2px;" >
                       
                        <tbody>

                                <tr>
                                    <td>
                                        <c:out value="${scode}" />
                                    </td>
                                   
                                </tr>
                        </tbody>

                    </table>
       
    </div>
    <div class="col-md-6">
       <h4>Course Title</h4>
       
              <!-- to insert course Title -->
       <table class="table table-bordered " style="border-color: black; border-width: 2px;" >
                       
                        <tbody>

                                <tr>
                                    <td>
                                        <c:out value="${stitle}" />
                                    </td>
                                   
                                </tr>
                        </tbody>

         </table>
       
    </div>
  </div>
  
  <!-- Student list -->
	<table class="table table-bordered table-success table-striped" >
                        <thead>
                            <tr>
                                <th>Student's Registration Number</th>
                                <th>Student's Name</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${listRegisteredStudents}">

                                <tr>
                                    <td>
                                        <c:out value="${student.reg}" />
                                    </td>
                                    <td>
                                        <c:out value="${student.name}" />
                                    </td>
                                    
                                    
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
	 	 
  
</div>
      <!-- end -->
      
   	 </div>
  	</div>
	</div>
                
     </div>
			 

 
		
		
	
  </body>
</html>