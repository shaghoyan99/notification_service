package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.exception.EmailSendException;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.properties.AuthProperties;
import am.agro_trade.notification_service.properties.FrontendProperties;
import am.agro_trade.notification_service.service.EmailOutboxService;
import am.agro_trade.notification_service.service.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SendEmailImpl implements SendMailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final EmailOutboxService emailOutboxService;
    private final AuthProperties authProperties;
    private final FrontendProperties frontendProperties;

    @Value("${corporation.email}")
    private String corporationEmail;


    @Override
    @Async
    public void sendMail(String to, String code, String url, String productName, EmailType type) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setFrom(corporationEmail);
            helper.setTo(to);

            EmailTemplateData templateData = prepareTemplateData(type, code, url, productName);

            String htmlContent = templateEngine.process(templateData.templateName(), templateData.context());

            helper.setSubject(templateData.subject());
            helper.setText(htmlContent, true);

            emailSender.send(mimeMessage);

            emailOutboxService.save(to, code, url, productName, type, Status.SENT);

        } catch (MessagingException e) {
            emailOutboxService.save(to, code, url, productName, type, Status.FAILED);
            throw new EmailSendException("Failed to send email to " + to, e);
        }
    }

    private EmailTemplateData prepareTemplateData(EmailType type,
                                                  String code,
                                                  String url,
                                                  String productName) {
        return switch (type) {
            case VERIFY -> createVerificationData(requireValue(code, "code"));
            case WELCOME -> createWelcomeData();
            case RESET_PASSWORD -> createResetPasswordData(requireValue(code, "code"));
            case ORDER_OPENED -> createOrderOpenedData(
                    requireValue(url, "orderUrl"),
                    requireValue(productName, "productName")
            );
        };
    }

    private EmailTemplateData createOrderOpenedData(String url, String productName) {
        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("orderUrl", url);
        ctx.setVariable("productName", productName);

        return new EmailTemplateData(
                "New Order Created",
                "mail/orderOpenedMailTemplate",
                ctx);
    }

    private EmailTemplateData createVerificationData(String code) {
        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("code", code);

        String verifyLink = UriComponentsBuilder
                .fromUriString(authProperties.baseUrl())
                .path(authProperties.verifyPath())
                .queryParam("code", code)
                .toUriString();

        ctx.setVariable("verifyLink", verifyLink);

        return new EmailTemplateData(
                "Verify your email",
                "mail/verificationMailTemplate",
                ctx
        );
    }

    private EmailTemplateData createWelcomeData() {
        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("appLink", UriComponentsBuilder
                .fromUriString(frontendProperties.baseUrl())
                .toUriString());

        return new EmailTemplateData(
                "Welcome 🎉",
                "mail/welcomeMailTemplate",
                ctx
        );
    }

    private EmailTemplateData createResetPasswordData(String code) {
        Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("code", code);

        String resetLink = UriComponentsBuilder
                .fromUriString(authProperties.baseUrl())
                .path(authProperties.resendCodePath())
                .queryParam("code", code)
                .toUriString();

        ctx.setVariable("resetLink", resetLink);

        return new EmailTemplateData(
                "Reset your password",
                "mail/resetPasswordMailTemplate",
                ctx
        );
    }

    private String requireValue(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required for email template rendering");
        }

        return value;
    }

    private record EmailTemplateData(
            String subject,
            String templateName,
            Context context
    ) {
    }
}
