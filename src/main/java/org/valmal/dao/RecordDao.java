package org.valmal.dao;

import org.valmal.bean.Record;

import java.util.List;

public interface RecordDao {
    void insert(Record record);
    List<Record> getRecords();
}
