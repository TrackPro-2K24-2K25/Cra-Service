package org.youcode.trackprocraservice.web.vm.File;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FileVM {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Content type is required")
    @Size(max = 100, message = "Content type must be less than 100 characters")
    private String contentType;

    @NotBlank(message = "URL is required")
    @Size(max = 500, message = "URL must be less than 500 characters")
    @URL(message = "URL must be valid")
    private String url;
}
