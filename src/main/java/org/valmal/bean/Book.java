package org.valmal.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String authors;
    private String genre;
    private String year;
    private int amount;
    private boolean isScarce;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "books_readers",
            joinColumns = { @JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "reader_id", referencedColumnName = "id")})
    private Set<Reader> readers = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Set<Reader> getReaders() {
        if(readers == null){
            readers = new HashSet<>();
        }
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    public boolean isScarce() {
        return isScarce;
    }

    public void setIsScarce(boolean isScarce) {
        this.isScarce = isScarce;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + getId() + "</td>" +
                "<td>" + getTitle() + "</td>" +
                "<td>" + getAuthors() + "</td>" +
                "<td>" + getYear() + "</td>" +
                "<td>" + getGenre() + "</td>" +
                "</tr>";
    }
}
