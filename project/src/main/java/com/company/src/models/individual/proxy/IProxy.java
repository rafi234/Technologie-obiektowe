package com.company.src.models.individual.proxy;

public interface IProxy {
    public boolean addEmployeeToContainer(String name,
                                       String surname,
                                       String phoneNumber,
                                       String email,
                                       String street,
                                       String city,
                                       String voivodeship,
                                       String postalCode,
                                       String country,
                                       String salary);
}
