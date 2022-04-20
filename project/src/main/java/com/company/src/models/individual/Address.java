package com.company.src.models.individual;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private String city;
    private String voivodeship;
    private String postalCode;
    private String country;

    public Address(
            String street,
            String city,
            String voivodeship,
            String postalCode,
            String country
    ) {
        this.street = street;
        this.city = city;
        this.voivodeship = voivodeship;
        this.postalCode = postalCode;
        this.country = country;
    }

}
