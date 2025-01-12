package org.youcode.trackprocraservice.web.vm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.domain.entities.InvoicingCurrency;
import org.youcode.trackprocraservice.web.vm.Company.CompanyResponseVM;
import org.youcode.trackprocraservice.web.vm.Company.CompanyVM;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyResponseVM;
import org.youcode.trackprocraservice.web.vm.InvoicingCurrency.InvoicingCurrencyVM;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyVM vm);

    CompanyResponseVM toResponseVM(Company entity);

}
