package org.youcode.trackprocraservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum TimeUnit {

    DAY("DAY"),
    HALF_DAY("HALF_DAY"),
    QUARTER_DAY("QUARTER_DAY");

    private final String label;
    TimeUnit(String label) {
        this.label = label;
    }

}
