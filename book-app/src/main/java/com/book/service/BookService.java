package com.book.service;

import com.book.dto.BookDTO;
import com.book.exception.BookException;
import com.book.security.UserInfo;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO) throws BookException;
    BookDTO getBook(String bookName) throws BookException;
    List<BookDTO> getAllBooks()throws BookException;
    BookDTO updateBook(BookDTO bookDTO,Integer id) throws BookException;
    String deleteBook(String bookName) throws BookException;
    String addUser(UserInfo userInfo) throws BookException;
}
