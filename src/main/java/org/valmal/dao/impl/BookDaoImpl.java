package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Book;
import org.valmal.dao.BookDao;

import java.util.ArrayList;
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
                .createSQLQuery("select * from books order by id desc limit 100")
                .addEntity(Book.class)
//                .createCriteria(Book.class)
//                .setMaxResults(10)
//                .addOrder(Order.desc("id"))
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
        List<Book> books;
        String query;
        if (example.getId() == 0) {
            query =
                    "select * from books where " +
                            "title like '%" + example.getTitle() + "%' and " +
                            "authors like '%" + example.getAuthors() + "%' and " +
                            "year like '%" + example.getYear() + "%' and " +
                            "genre like '%" + example.getGenre() + "%' " +
                            "order by id desc limit 100;";
        } else {
            query =
                    "select * from books where " +
                            "id = " + example.getId() + " and " +
                            "title like '%" + example.getTitle() + "%' and " +
                            "authors like '%" + example.getAuthors() + "%' and " +
                            "year like '%" + example.getYear() + "%' and " +
                            "genre like '%" + example.getGenre() + "%' " +
                            "order by id desc limit 100;";
        }

        books = sessionFactory.getCurrentSession()
                .createSQLQuery(query).addEntity(Book.class)
                .list();

        return books;
    }

    @Override
    public Integer readersCountById(int i) {
        return null;
    }
}
