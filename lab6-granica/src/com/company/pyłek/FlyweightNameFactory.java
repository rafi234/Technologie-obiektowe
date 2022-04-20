package com.company.pyłek;

import java.util.HashMap;

public class FlyweightNameFactory {
    public static HashMap<String, IFlyweight> nameFactory = new HashMap<>();

    public static void getNameFlyweight(String name) {
        if (nameFactory.containsKey(name)) {
            nameFactory.get(name);
        } else {
            NameFlyweight newNameFlyweight = new NameFlyweight(name);
            nameFactory.put(name, newNameFlyweight);
        }
    }
}
