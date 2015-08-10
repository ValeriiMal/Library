package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.valmal.bean.Record;
import org.valmal.dao.RecordDao;

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
}
