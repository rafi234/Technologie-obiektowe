package com.company.pyłek;

import com.company.posrednik.Officer;

import java.util.HashMap;

public class FlyweightSurnameFactory {
    public static HashMap<String, IFlyweight> surnameFactory = new HashMap<>();

    public static void getSurnameFlyweight(String surname, SurnameBuilder surnameBuilder) {
        if(surnameFactory.containsKey(surname)){
            surnameFactory.get(surname);
        }
        else {
            String[] separatedSurname = splitSurname(surname);
            if(separatedSurname.length == 2)
                getSurnameFlyweight(separatedSurname[1], surnameBuilder);
            IFlyweight newFlyweight = new SurnameFlyweight(Officer.convertLine(separatedSurname[0]));
            surnameFactory.put(separatedSurname[0], newFlyweight);
            surnameBuilder.addSurnamePart(newFlyweight);
        }
    }

    private static String[] splitSurname(String surname){
        return surname.split(" ", 2);
    }
}
