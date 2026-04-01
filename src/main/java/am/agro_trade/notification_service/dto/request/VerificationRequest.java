package am.agro_trade.notification_service.dto.request;

import am.agro_trade.notification_service.model.enums.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VerificationRequest(
        @Email
        @NotBlank
        String email,

        String code,

        @NotNull
        EmailType emailType

) {}