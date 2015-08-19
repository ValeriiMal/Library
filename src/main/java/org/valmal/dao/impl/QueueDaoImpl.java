package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.valmal.bean.Queue;
import org.valmal.dao.QueueDao;

import java.util.List;

@Repository
public class QueueDaoImpl implements QueueDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insert(Queue queue) {
        sessionFactory.getCurrentSession()
                .save(queue);
    }

    @Override
    public void update(Queue queue) {
        sessionFactory.getCurrentSession()
                .update(queue);
    }

    @Override
    public void delete(Queue queue) {
        sessionFactory.getCurrentSession()
                .delete(queue);
    }

    @Override
    public List<Queue> getQueues() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Queue.class)
                .addOrder(Order.desc("id"))
                .setMaxResults(100)
                .list();
    }

    @Override
    public Queue findQueueById(int id) {
        return (Queue) sessionFactory.getCurrentSession()
                .createCriteria(Queue.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<Queue> findQueuesByExample(Queue example) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Queue.class)
                .add(Restrictions.eq("id", example.getId()))
                .add(Restrictions.eq("book", example.getBook()))
                .add(Restrictions.eq("reader", example.getReader()))
                .setMaxResults(100)
                .list();
    }
}
