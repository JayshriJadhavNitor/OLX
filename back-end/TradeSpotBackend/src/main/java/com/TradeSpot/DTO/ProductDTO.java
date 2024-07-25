package com.TradeSpot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor

@Getter
@Setter
public class ProductDTO {

    private String productName;

    private String description;

    private double price;

    private LocalDate addedDate;

    private boolean isActive;

    public ProductDTO(String productName, String description, double price, LocalDate addedDate) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.addedDate = addedDate;
        this.isActive=true;
    }
}
