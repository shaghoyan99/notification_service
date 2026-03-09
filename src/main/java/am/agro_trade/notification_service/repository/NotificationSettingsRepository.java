package am.agro_trade.notification_service.repository;

import am.agro_trade.notification_service.model.NotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Long> {
}
