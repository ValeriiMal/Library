package org.valmal.service;

import org.valmal.bean.Book;

import java.util.List;

public interface BookService {
    void insert(Book book);
    void update(Book book);
    void delete(Book book);
    Book findBookById(int id);
    List<Book> getBooks();
    String booksToString(List<Book> books);
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthors(String authors);
    List<Book> findBooksByYear(String year);
    List<Book> findBooksByGenre(String genre);
}
