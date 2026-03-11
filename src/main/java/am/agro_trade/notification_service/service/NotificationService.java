package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.dto.NotificationDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    void save(NotificationDTO notificationDTO);

    void delete(long notificationId);

    List<NotificationDTO> findAll();

    Optional<NotificationDTO> findById(long notificationId);







}
