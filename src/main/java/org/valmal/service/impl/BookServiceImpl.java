package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Book;
import org.valmal.dao.BookDao;
import org.valmal.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    @Transactional
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookDao.update(book);
    }
}
