package org.youcode.trackprocraservice.web.vm.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseVM {

    private String name;
    private String contentType;
    private String url;
}
