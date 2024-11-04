package com.example.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSetPasswordEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText("""
                <div>
                  <p>Your OTP for setting the password is: %s</p>
                </div>
                """.formatted(otp), true);

        javaMailSender.send(mimeMessage);
    }

    // Method to generate a random 6-digit OTP
    public String generateOTP() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }
}

//@Component
//public class EmailUtil {
//	
//	 @Autowired
//	  private JavaMailSender javaMailSender;
//	 
//	 public void sendSetPasswordEmail(String email) throws MessagingException {
//	        // Generate OTP
//	        String otp = generateOTP();
//
//	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//	        mimeMessageHelper.setTo(email);
//	        mimeMessageHelper.setSubject("Set Password");
//	        mimeMessageHelper.setText("""
//	                <div>
//	                  <p>Your OTP for setting the password is: %s</p>
//	                </div>
//	                """.formatted(otp), true);
//
//	        javaMailSender.send(mimeMessage);
//	    }
//
//	    // Method to generate a random 6-digit OTP
//	    private String generateOTP() {
//	        Random random = new Random();
//	        int otp = 100_000 + random.nextInt(900_000);
//	        return String.valueOf(otp);
//	    }
//
//}
