package com.company.src.models.individual.productFlyweight;

import java.util.ArrayList;
import java.util.HashMap;

public class FlyweightProductFactory {
    private static HashMap<Integer, FlyweightProduct> flyweights = new HashMap<>();

    public static FlyweightProduct getFlyweight(int code, String name, double priceNet, double capacity) {
        FlyweightProduct flyweightProduct = flyweights.get(code);

        if (flyweightProduct == null) {
            flyweightProduct = new FlyweightProduct(code, capacity, name, priceNet);
            flyweights.put(code, flyweightProduct);
        }
        return flyweightProduct;
    }

    public static FlyweightProduct getFlyweight(int code) {
        return flyweights.get(code);
    }

    public static ArrayList<FlyweightProduct> getAllNameProducts() {
        return new ArrayList<>(flyweights.values());
    }


}

