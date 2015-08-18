package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
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

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public String findRecords(
            @RequestParam("id") String id,
            @RequestParam("book_id") String book_id,
            @RequestParam("reader_id") String reader_id,
            @RequestParam("date_from") String date_from,
            @RequestParam("date_to") String date_to,
            @RequestParam("return_from") String return_from,
            @RequestParam("return_to") String return_to,
            @RequestParam("returned") String returned

    ) throws IOException, ParseException {

        List<Record> records = new ArrayList<>();
            records = reportService.getRecords();

        switch (returned) {
            case "all": {

            }
            break;
            case "notReturned": {
                records.removeIf(Record::isChecked);
            }
            break;
            case "returned": {
                records.removeIf(r -> !r.isChecked());
            }
            break;
            default: {

            }
        }

        if (!id.equals("")) records.removeIf(r -> r.getId() != Integer.parseInt(id));

        if (!book_id.equals("")) records.removeIf(r -> r.getBook().getId() != Integer.parseInt(book_id));

        if (!reader_id.equals("")) records.removeIf(r -> r.getReader().getId() != Integer.parseInt(reader_id));

//        фільтр по даті запису
        if (!date_from.equals("")) {
            Date dateF = new SimpleDateFormat("yyy-MM-dd").parse(date_from);
            records.removeIf(r -> r.getDate().before(dateF));
        }
        if (!date_to.equals("")) {
            Date dateT = new SimpleDateFormat("yyy-MM-dd").parse(date_to);
            records.removeIf(r -> r.getDate().after(dateT));
        }
//        фільтр по даті повернення книги
        if (!return_from.equals("")) {
            Date retF = new SimpleDateFormat("yyy-MM-dd").parse(return_from);
            records.removeIf(r -> r.getReturnDate().before(retF));
        }
        if (!return_to.equals("")) {
            Date retT = new SimpleDateFormat("yyy-MM-dd").parse(return_to);
            records.removeIf(r -> r.getReturnDate().after(retT));
        }

        return new ObjectMapper().writeValueAsString(records);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addRecord(
            @RequestParam("bookId") String bookId,
            @RequestParam("readerId") String readerId,
            @RequestParam("returnDate") String returnDate
    ) throws ParseException {
        Book book = bookService.findBookById(Integer.parseInt(bookId));
        Reader reader = readerService.findReaderById(Integer.parseInt(readerId));

        if (bookService.isAvailable(book)) {

            Record record = new Record();
            record.setDate(new Date());
            book.getReaders().add(reader);
            bookService.update(book);

            record.setBook(book);
            record.setReader(reader);
            record.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(returnDate));
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
    ) throws ParseException {
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

            record.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(returnDate));
        }

        reportService.update(record);
        return "edited";
    }

    @RequestMapping("/allBooksByDate")
    @ResponseBody
    public String allBooksByDate(
            @RequestParam("checked") String how,
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo,
            @RequestParam("scarce") String scarce
    ) throws IOException, ParseException {
        List<Book> bookList = new ArrayList<>();
        List<Record> records = null;

        if (dateFrom.equals("") && dateTo.equals("")) {
            reportService.getRecordsByExample(new Record());
        } else if (dateTo.equals("")) {
            records = reportService.getRecordsBetweenDates(
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom),
                    new Date()
            );
        } else {
            records = reportService.getRecordsBetweenDates(
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom),
                    new SimpleDateFormat("yyyy-MM-dd").parse(dateTo)
            );
        }

        if (records != null) {
            if (Boolean.parseBoolean(scarce)) {
                records.removeIf(r -> !r.getBook().isScarce());
            }
            switch (how) {
                case "given": {
                    records.removeIf(Record::isChecked);
                }
                break;
                case "taken": {
                    records.removeIf(r -> !r.isChecked());
                }
                break;
            }

            records.stream().forEach(r -> bookList.add(r.getBook()));
            bookList.stream().distinct();
        }

        return new ObjectMapper().writeValueAsString(bookList);
    }

}
