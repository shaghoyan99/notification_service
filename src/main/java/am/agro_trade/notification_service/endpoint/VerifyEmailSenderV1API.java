package am.agro_trade.notification_service.endpoint;

import am.agro_trade.notification_service.dto.request.VerificationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification-service/api/v1/notifications")
/**
 * API for sending verification codes to user email.
 */
public interface VerifyEmailSenderV1API {
    /**
     * Sends a verification code to the specified email.
     *
     * @param request contains user's email and verification code
     */
    @PostMapping("/verification-code")
    void sendVerificationCode(@RequestBody @Valid VerificationRequest request);

}