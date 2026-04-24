package am.agro_trade.notification_service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth")
public record AuthProperties(
        String baseUrl,
        String verifyPath,
        String resendCodePath
) {
}