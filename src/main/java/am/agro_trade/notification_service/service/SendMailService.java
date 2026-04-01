package am.agro_trade.notification_service.service;

import am.agro_trade.notification_service.model.enums.EmailType;

public interface SendMailService {

    void sendMail(String to, String code, EmailType type);





}
