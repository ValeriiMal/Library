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
import org.valmal.domain.PreRecord;
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

    @RequestMapping(value = "/get", produces = { "application/json; charset=UTF-8" })
    @ResponseBody
    public String getRecords() throws IOException {
        List<Record> records = reportService.getRecords();
        return new ObjectMapper().writeValueAsString(records);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
    @ResponseBody
    public String findRecords(@RequestBody String json) throws IOException, ParseException {
        PreRecord preRecord = new ObjectMapper().readValue(json, PreRecord.class);
        List<Record> records = null;
        // ��� ��������� �������
        if (preRecord.getId() != 0) {
            records = new ArrayList<>();
            records.add(reportService.findRecordById(preRecord.getId()));
        }
        if (preRecord.getBook_id() != 0) {
            Book book = bookService.findBookById(preRecord.getBook_id());
            if (records != null) {
                records.removeIf(r -> r.getBook().getId() != book.getId());
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsByBook(book));
            }
        }
        if (preRecord.getReader_id() != 0) {
            Reader reader = readerService.findReaderById(preRecord.getReader_id());
            if (records != null) {
                records.removeIf(r -> r.getReader().getId() != reader.getId());
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsByReader(reader));
            }
        }
        if (records != null) {
            switch (preRecord.getReturned()) {
                case "all": {
                }
                break;
                case "returned": {
                    records.removeIf(r -> !r.isChecked());
                }
                break;
                case "notReturned": {
                    records.removeIf(Record::isChecked);
                }
                break;
            }
        } else {
            records = new ArrayList<>();
            records.addAll(reportService.getRecords());
            switch (preRecord.getReturned()) {
                case "all": {
                }
                break;
                case "returned": {
                    records.removeIf(r -> !r.isChecked());
                }
                break;
                case "notReturned": {
                    records.removeIf(Record::isChecked);
                }
                break;
            }
        }
        if (preRecord.getDate_from() != null) {
            if (records != null) {
                records.removeIf(r -> r.getDate().before(preRecord.getDate_from()));
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsFromDate(preRecord.getDate_from()));
            }
        }
        if (preRecord.getDate_to() != null) {
            if (records != null) {
                records.removeIf(r -> r.getDate().after(preRecord.getDate_to()));
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsToDate(preRecord.getDate_from()));
            }
        }
        if (preRecord.getReturn_from() != null) {
            if (records != null) {
                records.removeIf(r -> r.getReturnDate().before(preRecord.getReturn_from()));
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsFromReturnDate(preRecord.getReturn_from()));
            }
        }
        if (preRecord.getReturn_to() != null) {
            if (records != null) {
                records.removeIf(r -> r.getReturnDate().after(preRecord.getReturn_to()));
            } else {
                records = new ArrayList<>();
                records.addAll(reportService.getRecordsToReturnDate(preRecord.getReturn_to()));
            }
        }

        if (records == null) records = new ArrayList<>();
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

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
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

    @RequestMapping(value = "/allBooksByDate", produces = { "application/json; charset=UTF-8" })
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
