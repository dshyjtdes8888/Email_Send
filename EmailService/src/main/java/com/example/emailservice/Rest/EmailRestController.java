package com.example.emailservice.Rest;

import com.example.emailservice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/email")
public class EmailRestController {

    private final EmailService emailService;

    @Autowired
    public EmailRestController(EmailService emailService) {
        this.emailService = emailService;
    }

@RequestMapping("/send")
public boolean sendEmail(
        @RequestParam String subject,
        @RequestParam String toAddress,
        @RequestParam String body) {
    return emailService.sendEmail(subject, toAddress, body);
}

    @RequestMapping("/sendBatch")
    public boolean sendEmailBatch(
            @RequestParam String subject,
            @RequestParam String toAddress,
            @RequestParam String body) {
        return emailService.sendEmailBatch(subject, toAddress, body);
    }

    @RequestMapping("/validate")
    public boolean validateEmailAddress(@RequestParam String emailAddress) {
        return emailService.validateEmailAddress(emailAddress);
    }

}
