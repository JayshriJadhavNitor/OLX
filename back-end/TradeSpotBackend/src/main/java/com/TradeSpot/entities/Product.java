package com.TradeSpot.entities;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Product extends BaseEntity {

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "addedDate")
    private LocalDate addedDate;

    @Column(name = "imagePath")
    private String productImgPath;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<BroughtItems> productBroughtItemsList=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;




    private boolean isActive;


    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", addedDate=" + addedDate +
                ", productImgPath='" + productImgPath + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
