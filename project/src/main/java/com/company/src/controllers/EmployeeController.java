package com.company.src.controllers;

import com.company.src.models.individual.PeopleContainer;
import com.company.src.models.individual.Person;
import com.company.src.models.individual.proxy.IProxy;
import com.company.src.models.individual.proxy.Proxy;
import com.company.src.views.EmployeeViewManager;

import java.util.ArrayList;

public class EmployeeController {

    private final IProxy proxy = new Proxy();
    public static ArrayList<Person> foundPeople = new ArrayList<>();

    public void addEmployee(
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
        if (name.isEmpty()
                || surname.isEmpty()
                || email.isEmpty()
                || phoneNumber.isEmpty()
                || street.isEmpty()
                || city.isEmpty()
                || voivodeship.isEmpty()
                || postalCode.isEmpty()
                || country.isEmpty()
                || salary.isEmpty()
        ) {
            System.out.println("Data is missing!");
        } else {
            saveEmployee(name, surname, phoneNumber, email, street, city, voivodeship, postalCode, country, salary);
        }
    }

    private void saveEmployee(
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
        if (proxy.addEmployeeToContainer(
                name,
                surname,
                phoneNumber,
                email,
                street,
                city,
                voivodeship,
                postalCode,
                country,
                salary
        )
        ) {
            System.out.println("New employee has been added");
        } else
            System.out.println("Failed to add new employee!");
    }

    public void findEmployee(String userData, String criterion) {
        if (userData.isEmpty() || criterion.isEmpty())
            System.out.println("Fulfill whole form!");
        else
            find(userData, criterion);
    }

    private void find(String userData, String criterion) {
        String data = criterion.trim();
        String checkedCriterion = data.equals("ID") ? "ID" : data.equals("Name") ? "Name" : "Surname";
        foundPeople = PeopleContainer.getPeopleContainer().findEmployee(checkedCriterion, userData);
        if(foundPeople.size() == 0) {
            System.out.println("User with this " + criterion + " does not exist!");
            return;
        }
        EmployeeViewManager.isFoundListOfPeopleOnDisplay.setSelected(true);
    }
}
