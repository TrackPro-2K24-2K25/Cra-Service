package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractResponseVM;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractVM;
import org.youcode.trackprocraservice.web.vm.User.AppUserResponseVM;
import org.youcode.trackprocraservice.web.vm.User.AppUserVM;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toEntity(AppUserVM vm);

    AppUserResponseVM toResponseVM(AppUser entity);
}
