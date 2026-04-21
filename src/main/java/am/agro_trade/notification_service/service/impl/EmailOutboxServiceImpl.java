package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.model.EmailOutbox;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.repository.EmailOutboxRepository;
import am.agro_trade.notification_service.service.EmailOutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class EmailOutboxServiceImpl implements EmailOutboxService {

    private final EmailOutboxRepository repository;

    public void save(String to,
                     String code,
                     String url,
                     String productName,
                     EmailType type,
                     Status status) {

        EmailOutbox email = new EmailOutbox();
        email.setToEmail(to);
        email.setCode(code);
        email.setUrl(url);
        email.setProductName(productName);
        email.setType(type);
        email.setRetries(0);
        email.setStatus(status);
        email.setCreatedAt(LocalDateTime.now());

        repository.save(email);
    }
}
