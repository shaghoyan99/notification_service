package am.agro_trade.notification_service.endpoint;

import am.agro_trade.notification_service.dto.request.SendNotificationRequest;
import am.agro_trade.notification_service.dto.request.SendNotificationSettingsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification API", description = "Operations for sending notifications and managing notification settings")
@RequestMapping("/notification-service/api/v1/notifications")
@RestController
public interface VerifyEmailSenderV1API {

    @Operation(
            summary = "Send notification",
            description = "Sends a notification based on the requested email type and user notification settings",
            requestBody = @RequestBody(
                    required = true,
                    description = "Notification request payload",
                    content = @Content(schema = @Schema(implementation = SendNotificationRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload"
            )
    })
    @PostMapping("/send")
    void sendEmail(@Valid @org.springframework.web.bind.annotation.RequestBody SendNotificationRequest request);

    @Operation(
            summary = "Save notification settings",
            description = "Creates or updates notification settings for a user",
            requestBody = @RequestBody(
                    required = true,
                    description = "Notification settings payload",
                    content = @Content(schema = @Schema(implementation = SendNotificationSettingsRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Notification settings saved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid notification settings payload"
            )
    })
    @PostMapping("/settings/save")
    void saveNotificationSettings(
            @Valid @org.springframework.web.bind.annotation.RequestBody SendNotificationSettingsRequest request
    );

}
