package com.company.src.models.individual;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee extends Person {

    private double salary;
    private Date dateOfEmployment;

    public Employee(
            String name,
            String surname,
            int phoneNumber,
            String email,
            String street,
            String city,
            String voivodeship,
            String postalCode,
            String country,
            double salary,
            Date date) {
        super(
                name,
                surname,
                phoneNumber,
                email,
                street,
                city,
                voivodeship,
                postalCode,
                country
        );

        this.salary = salary;
        this.dateOfEmployment = date;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname;
    }
}
