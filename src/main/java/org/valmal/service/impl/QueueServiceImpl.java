package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Queue;
import org.valmal.dao.QueueDao;
import org.valmal.service.QueueService;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {
    @Autowired
    QueueDao queueDao;

    @Override
    @Transactional(readOnly = false)
    public void insert(Queue queue) {
        queueDao.insert(queue);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Queue queue) {
        queueDao.update(queue);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Queue queue) {
        queueDao.delete(queue);
    }

    @Override
    @Transactional
    public Queue findQueueById(int id) {
        return queueDao.findQueueById(id);
    }

    @Override
    @Transactional
    public List<Queue> findQueuesByExample(Queue example) {
        return queueDao.findQueuesByExample(example);
    }
}
