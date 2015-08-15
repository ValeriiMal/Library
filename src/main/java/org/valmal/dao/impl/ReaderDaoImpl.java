package org.valmal.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Reader;
import org.valmal.dao.ReaderDao;

import java.util.List;

@Repository
public class ReaderDaoImpl implements ReaderDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(Reader reader) {
        sessionFactory.getCurrentSession()
                .update(reader);
    }

    public void insert(Reader reader) {
        sessionFactory.getCurrentSession()
                .save(reader);
    }

    public void delete(Reader reader) {
        sessionFactory.getCurrentSession()
                .delete(reader);
    }

    public List<Reader> getReaders() {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM readers ORDER BY id DESC")
                .addEntity(Reader.class)
                .list();
    }

    public Reader findReaderById(int id) {

        return (Reader) sessionFactory
                .getCurrentSession()
                .get(Reader.class, id);
    }

    public List<Reader> findReadersByFName(String fName){
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM readers AS r WHERE r.fName LIKE '%" + fName + "%' ORDER BY r.fName ASC")
                .addEntity(Reader.class)
                .list();
    }

    @Override
    public List<Reader> findReadersByMName(String mName) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM readers AS r WHERE r.mName LIKE '%" + mName + "%' ORDER BY r.mName ASC")
                .addEntity(Reader.class)
                .list();
    }

    @Override
    public List<Reader> findReadersByLName(String lName) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM readers AS r WHERE r.lName LIKE '%" + lName + "%' ORDER BY r.lName ASC")
                .addEntity(Reader.class)
                .list();
    }

    @Override
    public List<Reader> findReadersByPhone(String phone) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM readers AS r WHERE r.phone LIKE '%" + phone + "%' ORDER BY r.phone ASC")
                .addEntity(Reader.class)
                .list();
    }

    @Override
    public List<Reader> findReadersByAddress(String address) {
        return null;
    }

    @Override
    public List<Reader> findReadersByExample(Reader reader) {
        if(reader.getId() == 0){
            return sessionFactory.getCurrentSession()
                    .createCriteria(Reader.class)
                    .add(Restrictions.like("fName", "%" + reader.getfName() + "%"))
                    .add(Restrictions.like("mName", "%" + reader.getmName() + "%"))
                    .add(Restrictions.like("lName", "%" + reader.getlName() + "%"))
                    .add(Restrictions.like("phone", "%" + reader.getPhone() + "%"))
                    .setMaxResults(100)
                    .list();
        }
        return sessionFactory.getCurrentSession()
                .createCriteria(Reader.class)
                .add(Restrictions.eq("id", reader.getId()))
                .add(Restrictions.like("fName", "%" + reader.getfName() + "%"))
                .add(Restrictions.like("mName", "%" + reader.getmName() + "%"))
                .add(Restrictions.like("lName", "%" + reader.getlName() + "%"))
                .add(Restrictions.like("phone", "%" + reader.getPhone() + "%"))
                .setMaxResults(100)
                .list();

    }
}
