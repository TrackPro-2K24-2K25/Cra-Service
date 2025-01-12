package org.youcode.trackprocraservice.web.vm.BankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponseVM {

    private String accountNumber;
    private String bankName;
    private String iban;
    private String bic;
}