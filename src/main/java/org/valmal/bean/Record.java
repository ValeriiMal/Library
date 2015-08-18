package org.valmal.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @OneToOne
    private Book book;
    @OneToOne
    private Reader reader;
    private boolean checked = false;
    private Date returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.getId() + "</td>" +
                "<td>" + this.getDate().toString() + "</td>" +
                "<td>" + this.getBook().getTitle() + "</td>" +
                "<td>" + this.getReader().getfName() + " " + this.getReader().getmName() + " " + this.getReader().getlName() + "</td>" +
                "<td>" + this.isChecked() + "</td>" +
                "<td>" + this.getReturnDate() + "</td>" +
                "</tr>";
    }
}
