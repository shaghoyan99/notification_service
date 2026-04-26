package am.agro_trade.notification_service.dto.request;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@Schema(description = "Request payload for creating or updating user notification settings")
public record SendNotificationSettingsRequest(
        @JsonProperty("notificationSettingsDTO")
        @Valid
        @Schema(description = "Notification settings data for a single user", required = true)
        NotificationSettingsDTO notificationSettingsDTO) {

}
