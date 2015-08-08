package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.valmal.bean.Book;
import org.valmal.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Book book) {
        sessionFactory.getCurrentSession()
                .save(book);
    }

    @Override
    public void update(Book book) {
        sessionFactory.getCurrentSession()
                .update(book);
    }
}
