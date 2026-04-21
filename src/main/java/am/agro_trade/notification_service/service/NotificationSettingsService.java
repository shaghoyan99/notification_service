package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import am.agro_trade.notification_service.dto.request.SendNotificationRequest;

/**
 * Manages notification settings and dispatching notifications for users.
 */
public interface NotificationSettingsService {

    /**
     * Creates or updates notification settings for a user.
     *
     * @param notificationSettings notification settings payload
     */
    void save(NotificationSettingsDTO notificationSettings);

    /**
     * Sends notifications according to the request and stored user settings.
     *
     * @param request notification request payload
     */
    void sendNotification(SendNotificationRequest request);

}
