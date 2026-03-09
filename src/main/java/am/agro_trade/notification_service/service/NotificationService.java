package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    void save(Notification notification);

    void delete(long notificationId);

    List<Notification> findAll();

    Optional<Notification> findById(long notificationId);







}
