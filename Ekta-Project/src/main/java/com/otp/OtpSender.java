package com.otp;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpSender {

	private static final String FROM_EMAIL = "ektaimec@gmail.com";
    private static final String PASSWORD = "ztlxuuzomckeqaef";
    
    public static void sendVerificationOTP(String toEmail, int otp) {
    	try {
        String subject = "Email Verification OTP";
        String body = 
        		"<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Email Verification OTP</title>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f2f2f2;\">\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 20px;\">\n" +
                "                <img src=\"src\\images\\eduinq.jpeg\" alt=\"Company Logo\" width=\"150\" style=\"display: block;\">\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td bgcolor=\"#ffffff\" style=\"padding: 20px; font-size: 16px; color: #333333;\">\n" +
                "                <h1 style=\"margin: 0;\">Email Verification OTP</h1>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td bgcolor=\"#ffffff\" style=\"padding: 20px; font-size: 16px; color: #333333;\">\n" +
                "                <p>Your email verification OTP is: <strong>" + otp + "</strong></p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 20px;\">\n" +
                "                <img src=\"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.alamy.com%2Femail-with-document-and-round-green-check-mark-icon-successful-verification-concepts-vector-email-icon-image260068837.html&psig=AOvVaw3mlAWhRxYyboopwIx16W35&ust=1691595596808000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIDOkeyyzYADFQAAAAAdAAAAABAE\" alt=\"Verification Image\" width=\"300\" style=\"display: block;\">\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td bgcolor=\"#007bff\" style=\"padding: 20px; text-align: center;\">\n" +
                "                <p style=\"margin: 0; font-size: 18px; color: #ffffff;\">\n" +
                "                    If you did not request this email, please ignore it.\n" +
                "                </p>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";


        // Set the host and port for the Gmail SMTP server
        String host = "smtp.gmail.com";
        int port = 587;

        // Create properties for the mail session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a session with the Gmail SMTP server
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            message.setContent("<h1>"+body+"</h1>","text/html");

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println("Failed to send email: " + e.getMessage());
        }

    }catch(Exception e) {
    	e.printStackTrace();
    }
}
}
