package am.agro_trade.notification_service.service.impl;


import am.agro_trade.notification_service.exception.EmailSendException;
import am.agro_trade.notification_service.service.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SendEmailService implements SendMailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        emailSender.send(simpleMailMessage);
    }

    @Override
    @Async
    public void sendVerificationMailHtml(String to, String verifyCode) {
        final Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("code", verifyCode);
        final MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            final MimeMessageHelper message =
                    new MimeMessageHelper(mimeMessage, false, "UTF-8"); // true = multipart
            message.setSubject("Please verify your email address");
            message.setFrom("wahejavawahe@gmail.com");
            message.setTo(to);
            final String htmlContent = templateEngine.process("mail/verificationMailTemplate", ctx);
            message.setText(htmlContent, true);
            emailSender.send(mimeMessage);
        }catch (MessagingException e){
            throw new EmailSendException("Failed to send email to " + to, e);
        }
    }
}
