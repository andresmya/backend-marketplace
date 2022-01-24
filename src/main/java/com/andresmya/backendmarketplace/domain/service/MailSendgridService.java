package com.andresmya.backendmarketplace.domain.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailSendgridService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    @Value("${sendgrid.api.email.sender}")
    private String sendGridSender;

    protected void sendResetPasswordMail(String recipient, String verificationCode) throws IOException {

        Email from = new Email(sendGridSender, "AndresMya");
        String subject = "Reset your password - AndresMya Marketplace";
        Email to = new Email(recipient);
        Content content = new Content("text/plain", "/* TEMPLATE UNDER CONSTRUCTION */ \n Your verification code is: " + verificationCode);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
    }


}
