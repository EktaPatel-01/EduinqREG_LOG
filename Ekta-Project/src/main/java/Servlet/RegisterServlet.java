package Servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otp.OtpSender;
import com.otp.PassValidator;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = "";
		javax.servlet.RequestDispatcher dispatcher = null;
		


		if (request.getParameter("mode") != null && "otpAuthenticate".equalsIgnoreCase(request.getParameter("mode"))) {

//				//get otp from cookie
//				and also from user getparameter
//				
//				otp====getparameter
//				forward to welcome the jsp

				try {
					Cookie[] cookies = request.getCookies();
					for (Cookie coke : cookies) {
						if (coke.getName().equalsIgnoreCase("otp")) {
							if (request.getParameter("otp") != null && !"".equalsIgnoreCase(request.getParameter("otp"))) {
								if (coke.getValue().equalsIgnoreCase(request.getParameter("otp"))) {
									uri="welcome.jsp";
									RequestDispatcher requestDispatcher = request.getRequestDispatcher(uri);
									requestDispatcher.forward(request, response);
								}else {
									// meansuser written otp and cokkie getteed otp is not same.
									request.setAttribute("errorMessage",
											"OTP Invalid");
									request.getRequestDispatcher("Error1.jsp").forward(request, response);
									throw new Exception("Invalid Otp");
									
								}
							}else {
								//means cokkie not exist for otp
								request.setAttribute("errorMessage",
										"OTP Invalid");
								request.getRequestDispatcher("Error1.jsp").forward(request, response);
								throw new Exception("Invalid Otp");
							}

						}
					}
					//same here cokkie is not available
					request.setAttribute("errorMessage",
							"OTP Invalid");
					request.getRequestDispatcher("Error1.jsp").forward(request, response);
					throw new Exception("Invalid Otp");
				}catch (Exception e) {
					uri="Error1.jsp";
				}

		} else {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String mobileNo = request.getParameter("mobile_no");
			String Password = request.getParameter("password");
			String confirmPassword = request.getParameter("cPassword");

			// Encode the raw password using Base64
			 String encodedPassword = base64Encode(Password);
			 System.out.println(encodedPassword);
    	
			if (!Password.equals(confirmPassword)) {

				request.setAttribute("errorMessage", "Passwords do not match.");
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);

			} else if (!PassValidator.isValidPassword(Password)) {
				// Password does not meet the complexity requirements, set an error message and
				// forward back to the error page.
				request.setAttribute("errorMessage",
						"Password must be 8 to 25 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special symbol (@#$%^&+=)");
				request.getRequestDispatcher("Error1.jsp").forward(request, response);

			} else

				
	           

		        // Save the encoded password and other details in the database

				// Now, save the user information to the database.

//		        response.setContentType("text/html");
//		        PrintWriter out = response.getWriter();

//
				try {

					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root",
							"uday12ka4");

					PreparedStatement pst = connection.prepareStatement(
							"insert into register(Name,Mobile_no,Email_id,Password ) values (?,?,?,?)");
					pst.setString(1, username);
					pst.setLong(2, Long.parseLong(mobileNo));
					pst.setString(3, email);
					pst.setString(4, encodedPassword);

					int executeUpdate = pst.executeUpdate();

					System.out.println(executeUpdate);
					{

						System.out.println("username " + username + ", email: " + email + ", mobileNo: " + mobileNo
								+ "Password" + Password + "<br>");
					}

					// send otp work

					int otp = (int) (Math.random() * 10000);

					Cookie cookie = new Cookie("otp", otp + "");
					cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(2)); // Set cookie expiration to 2 minutes
					cookie.setPath("/");

					response.addCookie(cookie);

					// Get the user's entered OTP
					String enteredOtp = request.getParameter("mode");

					// Retrieve the stored OTP from the cookie
					Cookie[] cookies = request.getCookies();
					String storedOtp; // Default value in case the cookie is not found
					if (cookies != null) {
						for (Cookie c : cookies) {
							if ("otp".equals(c.getName())) {
//		                     storedOtp = Integer.parseInt(c.getValue());
								storedOtp = c.getValue();
								break;
							}
						}
					}
					uri = "otpPage.jsp";

					OtpSender.sendVerificationOTP(email, otp);

				}

				catch (Exception e) {
					e.printStackTrace();
//		            out.println("Error: " + e.getMessage());
					request.setAttribute("error", "Something went wrong please try again");
					uri = "registration.jsp";
				} finally {
					dispatcher = request.getRequestDispatcher(uri);
					try {
						dispatcher.forward(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}

	}

	private String base64Encode(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

}
