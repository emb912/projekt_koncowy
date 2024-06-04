package com.projekt.security.models;

import com.projekt.security.entities.FlatPrices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlatPricesDto {
    private Integer id;
    private String region;
    private String quarter;
    private String type;
    private String surface;
    private Integer year;
    private Integer price;

    public FlatPricesDto(FlatPrices flatprices) {
        this.id = flatprices.getId();
        this.region = flatprices.getRegion();
        this.quarter = flatprices.getQuarter();
        this.type = flatprices.getType();
        this.surface = flatprices.getSurface();
        this.year = flatprices.getYear();
        this.price = flatprices.getPrice();
    }
}
