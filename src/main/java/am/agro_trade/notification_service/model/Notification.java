package am.agro_trade.notification_service.model;

import am.agro_trade.notification_service.model.enums.ReferenceType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.model.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  long userId;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String titleMessage;

    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

    private long referenceId;

    private LocalDateTime createdAt;
}
