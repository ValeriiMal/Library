package org.valmal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.bean.Record;
import org.valmal.service.BookService;
import org.valmal.service.ReaderService;
import org.valmal.service.ReportService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String loadReport(){
        return reportService.recordsToString(reportService.getRecords());
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addRecord(
            @RequestParam("bookId") String bookId,
            @RequestParam("readerId") String readerId,
            @RequestParam("returnDate") String returnDate
    ){
        Record record = new Record();
        record.setDate(new SimpleDateFormat("yyyy/mm/dd hh:mm:ss").format(new Date()));

        Book book = bookService.findBookById(Integer.parseInt(bookId));
        Reader reader = readerService.findReaderById(Integer.parseInt(readerId));
        book.getReaders().add(reader);
        bookService.update(book);

        record.setBook(book);
        record.setReader(reader);
        record.setReturnDate(returnDate);
        record.setChecked(false);

        reportService.insert(record);

        return "record added";
    }
}
