package org.valmal.dao;

import org.valmal.bean.Book;

public interface BookDao {
    void insert(Book book);
    void update(Book book);
}
