package org.valmal.service;

import org.valmal.bean.Book;
import org.valmal.bean.Queue;
import org.valmal.bean.Reader;

import java.util.Date;
import java.util.List;

public interface QueueService {
    void insert(Queue queue);
    void update(Queue queue);
    void delete(Queue queue);

    List<Queue> getQueues();
    Queue findQueueById(int id);
    List<Queue> findQueuesByExample(Queue example);
    List<Queue> findQueuesByDate(String date);
    List<Queue> findQueuesByBook(Book book);
    List<Queue> findQueuesByReader(Reader reader);
}
