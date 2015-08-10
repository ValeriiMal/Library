package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Record;
import org.valmal.dao.RecordDao;
import org.valmal.service.ReportService;

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
}
