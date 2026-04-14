package am.agro_trade.notification_service.dto;

import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.model.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Notification DTO")
public class NotificationDTO {
    @Schema(description = "User ID to whom the notification is sent", example = "42")
    private  long userId;

    @Schema(description = "Notification type", example = "EMAIL")
    private Type type;

    @Schema(description = "Notification status", example = "SENT")
    private Status status;

    @Schema(description = "Title or text of the notification", example ="Your order is confirmed")
    private String titleMessage;

    @Schema(description = "Type of related entity", example = "VERIFICATION")
    private EmailType referenceType;

    @Schema(description = "ID of related entity", example = "98765")
    private long referenceId;

    @Schema(description = "Date and time of notification creation", example = "2026-04-10T14:25:00")
    private LocalDateTime createdAt;

    public NotificationDTO() {}


}
