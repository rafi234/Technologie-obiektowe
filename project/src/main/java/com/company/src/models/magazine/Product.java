package com.company.src.models.magazine;

import com.company.src.models.individual.productFlyweight.FlyweightProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private FlyweightProduct productFlyweight;

    public Product(FlyweightProduct flyweight) {
        productFlyweight = flyweight;
    }

    public Product getProduct() {
        return this;
    }
}