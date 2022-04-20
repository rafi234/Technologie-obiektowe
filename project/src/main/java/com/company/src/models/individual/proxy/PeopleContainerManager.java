package com.company.src.models.individual.proxy;

import com.company.src.models.individual.Employee;
import com.company.src.models.individual.Person;

import java.util.ArrayList;
import java.util.Date;

public class PeopleContainerManager implements IProxy {
    public ArrayList<Person> personContainer = getFewPersonForTest();

    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> employees = new ArrayList<>();
        for (Person p : personContainer) {
            if (p instanceof Employee)
                employees.add((Employee) p);
        }
        return employees;
    }

    public String deleteEmployeeFromContainer(int id) {
        if (personContainer.removeIf(p -> p.getId() == id))
            return "User with id " + id + " has been fired!";
        return "ERROR: User with id " + id + " does not exist!";
    }

    public ArrayList<Person> findEmployee(String criterion, String value) {
        ArrayList<Person> people = new ArrayList<>();
        switch (criterion) {
            case "ID":
                for (Person p : personContainer)
                    if (p.getId() == Integer.parseInt(value))
                        people.add(p);
                break;
            case "Name":
                for (Person p : personContainer)
                    if (p.getName().equals(value))
                        people.add(p);
                break;
            case "Surname":
                for (Person p : personContainer)
                    if (p.getSurname().equals(value))
                        people.add(p);

        }
        return people;
    }

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
            String salary) {

        int parsedPhoneNumber = 0;
        double parsedSalary = 0.;

        try {
            parsedPhoneNumber = Integer.parseInt(phoneNumber);
            parsedSalary = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            return false;
        }

        personContainer.add(
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
                        new Date())
        );
    return true;
    }

    private ArrayList<Person> getFewPersonForTest() {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Employee("Rafał",
                "Faliński",
                500445836,
                "rafi_222@wp.pl",
                "Lwowska",
                "Nowy Sącz",
                "Małopolskie",
                "33-300",
                "Poland",
                3430,
                new Date())
        );

        people.add(new Employee("Tomek",
                "Myszka",
                345234654,
                "tomasz2@wp.pl",
                "Sucharskiego",
                "Nowy Sącz",
                "Małopolskie",
                "33-300",
                "Poland", 5130,
                new Date())
        );

        people.add(new Person("Rafał",
                        "Faliński",
                        500445836,
                        "rafi_222@wp.pl",
                        "Lwowska",
                        "Nowy Sącz",
                        "Małopolskie",
                        "33-300",
                        "Poland"
                )
        );

        return people;
    }
}
