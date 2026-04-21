package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import am.agro_trade.notification_service.dto.request.SendNotificationRequest;
import am.agro_trade.notification_service.exception.NotificationSettingsException;
import am.agro_trade.notification_service.model.NotificationSettings;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.repository.NotificationSettingsRepository;
import am.agro_trade.notification_service.service.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSettingsServiceImpl implements NotificationSettingsService {

    private final NotificationSettingsRepository repository;
    private final SendEmailImpl emailService;


    public void save(NotificationSettingsDTO notificationSettings) {

        if (notificationSettings == null) {
            throw new NotificationSettingsException("NotificationSettingsDTO cannot be null");
        }

        if (notificationSettings.getUserId() <= 0) {
            throw new NotificationSettingsException("User ID must be a positive number");
        }

        String email = notificationSettings.getEmail();

        if (email == null || email.trim().isEmpty()) {
            throw new NotificationSettingsException("Email cannot be null or empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new NotificationSettingsException("Invalid email format");
        }

        NotificationSettings settings = repository
                .findByUserId(notificationSettings.getUserId())
                .orElse(new NotificationSettings());

        settings.setUserId(notificationSettings.getUserId());
        settings.setEmail(email);
        settings.setEmailEnabled(notificationSettings.getEmailEnabled());
        settings.setSmsEnabled(notificationSettings.getSmsEnabled());
        settings.setInAppEnabled(notificationSettings.getInAppEnabled());

        repository.save(settings);
    }

    public void sendNotification(SendNotificationRequest request) {

        List<NotificationSettings> settingsList =
                repository.findAllByUserIdIn(request.userIds());

        if (settingsList.isEmpty()) {
            log.warn("No notification settings found for userIds={}", request.userIds());
            return;
        }
        List<String> emails = settingsList.stream()
                .filter(NotificationSettings::isEmailEnabled)
                .map(NotificationSettings::getEmail)
                .filter(Objects::nonNull)
                .toList();
        if (emails.isEmpty()) {
            log.warn("No enabled emails for userIds={}", request.userIds());
            return;
        }

        if (request.emailType() == EmailType.VERIFY ||
                request.emailType() == EmailType.RESET_PASSWORD) {
            emailService.sendMail(
                    emails.getFirst(),
                    request.code(),
                    safeUrl(request),
                    safeProductName(request),
                    request.emailType()
            );
            return;
        }

        if (request.emailType() == EmailType.ORDER_OPENED) {
            for (String email : emails) {
                emailService.sendMail(
                        email,
                        request.code(),
                        safeUrl(request),
                        safeProductName(request),
                        request.emailType()
                );
            }
            return;
        }

        emailService.sendMail(
                emails.getFirst(),
                request.code(),
                safeUrl(request),
                safeProductName(request),
                request.emailType()
        );
    }

    private String safeUrl(SendNotificationRequest request) {
        return request.orderOpenedDto() != null
                ? request.orderOpenedDto().orderUrl()
                : null;
    }

    private String safeProductName(SendNotificationRequest request) {
        return request.orderOpenedDto() != null
                ? request.orderOpenedDto().productName()
                : null;
    }


}
