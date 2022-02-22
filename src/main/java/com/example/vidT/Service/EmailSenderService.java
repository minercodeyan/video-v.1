package com.example.vidT.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService {


    private JavaMailSender mailS;

    @Autowired
    public EmailSenderService(JavaMailSender mailS) {
        this.mailS = mailS;
    }

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("timemachineservicenew@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailS.send(message);
        System.out.println("Mail Send...");
    }

}
