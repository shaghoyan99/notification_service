package am.agro_trade.notification_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OrderOpenedDto(

        @NotBlank(message = "Order URL is required")
        String orderUrl,

        @NotBlank(message = "Product name is required")
        String productName) {
}
