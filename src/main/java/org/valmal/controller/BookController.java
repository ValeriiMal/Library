package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.valmal.bean.Book;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/find")
    @ResponseBody
    public String findBook(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("authors") String authors,
            @RequestParam("year") String year,
            @RequestParam("genre") String genre,
            HttpServletRequest request
    ) {
        List<Book> books = null;
        Enumeration<String> params = request.getParameterNames();

        for(Enumeration<String> p = params; p.hasMoreElements(); ){
            switch (p.nextElement()){
                case "id" : {
                    if(!id.isEmpty()){
                        books = new ArrayList<>();
                        books.add(bookService.findBookById(Integer.parseInt(id)));
                    }
                } break;
                case "title" : {
                    if(!title.isEmpty()){
                        if(books == null){
                            books = new ArrayList<>();
                            books.addAll(bookService.findBooksByTitle(title));
                        } else {
                            books.removeIf(r -> !r.getTitle().contains(title));
                        }
                    }
                } break;
                case "authors" : {
                    if(!authors.isEmpty()){
                        if(books == null){
                            books = new ArrayList<>();
                            books.addAll(bookService.findBooksByAuthors(authors));
                        } else {
                            books.removeIf(r -> !r.getAuthors().contains(authors));
                        }
                    }
                } break;
                case "year" : {
                    if(!year.isEmpty()){
                        if(books == null){
                            books = new ArrayList<>();
                            books.addAll(bookService.findBooksByYear(year));
                        } else {
                            books.removeIf(r -> !r.getYear().contains(year));
                        }
                    }
                } break;
                case "genre" : {
                    if(!genre.isEmpty()){
                        if(books == null){
                            books = new ArrayList<>();
                            books.addAll(bookService.findBooksByGenre(genre));
                        } else {
                            books.removeIf(r -> !r.getGenre().contains(genre));
                        }
                    }
                } break;
            }
        }

        if(books == null){ books = new ArrayList<>(); }

        return bookService.booksToString(books);
    }


    @RequestMapping("/add")
    @ResponseBody
    public String addBook(
            @RequestParam("title") String title,
            @RequestParam("authors") String authors,
            @RequestParam("year") String year,
            @RequestParam("genre") String genre,
            @RequestParam("count") String count
    ){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);
        book.setYear(year);
        book.setGenre(genre);
        book.setAmount(Integer.parseInt(count));

        bookService.insert(book);
        return "book added";
    }

    @RequestMapping("/findBookByIdJSON")
    @ResponseBody
    public String findBookJSON(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(bookService.findBookById(Integer.parseInt(id)));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String editBook(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("authors") String authors,
            @RequestParam("year") String year,
            @RequestParam("genre") String genre,
            @RequestParam("count") String count
    ){
        Book book = bookService.findBookById(Integer.parseInt(id));
        book.setTitle(title);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setYear(year);
        book.setAmount(Integer.parseInt(count));
        bookService.update(book);
        return "edited";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String removeBook(
            @RequestParam("id") String id
    ){
        bookService.delete(bookService.findBookById(Integer.parseInt(id)));
        return "book removed";
    }
}
