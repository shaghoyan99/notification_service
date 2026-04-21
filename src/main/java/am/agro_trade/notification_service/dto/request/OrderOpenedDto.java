package am.agro_trade.notification_service.dto.request;

public record OrderOpenedDto(
        String orderUrl,
        String productName) {
}