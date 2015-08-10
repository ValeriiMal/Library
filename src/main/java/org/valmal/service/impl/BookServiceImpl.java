package org.valmal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.valmal.bean.Book;
import org.valmal.dao.BookDao;
import org.valmal.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    @Transactional(readOnly = false)
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    @Transactional
    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public String booksToString(List<Book> books){
        String res = "";
        if(books != null && !books.isEmpty()){
            for (Book book : books){
                res += book.toString();
            }
        }
        return res;
    }

    @Override
    @Transactional
    public List<Book> findBooksByTitle(String title) {
        return bookDao.findBooksByTitle(title);
    }

    @Override
    @Transactional
    public List<Book> findBooksByAuthors(String authors) {
        return bookDao.findBooksByAuthors(authors);
    }

    @Override
    @Transactional
    public List<Book> findBooksByYear(String year) {
        return bookDao.findBooksByYear(year);
    }

    @Override
    @Transactional
    public List<Book> findBooksByGenre(String genre) {
        return bookDao.findBooksByGenre(genre);
    }
}
