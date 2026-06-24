package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.dto.EmailNotificationEvent;
import am.agro_trade.notification_service.service.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationConsumer {

    private final SendMailService sendMailService;

    @KafkaListener(
            topics = "${spring.kafka.consumer.topics}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(EmailNotificationEvent event) {
        log.info("Received email event: type={}, to={}", event.type(), event.to());
        sendMailService.sendMail(
                event.to(),
                event.code(),
                event.url(),
                event.productName(),
                event.type()
        );
    }
}
