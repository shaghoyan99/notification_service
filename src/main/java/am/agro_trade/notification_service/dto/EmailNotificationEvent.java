package am.agro_trade.notification_service.dto;

import am.agro_trade.notification_service.model.enums.EmailType;

public record EmailNotificationEvent(
        String to,
        String code,
        String url,
        String productName,
        EmailType type
) {
}
