package am.agro_trade.notification_service.service;

import org.springframework.stereotype.Repository;

@Repository
public interface SendMailService {

    void sendMail(String to, String subject, String content);

    void sendVerificationMailHtml(String to, String verifyCode);



}
