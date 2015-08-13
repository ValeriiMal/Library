package org.valmal.service;

import org.valmal.bean.Record;

import java.util.Date;
import java.util.List;

public interface ReportService {
    void insert(Record record);
    void update(Record record);
    String recordsToString(List<Record> records);
    List<Record> getRecords();
    Record findRecordById(int id);
    List<Record> getRecordsBetweenDates(Date date1, Date date2);
}
