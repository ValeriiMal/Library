package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import org.valmal.bean.Reader;
import org.valmal.dao.ReaderDao;
import org.valmal.dao.impl.ReaderDaoImpl;
import org.valmal.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDao readerDao;

    @Transactional(readOnly = false)
    public void update(Reader reader) {
        readerDao.update(reader);
    }

    @Transactional(readOnly = false)
    public void insert(Reader reader) {
        readerDao.insert(reader);
    }

    @Transactional(readOnly = false)
    public void delete(Reader reader) {
        readerDao.delete(reader);
    }

    @Transactional
    public Reader findReaderById(int id) {
        return readerDao.findReaderById(id);
    }

    @Transactional
    public List<Reader> findReadersByFName(String fName) {
        return readerDao.findReadersByFName(fName);
    }

    @Transactional
    public List<Reader> getReaders() {
        return readerDao.getReaders();
    }

    public String readersToString(List<Reader> readers) {
        String res = "";

        if (readers != null && !readers.isEmpty()) {
            for (Reader r : readers) {
                res += r.toString();
            }
        }

        return res;
    }

    @Override
    @Transactional
    public List<Reader> findReadersByMName(String mName) {
        return readerDao.findReadersByMName(mName);
    }

    @Override
    @Transactional
    public List<Reader> findReadersByLName(String lName) {
        return readerDao.findReadersByLName(lName);
    }

    @Override
    @Transactional
    public List<Reader> findReadersByPhone(String phone) {
        return readerDao.findReadersByPhone(phone);
    }

    @Override
    @Transactional
    public List<Reader> findReadersByAddress(String address) {
        return readerDao.findReadersByAddress(address);
    }

    @Override
    @Transactional
    public List<Reader> findReadersByExample(Reader reader) {
        return readerDao.findReadersByExample(reader);
    }
}
