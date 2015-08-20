package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.valmal.bean.Book;
import org.valmal.bean.Queue;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;
import org.valmal.service.QueueService;
import org.valmal.service.ReaderService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/queue")
public class QueueController {
    @Autowired
    QueueService queueService;
    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;

    @RequestMapping("/getQueues")
    @ResponseBody
    public String getQueues() throws IOException {
        return new ObjectMapper().writeValueAsString(queueService.getQueues());
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(@RequestParam("id") String id,
                       @RequestParam("date") String date,
                       @RequestParam("book_id") String book_id,
                       @RequestParam("reader_id") String reader_id) throws ParseException, IOException {

        List<Queue> queues = null;

        if (!id.equals("")) {
            queues = new ArrayList<>();
            queues.add(queueService.findQueueById(Integer.parseInt(id)));
        }
        if (!date.equals("")) {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

            if (queues != null) {
                queues.removeIf(q -> !q.getDate().toString().contains(date));
            }
            else {
                queues = new ArrayList<>();
                queues.addAll(queueService.findQueuesByDate(date));
            }
        }
        if (!book_id.equals("")) {
            Book b = bookService.findBookById(Integer.parseInt(book_id));
            if (queues != null) {
                queues.removeIf(q -> q.getBook().getId() != b.getId());
            } else {
                queues = new ArrayList<>();
                queues.addAll(queueService.findQueuesByBook(b));
            }
        }
        if (!reader_id.equals("")) {
            Reader r = readerService.findReaderById(Integer.parseInt(reader_id));
            if (queues != null) {
                queues.removeIf(q -> q.getReader().getId() != r.getId());
            } else {
                queues = new ArrayList<>();
                queues.addAll(queueService.findQueuesByReader(r));
            }
        }

        if (queues == null) {
            return new ObjectMapper().writeValueAsString(new ArrayList<>());
        } else {
            return new ObjectMapper().writeValueAsString(queues);
        }
    }


    @RequestMapping("/add")
    @ResponseBody
    public String add(
            @RequestParam("book_id") String book_id,
            @RequestParam("reader_id") String reader_id
    ) {
        Queue queue = new Queue();
        queue.setDate(new Date());
        queue.setBook(bookService.findBookById(Integer.parseInt(book_id)));
        queue.setReader(readerService.findReaderById(Integer.parseInt(reader_id)));
        queueService.insert(queue);
        return "added";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public String findById(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(queueService.findQueueById(Integer.parseInt(id)));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(
            @RequestParam("id") String id,
            @RequestParam("book_id") String book_id,
            @RequestParam("reader_id") String reader_id) {
        Queue queue = queueService.findQueueById(Integer.parseInt(id));
        queue.setReader(readerService.findReaderById(Integer.parseInt(reader_id)));
        queue.setBook(bookService.findBookById(Integer.parseInt(book_id)));
        queueService.update(queue);
        return "edited";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("id") String id) {
        queueService.delete(queueService.findQueueById(Integer.parseInt(id)));
        return "removed";
    }
}
