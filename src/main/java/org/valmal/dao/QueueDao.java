package org.valmal.dao;

import org.valmal.bean.Queue;

import java.util.List;

public interface QueueDao {
    void insert(Queue queue);
    void update(Queue queue);
    void delete(Queue queue);

    Queue findQueueById(int id);
    List<Queue> findQueuesByExample(Queue example);
}
