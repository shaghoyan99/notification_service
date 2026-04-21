package am.agro_trade.notification_service.repository;

import am.agro_trade.notification_service.model.EmailOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailOutboxRepository extends JpaRepository<EmailOutbox, Long> {

    @Query("""
                SELECT e FROM EmailOutbox e
                WHERE e.status = 'FAILED'
            """)
    List<EmailOutbox> findAllFailed();
}
