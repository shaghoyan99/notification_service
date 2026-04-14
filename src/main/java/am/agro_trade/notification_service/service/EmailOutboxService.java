package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.model.enums.Status;

public interface EmailOutboxService {

    void save(String to, String code, EmailType type, Status status);
}
