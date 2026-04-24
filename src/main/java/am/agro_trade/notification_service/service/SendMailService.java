package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.enums.EmailType;

/**
 * Sends email messages using the configured mail transport.
 */
public interface SendMailService {

    /**
     * Sends an email for the specified business event.
     *
     * @param to          recipient email address
     * @param code        verification or business code used by the email template
     * @param url         related URL included in the email
     * @param productName product name included in the email content
     * @param type        email template type
     */
    void sendMail(String to, String code, String url, String productName, EmailType type);

}
