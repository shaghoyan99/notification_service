package am.agro_trade.notification_service.service.impl;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import am.agro_trade.notification_service.dto.request.OrderNotificationRequest;
import am.agro_trade.notification_service.dto.request.VerifyNotificationRequest;
import am.agro_trade.notification_service.dto.request.WelcomeNotificationRequest;
import am.agro_trade.notification_service.mapper.NotificationSettingsMapper;
import am.agro_trade.notification_service.model.NotificationSettings;
import am.agro_trade.notification_service.model.enums.EmailType;
import am.agro_trade.notification_service.repository.NotificationSettingsRepository;
import am.agro_trade.notification_service.service.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSettingsServiceImpl implements NotificationSettingsService {

    private final NotificationSettingsRepository repository;
    private final SendEmailImpl emailService;
    private final NotificationSettingsMapper notificationSettingsMapper;


    public void save(NotificationSettingsDTO notificationSettings) {

        NotificationSettings settings = repository
                .findByUserId(notificationSettings.getUserId())
                .orElse(new NotificationSettings());

        notificationSettingsMapper.updateEntity(notificationSettings, settings);

        repository.save(settings);
    }

    @Override
    public void sendVerificationNotification(VerifyNotificationRequest request) {
        sendSingleRecipientNotification(Collections.singletonList(request.userId()), request.code(), EmailType.VERIFY);
    }

    @Override
    public void sendResetPasswordNotification(VerifyNotificationRequest request) {
        sendSingleRecipientNotification(Collections.singletonList(request.userId()), request.code(), EmailType.RESET_PASSWORD);
    }

    @Override
    public void sendOrderOpenedNotification(OrderNotificationRequest request) {
        List<String> emails = findEnabledEmails(request.userIds());
        if (emails.isEmpty()) {
            return;
        }

        for (String email : emails) {
            emailService.sendMail(
                    email,
                    null,
                    request.orderOpenedDto().orderUrl(),
                    request.orderOpenedDto().productName(),
                    EmailType.ORDER_OPENED
            );
        }
    }

    @Override
    public void sendWelcomeNotification(WelcomeNotificationRequest request) {
        sendSingleRecipientNotification(Collections.singletonList(request.userId()), null, EmailType.WELCOME);
    }

    private void sendSingleRecipientNotification(List<Long> userIds, String code, EmailType emailType) {
        List<String> emails = findEnabledEmails(userIds);
        if (emails.isEmpty()) {
            return;
        }

        emailService.sendMail(
                emails.getFirst(),
                code,
                null,
                null,
                emailType
        );
    }

    private List<String> findEnabledEmails(List<Long> userIds) {
        List<NotificationSettings> settingsList = repository.findAllByUserIdIn(userIds);

        if (settingsList.isEmpty()) {
            log.warn("No notification settings found for userIds={}", userIds);
            return List.of();
        }

        List<String> emails = settingsList.stream()
                .filter(NotificationSettings::isEmailEnabled)
                .map(NotificationSettings::getEmail)
                .filter(Objects::nonNull)
                .toList();

        if (emails.isEmpty()) {
            log.warn("No enabled emails for userIds={}", userIds);
        }

        return emails;
    }
}
