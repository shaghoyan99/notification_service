package am.agro_trade.notification_service.repository;

import am.agro_trade.notification_service.model.EmailOutbox;
import am.agro_trade.notification_service.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailOutboxRepository extends JpaRepository<EmailOutbox, Long> {
    List<EmailOutbox> findTop10ByStatusInOrderByCreatedAtAsc(List<Status> statuses);

}
