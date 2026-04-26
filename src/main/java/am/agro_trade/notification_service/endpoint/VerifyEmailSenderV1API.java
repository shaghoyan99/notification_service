package am.agro_trade.notification_service.endpoint;

import am.agro_trade.notification_service.dto.request.OrderNotificationRequest;
import am.agro_trade.notification_service.dto.request.SendNotificationSettingsRequest;
import am.agro_trade.notification_service.dto.request.VerifyNotificationRequest;
import am.agro_trade.notification_service.dto.request.WelcomeNotificationRequest;
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
            summary = "Send verification notification",
            description = "Sends a verification email to a single user",
            requestBody = @RequestBody(
                    required = true,
                    description = "Verification notification payload",
                    content = @Content(schema = @Schema(implementation = VerifyNotificationRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Verification notification processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid verification notification payload"
            )
    })
    @PostMapping("/send/verify")
    void sendVerifyEmail(@Valid @RequestBody VerifyNotificationRequest request);

    @Operation(
            summary = "Send reset password notification",
            description = "Sends a reset password email to a single user",
            requestBody = @RequestBody(
                    required = true,
                    description = "Reset password notification payload",
                    content = @Content(schema = @Schema(implementation = VerifyNotificationRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reset password notification processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid reset password notification payload"
            )
    })
    @PostMapping("/send/reset-password")
    void sendResetPasswordEmail(@Valid @RequestBody VerifyNotificationRequest request);

    @Operation(
            summary = "Send order opened notification",
            description = "Sends an order-opened email to all provided recipients",
            requestBody = @RequestBody(
                    required = true,
                    description = "Order-opened notification payload",
                    content = @Content(schema = @Schema(implementation = OrderNotificationRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Order-opened notification processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid order-opened notification payload"
            )
    })
    @PostMapping("/send/order-opened")
    void sendOrderOpenedEmail(@Valid @RequestBody OrderNotificationRequest request);

    @Operation(
            summary = "Send welcome notification",
            description = "Sends a welcome email to a single user",
            requestBody = @RequestBody(
                    required = true,
                    description = "Welcome notification payload",
                    content = @Content(schema = @Schema(implementation = WelcomeNotificationRequest.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Welcome notification processed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid welcome notification payload"
            )
    })
    @PostMapping("/send/welcome")
    void sendWelcomeEmail(@Valid @RequestBody WelcomeNotificationRequest request);

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
            @Valid @RequestBody SendNotificationSettingsRequest request
    );

}
