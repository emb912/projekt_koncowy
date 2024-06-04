package com.projekt.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "FlatPrices")
public class FlatPrices {
    @Id
    @GeneratedValue
    private Integer id;
    private String region;
    private String quarter;
    private String type;
    private String surface;
    private Integer year;
    private Integer price;

}
