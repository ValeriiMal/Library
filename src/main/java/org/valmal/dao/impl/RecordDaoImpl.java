package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.valmal.bean.Record;
import org.valmal.dao.RecordDao;

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
                .setMaxResults(10)
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
                .setMaxResults(10)
                .list();
    }

    @Override
    public List<Record> getRecordsBetweenDates(Date date1, Date date2) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Record.class)
                .add(Restrictions.between("date", date1, date2))
                .setMaxResults(10)
                .list();
    }
}
