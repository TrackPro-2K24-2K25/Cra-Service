package org.youcode.trackprocraservice.web.vm.InvoicingConditions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoicingConditionsVM {

    @NotNull(message = "PaymentTerm ID cannot be null")
    private UUID paymentTermId;  // Reference to PaymentTerm

    @PositiveOrZero(message = "Discount must be a positive number or zero")
    private double discount;

    @PositiveOrZero(message = "Days for discount must be a positive number or zero")
    private int daysForDiscount;

    @PositiveOrZero(message = "Late fee rate must be a positive number or zero")
    private double lateFeeRate;

    private boolean isActive;
}
