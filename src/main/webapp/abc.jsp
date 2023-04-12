<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="PatientController" method="post">
							<div class="form-group">
								<label>Your Firstname</label> 
								<input type="text" class="form-control" name="FirstName" placeholder=""	required="">
							</div>
							<div class="form-group">
								<label>Your Surname</label> 
								<input type="text" class="form-control" name="LastName" placeholder="" required="">
							</div>
							<div class="form-group">
								<label>Your gender</label> 
								<input type="text" class="form-control" name="Gender" placeholder="" required="">
							</div>
							<div class="form-group">
								<label>Your Address</label> 
								<input type="text" class="form-control" name="Address" placeholder="" required="">
							</div>
							<div class="form-group">
								<label>Your Mobile</label> 
								<input type="tel" class="form-control" name="Mobile" placeholder="" required="">
							</div>
							<div class="form-group">
								<label>Email</label> 
								<input type="email" class="form-control" name="Email" placeholder="" required="">
							</div>
							<div class="form-group">
								<label class="mb-2">Password</label>
								<input type="password" class="form-control" name="Password" id="password1" placeholder="" required="">
							</div>
							<button type="submit" class="btn btn-primary submit mb-4" name="action" value="Register">Register</button>
							<p class="text-center pb-2">
								<a href="#" class="text-white">By clicking Register, I agree to your terms</a>
							</p>
						</form>
</body>
</html>