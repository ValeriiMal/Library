package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.bean.Record;
import org.valmal.dao.RecordDao;
import org.valmal.domain.PreRecord;
import org.valmal.service.ReportService;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    RecordDao recordDao;

    @Override
    @Transactional(readOnly = false)
    public void insert(Record record) {
        recordDao.insert(record);
    }

    @Override
    @Transactional
    public List<Record> getRecords() {
        return recordDao.getRecords();
    }

    @Override
    public String recordsToString(List<Record> records) {
        String res = "";
        if(records != null && !records.isEmpty()){
            for(Record r : records){
                res += r.toString();
            }
        }
        return res;
    }

    @Override
    @Transactional
    public Record findRecordById(int id) {
        return recordDao.findRecordById(id);
    }

    @Override
    @Transactional
    public void update(Record record) {
        recordDao.update(record);
    }

    @Override
    @Transactional
    public List<Record> getRecordsBetweenDates(Date date1, Date date2) {
        return recordDao.getRecordsBetweenDates(date1, date2);
    }

    @Override
    @Transactional
    public List<Record> getRecordsByExample(Record example) {
        return recordDao.getRecordsByExample(example);
    }

    @Override
    @Transactional
    public Date getRecordsFirstDate() {
        return recordDao.getRecordsFirstDate();
    }

    @Override
    @Transactional
    public List<Record> getRecordsByPreExample(PreRecord preExample, Book book, Reader reader) {
        return recordDao.getRecordsByPreExample(preExample, book, reader);
    }

    @Override
    @Transactional
    public List<Record> getRecordsByBook(Book book) {
        return recordDao.getRecordsByBook(book);
    }

    @Override
    @Transactional
    public List<Record> getRecordsByReader(Reader reader) {
        return recordDao.getRecordsByReader(reader);
    }

    @Override
    @Transactional
    public List<Record> getRecordsFromDate(Date date) {
        return recordDao.getRecordsFromDate(date);
    }

    @Override
    @Transactional
    public List<Record> getRecordsToDate(Date date) {
        return recordDao.getRecordsToDate(date);
    }

    @Override
    @Transactional
    public List<Record> getRecordsFromReturnDate(Date date) {
        return recordDao.getRecordsFromReturnDate(date);
    }

    @Override
    @Transactional
    public List<Record> getRecordsToReturnDate(Date date) {
        return recordDao.getRecordsToReturnDate(date);
    }
}
