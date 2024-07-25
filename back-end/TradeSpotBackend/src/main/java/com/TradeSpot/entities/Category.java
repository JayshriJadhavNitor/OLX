package com.TradeSpot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Category extends BaseEntity{

    private String name;
    private String categoryImgPath;

    @OneToMany(mappedBy = "category")
    List<Product> products=new ArrayList<>();

}
