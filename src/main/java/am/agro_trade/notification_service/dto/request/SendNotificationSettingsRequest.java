package am.agro_trade.notification_service.dto.request;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

public record SendNotificationSettingsRequest(
        @JsonProperty("notificationSettingsDTO")
        @Valid
        NotificationSettingsDTO notificationSettingsDTO) {

}
