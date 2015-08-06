package org.valmal.service;

import org.valmal.bean.Reader;

import java.util.List;

public interface ReaderService {
    void update(Reader reader);
    void insert(Reader reader);
    void delete(Reader reader);

    List<Reader> getReaders();

    Reader findReaderById(int id);
    List<Reader> findReadersByFName(String fName);
    List<Reader> findReadersByMName(String mName);
    List<Reader> findReadersByLName(String lName);
    List<Reader> findReadersByPhone(String phone);
    List<Reader> findReadersByAddress(String address);

    String readersToString(List<Reader> readers);
}
