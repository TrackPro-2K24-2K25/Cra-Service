package org.youcode.trackprocraservice.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.trackprocraservice.domain.entities.BankAccount;
import org.youcode.trackprocraservice.domain.entities.File;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountResponseVM;
import org.youcode.trackprocraservice.web.vm.BankAccount.BankAccountVM;
import org.youcode.trackprocraservice.web.vm.File.FileResponseVM;
import org.youcode.trackprocraservice.web.vm.File.FileVM;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    File toEntity(FileVM vm);

    FileResponseVM toResponseVM(File entity);

}
