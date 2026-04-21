package am.agro_trade.notification_service.model;

import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_outbox")
public class EmailOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String toEmail;

    private String code;

    private String url;

    private String productName;

    @Enumerated(EnumType.STRING)
    private EmailType type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int retries;

    private LocalDateTime createdAt;
}
