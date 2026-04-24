package am.agro_trade.notification_service.endpoint.impl;

import am.agro_trade.notification_service.dto.request.OrderNotificationRequest;
import am.agro_trade.notification_service.dto.request.SendNotificationSettingsRequest;
import am.agro_trade.notification_service.dto.request.VerifyNotificationRequest;
import am.agro_trade.notification_service.dto.request.WelcomeNotificationRequest;
import am.agro_trade.notification_service.endpoint.VerifyEmailSenderV1API;
import am.agro_trade.notification_service.service.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSenderEndpoint implements VerifyEmailSenderV1API {

    private final NotificationSettingsService service;

    @Override
    public void sendVerifyEmail(VerifyNotificationRequest request) {
        service.sendVerificationNotification(request);
    }

    @Override
    public void sendResetPasswordEmail(VerifyNotificationRequest request) {
        service.sendResetPasswordNotification(request);
    }

    @Override
    public void sendOrderOpenedEmail(OrderNotificationRequest request) {
        service.sendOrderOpenedNotification(request);
    }

    @Override
    public void sendWelcomeEmail(WelcomeNotificationRequest request) {
        service.sendWelcomeNotification(request);
    }

    @Override
    public void saveNotificationSettings(SendNotificationSettingsRequest request) {
        service.save(request.notificationSettingsDTO());
    }
}
