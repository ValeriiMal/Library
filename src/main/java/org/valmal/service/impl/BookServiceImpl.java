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

    @Override
    @Transactional
    public List<Book> findBooksByExample(Book book) {
        List<Book> books = bookDao.findBooksByExample(book);
        if(book.isScarce()){
            books.removeIf(b -> !b.isScarce());
            return books;
        }
        return books;
    }

    @Override
    public int available(Book book) {
        return book.getAmount() - book.getReaders().size();
    }

    @Override
    public boolean isAvailable(Book book) {
        if(available(book) <= 0){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Integer readersCountById(int i) {
        return null;
    }
}
