package am.agro_trade.notification_service.dto;

import am.agro_trade.notification_service.model.enums.ReferenceType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.model.enums.Type;

import java.time.LocalDateTime;

public class NotificationDTO {
    private  long userId;

    private Type type;

    private Status status;

    private String titleMessage;

    private ReferenceType referenceType;

    private long referenceId;

    private LocalDateTime createdAt;
}
