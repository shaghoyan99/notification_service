package am.agro_trade.notification_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "User notification settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettingsDTO {

    @NotNull(message = "UserId is required")
    @Positive(message = "UserId must be positive")
    @Schema(description = "User ID", example = "12345", required = true)
    @JsonProperty("userId")
    private Long userId;

    @NotNull(message = "Email is required")
    @Email
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Email setting is required")
    @Schema(description = "Are email notifications enabled", example = "true")
    @JsonProperty("emailEnabled")
    private Boolean emailEnabled;

    @NotNull(message = "SMS setting is required")
    @Schema(description = "Are SMS notifications enabled", example = "false")
    @JsonProperty("smsEnabled")
    private Boolean smsEnabled;

    @NotNull(message = "In-app setting is required")
    @Schema(description = "Are in-app notifications enabled", example = "true")
    @JsonProperty("inAppEnabled")
    private Boolean inAppEnabled;

}
