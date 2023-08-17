<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
System.out.println("welcome page ------------------------------------->");

String err=(String)(request.getAttribute("error"));
System.out.println(request.getAttribute("error"));
%>

<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
    <style>

       body {
            margin: 0;
            padding: 0;
            background-color: #17a2b8;
            height: 100vh;
            color: #fff;
            align-items: center;
   			justify-content: center;
   			margin-left:30px	
        }
        #registration .container #registration-row #registration-column #registration-box {
            margin-top: 80px;
            max-width: 450px;
            height: 600px;
            border: 1px solid #9C9C9C;
            background-color: #EAEAEA;
            border-radius: 10px;
            margin-left:30px
        }
        #registration .container #registration-row #registration-column #registration-box #registration-form {
            padding: 20px;
        }
    </style>
</head>
<body>

	<%if(err!=null && !"".equalsIgnoreCase(err)){ %>
   <h3> <%=err %></h3>
    <%} %>
    
    
    <div id="registration">
        <!-- <h3 class="text-center text-white pt-5">Registration form</h3>-->
        <div class="container">
            <div id="registration-row" class="row justify-content-center align-items-center">
                <div id="registration-column" class="col-md-6">
                    <div id="registration-box" class="col-md-12">
                        <form id="registration-form" class="form" action="RegisterServlet" method="post">
                            <h3 class="text-center text-info">Registration</h3>
                            <div class="form-group">
                                <label for="name" class="text-info">Name:</label><br>
                                <input type="text" name="username" id="name" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="email" class="text-info">Email:</label><br>
                                <input type="email" name="email" id="email" class="form-control"required>
                            </div>
                            <div class="form-group">
                                <label for="mobileNumber" class="text-info">Mobile Number:</label><br>
                                <input type="text" name="mobile_no" id="mobileNumber" class="form-control"required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control"required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword" class="text-info">Confirm Password:</label><br>
                                <input type="password" name="cPassword" id="confirmPassword" class="form-control"required>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Register">
                            </div>
                            <div id="login-link" class="text-right">
                                <a href="index.jsp" class="text-info">Back to Login</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>


    