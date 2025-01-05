package org.youcode.trackprocraservice.domain.embeddables;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Comments {
    private LocalDateTime jour;
    private String text;
}
