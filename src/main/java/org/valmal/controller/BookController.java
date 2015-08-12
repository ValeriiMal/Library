package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/load")
    @ResponseBody
    public String loadBooks(){
        return bookService.booksToString(bookService.getBooks());
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public String findBook( @RequestBody String obj, HttpServletResponse response) throws IOException {
        Book example = new ObjectMapper().readValue(obj, Book.class);
        List<Book> books = bookService.findBooksByExample(example);
        return bookService.booksToString(books);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody String json) throws IOException {

        Book book = new ObjectMapper().readValue(json, Book.class);
        bookService.insert(book);

        return "book added";
    }

    @RequestMapping("/findBookByIdJSON")
    @ResponseBody
    public String findBookJSON(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(bookService.findBookById(Integer.parseInt(id)));
    }


    @RequestMapping("/getBookById")
    @ResponseBody
    public String getBookById(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(bookService.findBookById(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editBook(@RequestBody String obj) throws IOException {
        Book new_book = new ObjectMapper().readValue(obj, Book.class);
        Book book = bookService.findBookById(new_book.getId());

        book.setTitle(new_book.getTitle());
        book.setAuthors(new_book.getAuthors());
        book.setGenre(new_book.getGenre());
        book.setYear(new_book.getYear());
        book.setAmount(new_book.getAmount());

        bookService.update(book);
        return "edited";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String removeBook(@RequestParam("id") String id){
        bookService.delete(bookService.findBookById(Integer.parseInt(id)));
        return "book removed";
    }

    @RequestMapping("/readers")
    @ResponseBody
    public String getReaders(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(bookService.findBookById(Integer.parseInt(id)).getReaders());
    }
}
