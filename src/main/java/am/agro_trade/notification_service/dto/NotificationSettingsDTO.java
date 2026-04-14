package am.agro_trade.notification_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User notification settings")
public class NotificationSettingsDTO {

    @Schema(description = "User ID", example = "12345", required = true)
    private long userId;

    @Schema(description = "Are email notifications enabled", example = "true")
    private boolean emailEnabled;

    @Schema(description = "Are SMS notifications enabled", example = "false")
    private boolean smsEnabled;

    @Schema(description = "Are in-app notifications enabled", example = "true")
    private boolean inAppEnabled;

    public NotificationSettingsDTO() {

    }
}
