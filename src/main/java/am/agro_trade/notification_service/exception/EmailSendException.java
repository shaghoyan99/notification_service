package am.agro_trade.notification_service.exception;

import jakarta.mail.MessagingException;

public class EmailSendException extends RuntimeException {
    public EmailSendException(String message, MessagingException e) {
        super(message);
    }
}
