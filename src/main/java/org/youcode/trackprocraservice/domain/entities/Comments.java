package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.Embeddable;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
public class Comments {
    private LocalDateTime jour;
    private String text;
}
