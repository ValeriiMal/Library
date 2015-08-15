package org.valmal.bean;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity()
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fName;
    private String mName;
    private String lName;
    private String phone;
    private Date registrationDate;
    private Date dateOfBirth;
    @Embedded
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();


    public Reader() {
    }

    public static Reader getEmptyReader(){
        Reader reader = new Reader();
        reader.fName = "";
        reader.mName = "";
        reader.lName = "";
        reader.phone = "";
        return reader;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Book> getBooks() {
        if(books == null){
            books = new HashSet<>();
        }
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + getId() + "</td>" +
                "<td>" + getfName() + "</td>" +
                "<td>" + getmName() + "</td>" +
                "<td>" + getlName() + "</td>" +
                "<td>" + getPhone() + "</td>" +
                "</tr>"
                ;
    }
}
