package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.bean.Record;
import org.valmal.dao.RecordDao;
import org.valmal.domain.PreRecord;

import java.util.Date;
import java.util.List;

@Repository
public class RecordDaoImpl implements RecordDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insert(Record record) {
        sessionFactory.getCurrentSession()
                .save(record);
    }

    @Override
    public List<Record> getRecords() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .addOrder(Order.desc("id"))
//                .setMaxResults(100)
                .list();
    }

    @Override
    public Record findRecordById(int id) {
        return (Record) sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void update(Record record) {
        sessionFactory.getCurrentSession()
                .update(record);
    }

    @Override
    public List<Record> getRecordsBetweenDates(String date1, String date2) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.between("date", date1, date2))
                .setMaxResults(100)
                .list();
    }

    @Override
    public List<Record> getRecordsBetweenDates(Date date1, Date date2) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.between("date", date1, date2))
                .list();
    }

    @Override
    public List<Record> getRecordsByExample(Record example) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.eq("id", example.getId()))
                .add(Restrictions.or(Restrictions.eq("book", example.getBook())))
//                .add(Restrictions.eq("reader", example.getReader()))
//                .add(Restrictions.eq("checked", example.isChecked()))
                .setMaxResults(100)
                .list();
    }

    @Override
    public Date getRecordsFirstDate() {
        return (Date) sessionFactory.getCurrentSession()
                .createSQLQuery("select MIN(" + "date" + ") from records;")
                .uniqueResult();
    }

    @Override
    public List<Record> getRecordsByPreExample(PreRecord preExample, Book book, Reader reader) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.eq("id", preExample.getId()))
                .add(Restrictions.eq("book", book))
                .add(Restrictions.eq("reader", reader))
                .add(Restrictions.between("date", preExample.getDate_from(), preExample.getDate_to()))
                .add(Restrictions.between("returnDate", preExample.getReturn_from(), preExample.getReturn_to()))
                .setMaxResults(100)
                .list();
    }
}
