package am.agro_trade.notification_service.dto.request;

import am.agro_trade.notification_service.model.enums.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SendNotificationRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Code is required")
        @Size(min = 4, max = 10, message = "Code must be between 4 and 10 characters")
        String code,

        @NotNull(message = "EmailType is required")
        EmailType emailType
) {
}