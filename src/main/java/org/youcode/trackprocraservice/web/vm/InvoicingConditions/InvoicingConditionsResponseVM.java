package org.youcode.trackprocraservice.web.vm.InvoicingConditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoicingConditionsResponseVM {

    private UUID paymentTermId;
    private double discount;
    private int daysForDiscount;
    private double lateFeeRate;
    private boolean isActive;
}
