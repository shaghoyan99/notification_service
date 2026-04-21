package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.exception.EmailSendException;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SendEmailImpl implements SendMailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final EmailOutboxService emailOutboxService;

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

            EmailTemplateData templateData = prepareTemplateData(type, code, url,productName);

            String htmlContent = templateEngine.process(templateData.templateName(), templateData.context());

            helper.setSubject(templateData.subject());
            helper.setText(htmlContent, true);

            emailSender.send(mimeMessage);

            emailOutboxService.save(to, code, url,productName, type, Status.SENT);

        } catch (MessagingException e) {
            emailOutboxService.save(to, code, url,productName, type, Status.FAILED);
            throw new EmailSendException("Failed to send email to " + to, e);
        }
    }

    private EmailTemplateData prepareTemplateData(EmailType type,
                                                  String code,
                                                  String url,
                                                  String productName) {

        Context ctx = new Context(Locale.ENGLISH);

        if (code != null) {
            ctx.setVariable("code", code);
        }
        if (url != null) {
            ctx.setVariable("orderUrl", url);
        }
        if (productName != null) {
            ctx.setVariable("productName", productName);
        }

        return switch (type) {
            case VERIFY -> createVerificationData(ctx, code);
            case WELCOME -> createWelcomeData(ctx);
            case RESET_PASSWORD -> createResetPasswordData(ctx, code);
            case ORDER_OPENED -> createOrderOpenedData(ctx);
            default -> throw new IllegalArgumentException("Unknown email type: " + type);
        };
    }

    private EmailTemplateData createOrderOpenedData(Context ctx) {
        return new EmailTemplateData(
                "New Order Created",
                "mail/orderOpenedMailTemplate",
                ctx);
    }

    private EmailTemplateData createVerificationData(Context ctx, String code) {
        String verifyLink = "http://localhost:8080/agro-trade-service/api/v1/auth/verify?code=" + code;

        ctx.setVariable("verifyLink", verifyLink);

        return new EmailTemplateData(
                "Verify your email",
                "mail/verificationMailTemplate",
                ctx
        );
    }

    private EmailTemplateData createWelcomeData(Context ctx) {
        ctx.setVariable("appLink", "http://localhost:8080/");

        return new EmailTemplateData(
                "Welcome 🎉",
                "mail/welcomeMailTemplate",
                ctx
        );
    }

    private EmailTemplateData createResetPasswordData(Context ctx, String code) {
        String resetLink = "http://localhost:8080/agro-trade-service/api/v1/auth/resend-code?code=" + code;

        ctx.setVariable("resetLink", resetLink);

        return new EmailTemplateData(
                "Reset your password",
                "mail/resetPasswordMailTemplate",
                ctx
        );
    }

    private record EmailTemplateData(
            String subject,
            String templateName,
            Context context
    ) {
    }
}
