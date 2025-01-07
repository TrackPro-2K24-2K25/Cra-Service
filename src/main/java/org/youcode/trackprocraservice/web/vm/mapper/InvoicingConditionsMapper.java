package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.youcode.trackprocraservice.domain.entities.InvoicingConditions;
import org.youcode.trackprocraservice.web.vm.InvoicingConditions.InvoicingConditionsResponseVM;
import org.youcode.trackprocraservice.web.vm.InvoicingConditions.InvoicingConditionsVM;

@Mapper(componentModel = "spring")
public interface InvoicingConditionsMapper {

    InvoicingConditionsMapper INSTANCE = Mappers.getMapper(InvoicingConditionsMapper.class);

    @Mapping(target = "paymentTerm.id", source = "paymentTermId")
    InvoicingConditions toEntity(InvoicingConditionsVM vm);

    @Mapping(target = "paymentTermId", source = "paymentTerm.id")
    InvoicingConditionsResponseVM toResponseVM(InvoicingConditions entity);
}