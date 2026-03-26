package am.agro_trade.notification_service.dto.request;

public record VerificationRequest(

        String email,
        String code
) {
}