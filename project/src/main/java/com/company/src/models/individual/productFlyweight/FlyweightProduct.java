package com.company.src.models.individual.productFlyweight;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class FlyweightProduct implements IFlyweight {
    private int code;
    private double capacity;
    private String name;
    private double priceNet;


    public FlyweightProduct(int code, double capacity, String name, double priceNet) {
        this.code = code;
        this.capacity = capacity;
        this.name = name;
        this.priceNet = priceNet;
    }

    @Override
    public FlyweightProduct getData() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IFlyweight that = (FlyweightProduct) o;
        return that.getData().equals(this.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        return code + " " + name;
    }
}
