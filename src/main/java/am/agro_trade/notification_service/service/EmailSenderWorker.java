package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.repository.EmailOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSenderWorker {
    private final EmailOutboxRepository repository;
    private final SendEmailService sendEmailService;

    @Scheduled(fixedDelay = 5000)
    public void processEmails() {
        var emails = repository.findTop10ByStatusInOrderByCreatedAtAsc(
                List.of(Status.NEW,Status.FAILED)
        );
        for (var email : emails) {
            try {
                sendEmailService.sendMail(
                        email.getToEmail(),
                        email.getCode(),
                        email.getType()
                );
                email.setStatus(Status.SENT);
                email.setSentAt(LocalDateTime.now());
            } catch (Exception e) {
                email.setStatus(Status.FAILED);
                email.setRetries(email.getRetries() + 1);
            }
            repository.save(email);
        }
    }
}
