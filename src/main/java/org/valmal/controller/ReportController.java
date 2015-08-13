package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.bean.Record;
import org.valmal.service.BookService;
import org.valmal.service.ReaderService;
import org.valmal.service.ReportService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;
    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;

    @RequestMapping("/load")
    @ResponseBody
    public String loadReport() {
        return reportService.recordsToString(reportService.getRecords());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addRecord(
            @RequestParam("bookId") String bookId,
            @RequestParam("readerId") String readerId,
            @RequestParam("returnDate") String returnDate
    ) {
        Book book = bookService.findBookById(Integer.parseInt(bookId));
        Reader reader = readerService.findReaderById(Integer.parseInt(readerId));

        if (bookService.isAvailable(book)) {

            Record record = new Record();
            record.setDate(new Date());
            book.getReaders().add(reader);
//            reader.getBooks().add(book);
            bookService.update(book);
//            readerService.update(reader);

            record.setBook(book);
            record.setReader(reader);
            record.setReturnDate(returnDate);
            record.setChecked(false);

            reportService.insert(record);
            return "record added";
        }

        return "books ended";

    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public String findRecordById(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(reportService.findRecordById(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseBody
    public String editRecord(
            @RequestParam("id") String id,
            @RequestParam("bookId") String bookId,
            @RequestParam("readerId") String readerId,
            @RequestParam("checked") String checked,
            @RequestParam("returnDate") String returnDate
    ) {
        Record record = reportService.findRecordById(Integer.parseInt(id));

        if (Boolean.parseBoolean(checked)) {
            record.setChecked(Boolean.parseBoolean(checked));
            Book book = record.getBook();
            book.getReaders().removeIf(r -> r.getId() == Integer.parseInt(readerId));
            bookService.update(book);
        } else {
            if (record.getBook().getId() != Integer.parseInt(bookId)) {
                record.setBook(bookService.findBookById(Integer.parseInt(bookId)));
            }
            if (record.getReader().getId() != Integer.parseInt(readerId)) {
                record.setReader(readerService.findReaderById(Integer.parseInt(readerId)));
            }

            record.setChecked(Boolean.parseBoolean(checked));

            record.setReturnDate(returnDate);
        }

        reportService.update(record);
        return "edited";
    }

    @RequestMapping("/allBooksByDate")
    @ResponseBody
    public String allBooksByDate(
            @RequestParam("checked") String how,
            @RequestParam("date1") String date1,
            @RequestParam("date2") String date2
    ) throws IOException, ParseException {
        List<Book> bookList = new ArrayList<>();

        Date date11 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        Date date22 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);

        List<Record> records = reportService.getRecordsBetweenDates(date11, date22);

        switch (how) {
            case "given": {
                records.removeIf(r -> !r.isChecked());
            }
            break;
            case "taken": {
                records.removeIf(Record::isChecked);
            }
            break;
        }

        records.stream().forEach(r -> bookList.add(r.getBook()));
        bookList.stream().distinct();

        return new ObjectMapper().writeValueAsString(bookList);
    }
}
