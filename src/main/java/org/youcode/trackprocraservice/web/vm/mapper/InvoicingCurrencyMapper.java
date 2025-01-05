package org.youcode.trackprocraservice.web.vm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.Mapping;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyResponseVM;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyVM;

@Mapper(componentModel = "spring")
public interface InvoicingCurrencyMapper {

    InvoicingCurrency toEntity(InvoicingCurrencyVM vm);

    InvoicingCurrencyResponseVM toResponseVM(InvoicingCurrency entity);

 }

