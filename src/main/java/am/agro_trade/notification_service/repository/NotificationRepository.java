package am.agro_trade.notification_service.repository;

import am.agro_trade.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
