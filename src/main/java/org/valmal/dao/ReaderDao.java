package org.valmal.dao;

import org.valmal.bean.Reader;

import java.util.List;

public interface ReaderDao {
    void update(Reader reader);
    void insert(Reader reader);
    void delete(Reader reader);

    Reader findReaderById(int id);

    List<Reader> getReaders();

    List<Reader> findReadersByFName(String fName);
    List<Reader> findReadersByMName(String mName);
    List<Reader> findReadersByLName(String lName);
    List<Reader> findReadersByPhone(String phone);
    List<Reader> findReadersByAddress(String address);

    List<Reader> findReadersByExample(Reader reader);
}
