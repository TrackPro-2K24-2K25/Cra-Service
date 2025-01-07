package org.youcode.trackprocraservice.web.vm.PaymentTerm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTermVM {

    @NotBlank(message = "Value cannot be blank")
    private String value;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    private String description;

    @Min(value = 0, message = "Days must be a non-negative number")
    private int days;

    private boolean isDefault;

    private boolean isActive;
}
