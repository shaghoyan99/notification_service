package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.exception.EmailSendException;
import am.agro_trade.notification_service.model.enums.EmailType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${corporation.email}")
    private String corporationEmail;

    @Override
    @Async
    public void sendMail(String to, String code, EmailType type) {
        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("code", code);

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(mimeMessage, false, "UTF-8");

            message.setFrom(corporationEmail);
            message.setTo(to);

            String subject;
            String template;

            switch (type) {
                case VERIFICATION -> {
                    subject = "Verify your email";
                    template = "mail/verificationMailTemplate";
                    ctx.setVariable("verifyLink", "http://localhost:8080/reset?code=" + code);
                }
                case WELCOME -> {
                    subject = "Welcome 🎉";
                    template = "mail/welcomeMailTemplate";
                    ctx.setVariable("appLink", "http://localhost:8080/");
                }
                case RESET_PASSWORD -> {
                    subject = "Reset your password";
                    template = "mail/resetPasswordMailTemplate";
                    ctx.setVariable("resetLink",  "http://localhost:8080/reset?code=" + code);
                }
                default -> throw new IllegalArgumentException("Unknown email type");
            }
            String html = templateEngine.process(template, ctx);
            message.setSubject(subject);
            message.setText(html, true);

            emailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new EmailSendException("Failed to send email to " + to, e);
        }
    }
}
