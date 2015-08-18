package org.valmal.domain;

import java.util.Date;

public class PreRecord {
    private int id;
    private int book_id;
    private int reader_id;
    private Date date_from;
    private Date date_to;
    private Date return_from;
    private Date return_to;
    private String returned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Date getReturn_from() {
        return return_from;
    }

    public void setReturn_from(Date return_from) {
        this.return_from = return_from;
    }

    public Date getReturn_to() {
        return return_to;
    }

    public void setReturn_to(Date return_to) {
        this.return_to = return_to;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
}
