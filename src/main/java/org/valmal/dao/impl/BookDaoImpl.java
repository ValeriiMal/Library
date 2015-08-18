package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Book;
import org.valmal.dao.BookDao;

import java.util.List;

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

    @Override
    public Book findBookById(int id) {
        return (Book) sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void delete(Book book) {
        sessionFactory.getCurrentSession()
                .delete(book);
    }

    @Override
    public List<Book> getBooks() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .setMaxResults(10)
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.like("title", "%" + title + "%"))
                .addOrder(Order.asc("title"))
                .setMaxResults(10)
                .list();
    }

    @Override
    public List<Book> findBooksByAuthors(String authors) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.like("authors", "%" + authors + "%"))
                .addOrder(Order.asc("authors"))
                .setMaxResults(10)
                .list();
    }

    @Override
    public List<Book> findBooksByYear(String year) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.like("year", "%" + year + "%"))
                .addOrder(Order.asc("year"))
                .setMaxResults(10)
                .list();
    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.like("genre", "%" + genre + "%"))
                .addOrder(Order.asc("genre"))
                .setMaxResults(10)
                .list();
    }

    @Override
    public List<Book> findBooksByExample(Book example) {
        if (example.getId() == 0) {
            return sessionFactory.getCurrentSession()
                    .createCriteria(Book.class)
                    .add(Restrictions.like("title", "%" + example.getTitle() + "%"))
                    .add(Restrictions.like("authors", "%" + example.getAuthors() + "%"))
                    .add(Restrictions.like("year", "%" + example.getYear() + "%"))
                    .add(Restrictions.like("genre", "%" + example.getGenre() + "%"))
                    .setMaxResults(100)
                    .list();
        }
        return sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .add(Restrictions.eq("id", example.getId()))
                .add(Restrictions.like("title", "%" + example.getTitle() + "%"))
                .add(Restrictions.like("authors", "%" + example.getAuthors() + "%"))
                .add(Restrictions.like("year", "%" + example.getYear() + "%"))
                .add(Restrictions.like("genre", "%" + example.getGenre() + "%"))
                .setMaxResults(100)
                .list();
    }

    @Override
    public Integer readersCountById(int i) {
        return null;
    }
}
