package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;

/**
 * Persists outgoing email messages in the outbox for later processing or tracking.
 */
public interface EmailOutboxService {

    /**
     * Saves an email message in the outbox.
     *
     * @param to recipient email address
     * @param code verification or business code associated with the email
     * @param url related URL included in the email
     * @param productName product name included in the email content
     * @param type email template type
     * @param status current delivery status stored for the outbox entry
     */
    void save(String to,
              String code,
              String url,
              String productName,
              EmailType type,
              Status status);
}
