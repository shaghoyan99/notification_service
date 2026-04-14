package am.agro_trade.notification_service.endpoint;

import am.agro_trade.notification_service.dto.request.SendNotificationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Email Sender Endpoint", description = "API for sending email notifications")
@RequestMapping("/notification-service/api/v1/notifications")
@RestController

public interface VerifyEmailSenderV1API {
    @Operation(
            summary = "Sending email notification",
            description = "Sends an email with verification code or notification"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Email sent successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request data",
            content = @Content(schema = @Schema(implementation = SendNotificationRequest.class))
    )

    @PostMapping("/send")
    void sendEmail(@RequestBody @Valid SendNotificationRequest request);

}