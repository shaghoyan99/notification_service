package am.agro_trade.notification_service.repository;

import am.agro_trade.notification_service.model.NotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Long> {

    Optional<NotificationSettings> findByUserId(long userId);

    List<NotificationSettings> findAllByUserIdIn(List<Long> userIds);

}
