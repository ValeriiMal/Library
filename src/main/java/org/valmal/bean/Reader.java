package org.valmal.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "readers")
public class Reader {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "mName")
    private String mName;
    @Column(name = "lName")
    private String lName;
    @Column(name = "phone")
    private String phone;
    private Address address;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + getId() + "</td>" +
                "<td>" + getfName() + "</td>" +
                "<td>" + getmName() + "</td>" +
                "<td>" + getlName() + "</td>" +
                "<td>" + getPhone() + "</td>" +
                "<td>" + "some address" + "</td>" +
                "</tr>"
                ;
    }
}
