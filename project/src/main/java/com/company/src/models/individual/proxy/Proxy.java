package com.company.src.models.individual.proxy;

import com.company.src.models.individual.Employee;
import com.company.src.models.individual.PeopleContainer;

import java.util.Date;

public class Proxy implements IProxy {
    @Override
    public boolean addEmployeeToContainer(
            String name,
            String surname,
            String phoneNumber,
            String email,
            String street,
            String city,
            String voivodeship,
            String postalCode,
            String country,
            String salary
    ) {
        name = convertLine(name);
        surname = convertLine(surname);
        street = convertLine(street);
        city = convertLine(city);
        voivodeship = convertLine(voivodeship);
        country = convertLine(country);

        int parsedPhoneNumber = 0;
        double parsedSalary = 0;
        try {
            parsedPhoneNumber = Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            System.out.println("Phone number must consist of integers!");
            return false;
        }

        try {
            salary = salary.replace(",", ".");
            parsedSalary = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            System.out.println("Salary must be a number");
            return false;
        }
        PeopleContainer.getPeopleContainer().personContainer.add(
                new Employee(
                        name,
                        surname,
                        parsedPhoneNumber,
                        email,
                        street,
                        city,
                        voivodeship,
                        postalCode,
                        country,
                        parsedSalary,
                        new Date()
                )
        );
        return true;
    }

    private String convertLine(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
