package model;


import jakarta.mail.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String eFrom = "mthanh1095@gmail.com"; //mật khẩu ứng dụng google
    private final String ePass = "ezct eryf ephd ttvs"; // App Password từ Google

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean sendEmail(String subject, String message, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(eFrom, ePass);
            }
        });

        try {
            jakarta.mail.Message msg = new jakarta.mail.internet.MimeMessage(session);
            msg.setFrom(new jakarta.mail.internet.InternetAddress(eFrom));
            msg.setRecipients(jakarta.mail.Message.RecipientType.TO,
                    jakarta.mail.internet.InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setContent(message, "text/html; charset=UTF-8");
            jakarta.mail.Transport.send(msg);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi email:");
            e.printStackTrace(); // In chi tiết ra console
            return false;
        }
    }

    public String subjectDiscount() {
        return "Perfume Paradise - Heyyy, Unlock Your Exclusive Discount Inside!";
    }

    public String subjectContact(String name) {
        return "Hey " + name + " you have an appointment with us - Perfume Paradise";
    }

    public String subjectOrder(String fullName) {
        return "Hi, " + fullName + ", thanks for your order from Perfume Paradise Store!";
    }

    public String subjectForgotPass() {
        return "Support forgot password";
    }

    public String messageDiscount(int discount) {
        return "<!DOCTYPE html><html><head><style>body{font-family:sans-serif;}" +
                ".code{background:#e74c3c;color:#fff;padding:10px;font-size:20px;}</style></head><body>" +
                "<h2>Special Discount Alert!</h2>" +
                "<p>You're the first to know about this special offer.</p>" +
                "<div class='code'>DISCOUNT" + discount + "</div>" +
                "<p>Don't miss out!</p></body></html>";
    }

    public String messageContact(String name) {
        return "<!DOCTYPE html><html><body><h2>Xin chào " + name + "!</h2>" +
                "<p>Cảm ơn bạn đã liên hệ với Perfume Paradise. Chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>" +
                "<p>Email hỗ trợ: customer_support@perfumeparadise.com</p></body></html>";
    }

    public String messageOrder(java.time.LocalDateTime date, double totalMoney, String address) {
        return "<!DOCTYPE html><html><body><h2>Order Confirmation</h2>" +
                "<p>Date: " + date + "</p>" +
                "<p>Total: $" + totalMoney + "</p>" +
                "<p>Shipping to: " + address + "</p>" +
                "<p>Thank you for shopping with us!</p></body></html>";
    }

    public String messageForgotPass(String name, int code) {
        return "<!DOCTYPE html><html><body><h2>Password Reset</h2>" +
                "<p>Hi " + name + ",</p>" +
                "<p>Your password reset code is:</p>" +
                "<div style='font-size:24px;font-weight:bold;color:#4CAF50;'>" + code + "</div>" +
                "<p>If you didn't request this, you can ignore it.</p></body></html>";
    }
}
