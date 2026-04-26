package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.model.enums.Status;
import am.agro_trade.notification_service.repository.EmailOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderWorker {

    private final EmailOutboxRepository repository;
    private final SendEmailImpl sendEmailImpl;

    @Scheduled(cron = "0 * * * * *")
    public void processEmails() {
        var emails = repository.findAllFailed();
        for (var email : emails) {
            try {
                sendEmailImpl.sendMail(
                        email.getToEmail(),
                        email.getCode(),
                        email.getUrl(),
                        email.getProductName(),
                        email.getType()
                );
                email.setStatus(Status.SENT);
            } catch (Exception e) {
                email.setStatus(Status.FAILED);
                email.setRetries(email.getRetries() + 1);
            }
            repository.save(email);
        }
    }
}
