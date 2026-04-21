package am.agro_trade.notification_service.dto.request;

import am.agro_trade.notification_service.model.enums.EmailType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SendNotificationRequest(

        @NotEmpty(message = "User IDs list must not be empty")
        List<Long> userIds,

        String code,

        @NotNull(message = "EmailType is required")
        EmailType emailType,

        OrderOpenedDto orderOpenedDto
) {
}