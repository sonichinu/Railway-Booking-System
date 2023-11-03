package com.irctc.sendpdfmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class EmailService {

    private String to;
    private String subject;
    private String message;
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String to,String subject, String message, String attachment) throws MessagingException {
        boolean f=false;
        MimeMessage helper = this.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessage = new MimeMessageHelper(helper,true);
        FileSystemResource resource = new FileSystemResource(new File(attachment));
        try {
            mimeMessage.setSubject(subject);
            mimeMessage.setTo(to);
            mimeMessage.setText(message);
            mimeMessage.addAttachment(resource.getFilename(), resource);
            mailSender.send(helper);
            f=true;
            System.out.println("SUCESSFULLY SENDED MAIL");
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return f;
    }

}
