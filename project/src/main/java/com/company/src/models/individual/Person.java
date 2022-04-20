package com.company.src.models.individual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    protected int id;
    protected String name;
    protected String surname;
    protected int phoneNumber;
    protected String email;
    protected Address address;

    public Person(
            String name,
            String surname,
            int phoneNumber,
            String email,
            String street,
            String city,
            String voivodeship,
            String postalCode,
            String country
    ) {
        this.id = IdGenerator.getID();
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = new Address(
                street,
                city,
                voivodeship,
                postalCode,
                country);
    }

    @Override
    public String toString() {
        return "Person";
    }
}
