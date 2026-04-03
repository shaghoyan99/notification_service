package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.EmailOutbox;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.repository.EmailOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final EmailOutboxRepository repository;

    public void queueEmail(String to,String code, EmailType type){
        EmailOutbox email = new EmailOutbox();
        email.setToEmail(to);
        email.setCode(code);
        email.setType(type);
        email.setRetries(0);
        email.setStatus(Status.NEW);
        email.setCreatedAt(LocalDateTime.now());
        email.setSentAt(null);

        repository.save(email);
    }
}
