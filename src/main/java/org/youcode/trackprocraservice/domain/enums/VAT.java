package org.youcode.trackprocraservice.domain.enums;

import lombok.Getter;

@Getter
public enum VAT {
    STANDARD(20),         // 20% VAT
    REDUCED(5_5),         // 5.5% VAT
    SUPER_REDUCED(2_1),   // 2.1% VAT
    INTERMEDIATE(10);     // 10% VAT


    private final double rate;

    VAT(double rate) {
        this.rate = rate;
    }

}
