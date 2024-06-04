package com.projekt.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FlatAmounts")
public class FlatAmounts {
    @Id
    @GeneratedValue
    private Integer id;
    private String region;
    private String quarter;
    private String type;
    private String surface;
    private Integer year;
    private Integer amount;
}
