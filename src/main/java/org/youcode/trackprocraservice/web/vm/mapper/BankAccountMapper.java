package org.youcode.trackprocraservice.web.vm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.domain.entities.Company;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountResponseVM;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountVM;
import org.youcode.trackprocraservice.web.vm.Company.CompanyResponseVM;
import org.youcode.trackprocraservice.web.vm.Company.CompanyVM;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount toEntity(BankAccountVM vm);

    BankAccountResponseVM toResponseVM(BankAccount entity);

}
