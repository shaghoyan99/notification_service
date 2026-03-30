package am.agro_trade.notification_service.endpoint;

import am.agro_trade.notification_service.dto.request.VerificationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification-service/api/v1")
public interface VerifyEmailSenderV1API {

    @PostMapping("/send-email-verify")
    ResponseEntity<String> sendVerificationCode(@RequestBody @Valid VerificationRequest request);

}