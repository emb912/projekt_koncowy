package com.projekt.security.models;

import com.projekt.security.entities.FlatAmounts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlatAmountsDto {
    private Integer id;
    private String region;
    private String quarter;
    private String type;
    private String surface;
    private Integer year;
    private Integer amount;

    public FlatAmountsDto(FlatAmounts flatamounts) {
        this.id = flatamounts.getId();
        this.region = flatamounts.getRegion();
        this.quarter = flatamounts.getQuarter();
        this.type = flatamounts.getType();
        this.surface = flatamounts.getSurface();
        this.year = flatamounts.getYear();
        this.amount = flatamounts.getAmount();
    }
}
