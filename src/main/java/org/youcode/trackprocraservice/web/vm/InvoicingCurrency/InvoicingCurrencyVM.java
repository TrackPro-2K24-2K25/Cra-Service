package org.youcode.trackprocraservice.web.vm.InvoicingCurrency;


import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicingCurrencyVM {

    private String id;

    @NotBlank(message = "Currency value cannot be blank")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Currency value must be a 3-letter uppercase code (ISO 4217 standard)"
    )
    private String value;
}
