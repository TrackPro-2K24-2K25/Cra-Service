package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.trackprocraservice.domain.entities.Mission;
import org.youcode.trackprocraservice.web.vm.Mission.MissionResponseVM;
import org.youcode.trackprocraservice.web.vm.Mission.MissionVM;


@Mapper(componentModel = "spring")
public interface MissionMapper {

    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    Mission toEntity(MissionVM vm);

    MissionResponseVM toResponseVM(Mission entity);
}
