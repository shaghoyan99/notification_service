package am.agro_trade.notification_service.endpoint.impl;

import am.agro_trade.notification_service.dto.request.SendNotificationRequest;
import am.agro_trade.notification_service.endpoint.VerifyEmailSenderV1API;
import am.agro_trade.notification_service.service.SendMailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmailSenderEndpoint implements VerifyEmailSenderV1API {

    private final SendMailService sendMailService;

    @Override
    public void sendEmail(@Valid SendNotificationRequest request) {
        sendMailService.sendMail(
                request.email(),
                request.code(),
                request.emailType()
        );
    }
}
