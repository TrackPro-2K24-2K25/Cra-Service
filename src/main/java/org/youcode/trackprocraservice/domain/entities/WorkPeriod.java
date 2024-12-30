package org.youcode.trackprocraservice.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.youcode.trackprocraservice.domain.enums.WorkPeriodType;

@Embeddable
public class WorkPeriod {
    private Integer day;

    @Enumerated(EnumType.STRING)
    private WorkPeriodType period;

}
