package org.valmal.bean;

import javax.persistence.*;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    @OneToOne
    private Book book;
    @OneToOne
    private Reader reader;
    private boolean checked;
    private String returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.getId() + "</td>" +
                "<td>" + this.getDate() + "</td>" +
                "<td>" + this.getBook().getTitle() + "</td>" +
                "<td>" + this.getReader().getfName() + " " + this.getReader().getmName() + " " + this.getReader().getlName() + "</td>" +
                "<td>" + this.isChecked() + "</td>" +
                "<td>" + this.getReturnDate() + "</td>" +
                "</tr>";
    }
}
