package am.agro_trade.notification_service.endpoint.impl;


import am.agro_trade.notification_service.dto.request.VerificationRequest;
import am.agro_trade.notification_service.endpoint.VerifyEmailSenderV1API;
import am.agro_trade.notification_service.service.SendMailService;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class EmailSenderEndpoint implements VerifyEmailSenderV1API {

    private final SendMailService sendMailService;


    @Override
    public void sendVerificationCode(VerificationRequest request) {
        sendMailService.sendVerificationMailHtml(request.email(),request.code());
    }
}
