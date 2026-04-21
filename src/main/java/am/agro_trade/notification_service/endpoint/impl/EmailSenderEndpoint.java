package am.agro_trade.notification_service.endpoint.impl;

import am.agro_trade.notification_service.dto.request.SendNotificationRequest;
import am.agro_trade.notification_service.dto.request.SendNotificationSettingsRequest;
import am.agro_trade.notification_service.endpoint.VerifyEmailSenderV1API;
import am.agro_trade.notification_service.service.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSenderEndpoint implements VerifyEmailSenderV1API {

    private final NotificationSettingsService service;

    @Override
    public void sendEmail(SendNotificationRequest request) {
        service.sendNotification(request);
    }

    @Override
    public void saveNotificationSettings(SendNotificationSettingsRequest request) {
        service.save(request.notificationSettingsDTO());
    }
}
