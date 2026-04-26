package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import am.agro_trade.notification_service.dto.request.OrderNotificationRequest;
import am.agro_trade.notification_service.dto.request.VerifyNotificationRequest;
import am.agro_trade.notification_service.dto.request.WelcomeNotificationRequest;

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
     * Sends a verification email to a single user.
     *
     * @param request verification notification payload
     */
    void sendVerificationNotification(VerifyNotificationRequest request);

    /**
     * Sends a reset password email to a single user.
     *
     * @param request reset password notification payload
     */
    void sendResetPasswordNotification(VerifyNotificationRequest request);

    /**
     * Sends an order-opened email to all provided recipients.
     *
     * @param request order-opened notification payload
     */
    void sendOrderOpenedNotification(OrderNotificationRequest request);

    /**
     * Sends a welcome email to a single user.
     *
     * @param request welcome notification payload
     */
    void sendWelcomeNotification(WelcomeNotificationRequest request);

}
