package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.youcode.trackprocraservice.domain.entities.PaymentTerm;
import org.youcode.trackprocraservice.web.vm.PaymentTerm.PaymentTermResponseVM;
import org.youcode.trackprocraservice.web.vm.PaymentTerm.PaymentTermVM;

@Mapper(componentModel = "spring")
public interface PaymentTermMapper {

    PaymentTerm toEntity(PaymentTermVM vm);

    PaymentTermResponseVM toResponseVM(PaymentTerm entity);

}
