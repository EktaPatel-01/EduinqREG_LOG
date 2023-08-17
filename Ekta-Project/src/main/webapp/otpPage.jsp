<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<title>Enter OTP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRy3sfTb2Bz29uHxjMDTRHm7sVxo7DzBp/EV9Kfo"
	crossorigin="anonymous">
<style>
/* Add your custom styles here */
body {
	background-color: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}
s
.otp-container {
	max-width: 400px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 30px;
}

.otp-input {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	border: none;
	border-bottom: 2px solid #ccc;
	outline: none;
	width: 100%;
	margin-bottom: 20px;
	padding: 10px 0;
}

.otp-button {
	display: block;
	width: 100%;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 0;
	font-size: 16px;
	cursor: pointer;
}

.otp-button:hover {
	background-color: #0056b3;
}
</style>


</head>
<body onload="myFunction();">
	<div class="otp-container">
		<h2 class="text-center mb-4">Enter OTP</h2>
		<form action="RegisterServlet" method="post">
			<div class="form-group">
				<input type="text" class="form-control otp-input" name="otp"
					maxlength="4" required autofocus>
			</div>
			<button type="submit" class="btn otp-button">Verify OTP</button>
			<input type="hidden" name="mode" value="otpAuthenticate">
		</form>
	</div>
</body>
</html>


