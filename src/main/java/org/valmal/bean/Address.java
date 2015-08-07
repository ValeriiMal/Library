package org.valmal.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reader_id;

    private String country;

    private String city;

    private String street;

    private String house;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Reader reader;

    public Address() {
    }


    public int getId() {
        return reader_id;
    }

    public void setId(int id) {
        this.reader_id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Reader getReader() {
        return this.reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return getCountry() + ", " + getCity() + ", " + getStreet() + ", " + getHouse();
    }
}
