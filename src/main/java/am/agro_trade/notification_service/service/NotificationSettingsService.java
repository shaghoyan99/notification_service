package am.agro_trade.notification_service.service;
import am.agro_trade.notification_service.model.NotificationSettings;

import java.util.List;
import java.util.Optional;

public interface NotificationSettingsService {
    void save(NotificationSettings notificationSettings);

    void delete(long notificationSettingsId);

    List<NotificationSettings> findAll();

    Optional<NotificationSettings> findById(long notificationSettingsId);
}
