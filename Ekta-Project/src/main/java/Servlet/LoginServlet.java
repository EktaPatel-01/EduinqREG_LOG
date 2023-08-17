package Servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	response.sendRedirect("welcome.jsp");
//	HttpSession session = request.getSession();
//	session.setAttribute("username", "ekta");

		String email = request.getParameter("email");
		String Password = request.getParameter("password");
		
		String encodedEnteredPassword = base64Encode(Password);


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root",
					"uday12ka4");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM register WHERE Email_id=? AND Password=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, encodedEnteredPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);

//			if (encodedEnteredPassword.equals(encodedStoredPassword))
			
			if (resultSet.next()) {
				// User is logged in successfully. Redirect to the success page.
				response.sendRedirect("welcome.jsp");
				javax.servlet.http.HttpSession session = request.getSession();
				session.setAttribute("username", email);
			} else {
				// Login failed. Redirect back to the login page with an error message.
				response.sendRedirect("index.jsp?loginFailed=true");

			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle database connection errors.
		}
	}
	private String base64Encode(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}
