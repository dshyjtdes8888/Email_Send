package com.example.emailservice.Soap;

import com.example.emailservice.EmailService;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@CrossOrigin(origins = "*")
@Endpoint
public class EmailEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private EmailService emailService;

    @Autowired
    public EmailEndpoint(EmailService emailService) {
        this.emailService = emailService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendEmailRequest")
    @ResponsePayload
    public SendEmailResponse sendEmail(@RequestPayload SendEmailRequest request) {
        SendEmailResponse response = new SendEmailResponse();

        // SendEmailRequest 包含 subject、toAddress 和 body 字段
        String subject = request.getSubject();
        String toAddress = request.getToAddress();
        String body = request.getBody();

        boolean result = emailService.sendEmail(subject, toAddress, body);
        response.setSuccess(result);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendEmailBatchRequest")
    @ResponsePayload
    public SendEmailBatchResponse sendEmailBatch(@RequestPayload SendEmailBatchRequest request) {
        SendEmailBatchResponse response = new SendEmailBatchResponse();

        // SendEmailBatchRequest 包含 subject、toAddress 和 body 字段
        String subject = request.getSubject();
        String toAddress = request.getToAddress();
        String body = request.getBody();

        boolean result = emailService.sendEmailBatch(subject, toAddress, body);
        response.setSuccess(result);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateEmailAddressRequest")
    @ResponsePayload
    public ValidateEmailAddressResponse validateEmailAddress(@RequestPayload ValidateEmailAddressRequest request) {
        ValidateEmailAddressResponse response = new ValidateEmailAddressResponse();

        // ValidateEmailAddressRequest 包含一个 emailAddress 字段
        String emailAddress = request.getEmailAddress();

        boolean result = emailService.validateEmailAddress(emailAddress);
        response.setValid(result);

        return response;
    }
}
