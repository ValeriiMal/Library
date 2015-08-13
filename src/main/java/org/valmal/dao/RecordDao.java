package org.valmal.dao;

import org.valmal.bean.Record;

import java.util.Date;
import java.util.List;

public interface RecordDao {
    void insert(Record record);
    void update(Record record);
    List<Record> getRecords();
    Record findRecordById(int id);
    List<Record> getRecordsBetweenDates(String date1, String date2);
    List<Record> getRecordsBetweenDates(Date date1, Date date2);
}
