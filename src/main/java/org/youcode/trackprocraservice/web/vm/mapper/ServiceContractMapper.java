package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.youcode.trackprocraservice.domain.entities.ServiceContract;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractResponseVM;
import org.youcode.trackprocraservice.web.vm.ServiceContract.ServiceContractVM;

@Mapper(componentModel = "spring")
public interface ServiceContractMapper {

    ServiceContract toEntity(ServiceContractVM vm);

    ServiceContractResponseVM toResponseVM(ServiceContract entity);
}