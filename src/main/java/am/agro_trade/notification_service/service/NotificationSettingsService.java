package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;

import java.util.List;

public interface NotificationSettingsService {
    void save(NotificationSettingsDTO notificationSettings);

    List<NotificationSettingsDTO> findAll();

    NotificationSettingsDTO findById(long notificationSettingsId);
}
