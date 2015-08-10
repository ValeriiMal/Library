package org.valmal.service;

import org.valmal.bean.Record;

import java.util.List;

public interface ReportService {
    void insert(Record record);
    String recordsToString(List<Record> records);
    List<Record> getRecords();
}
