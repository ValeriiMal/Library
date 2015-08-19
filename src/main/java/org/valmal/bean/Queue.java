package org.valmal.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="queues")
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @OneToOne
    private Book book;
    @OneToOne
    private Reader reader;

    public Queue() {
    }

    public Queue(Date date, Book book, Reader reader) {
        this.date = date;
        this.book = book;
        this.reader = reader;
    }

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
}
