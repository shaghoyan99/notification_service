package am.agro_trade.notification_service.mapper;

import am.agro_trade.notification_service.dto.NotificationSettingsDTO;
import am.agro_trade.notification_service.model.NotificationSettings;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationSettingsMapper {

    NotificationSettings toEntity(NotificationSettingsDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(NotificationSettingsDTO dto,
                      @MappingTarget NotificationSettings entity);
}