package org.valmal.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    private int id;
    private String country;
    private String city;
    private String street;
    private String house;
    private Set<Reader> readers = new HashSet<>();

    public Address() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "house")
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address")
    public Set<Reader> getReaders() {
        return this.readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return getCountry() + ", " + getCity() + ", " + getStreet() + ", " + getHouse();
    }
}
