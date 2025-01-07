package org.youcode.trackprocraservice.web.vm.PaymentTerm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTermResponseVM {
    private String value;
    private String description;
    private int days;
    private boolean isDefault;
    private boolean isActive;
}
