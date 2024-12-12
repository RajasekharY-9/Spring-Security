package com.book.service;

import com.book.dto.BookDTO;
import com.book.entity.Books;
import com.book.exception.BookException;
import com.book.repo.BookRepository;
import com.book.security.UserInfo;
import com.book.security.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public void addBook(BookDTO bookDTO) throws BookException {
        Books book = bookRepository.findByBookName(bookDTO.getBookName());
        if(book ==null){
            Books bs=new Books();
            bs.setBookName(bookDTO.getBookName());
            bs.setAuthor(book.getAuthor());
            bookRepository.save(bs);
        }
        else{
            throw new BookException("SERVICE_BOOK_EXISTS!");
        }

    }

    @Override
    public BookDTO getBook(String bookName) throws BookException {
        Books book = bookRepository.findByBookName(bookName);
        if(Optional.of(book).isEmpty()){
            throw new BookException("SERVICE_BOOK_NOT_EXISTS!");
        }else{
            BookDTO bs=new BookDTO();
            bs.setBookName(book.getBookName());
            bs.setAuthor(book.getAuthor());
            bs.setId(book.getId());
            return bs;
        }
    }

    @Override
    public List<BookDTO> getAllBooks() throws BookException {
        List<Books> all = bookRepository.findAll();
        List<BookDTO> dtos = new ArrayList<>();
        if(all.isEmpty()){
            throw new BookException("SERVICE_BOOKS_NOT_EXISTS!");
        }
        for(Books b:all){
            BookDTO bookDTO=new BookDTO();
            bookDTO.setId(b.getId());;
            bookDTO.setBookName(b.getBookName());
            bookDTO.setAuthor(b.getAuthor());
            dtos.add(bookDTO);
        }
        return dtos;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Integer id) throws BookException {
        Optional<Books> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new BookException("SERVICE_BOOK_NOT_EXISTS!");
        }
        else{
            Books bs=book.get();
            bs.setBookName(bookDTO.getBookName());
            bs.setAuthor(bookDTO.getAuthor());
            bookRepository.save(bs);
            BookDTO bookDTO1=new BookDTO();
            bookDTO1.setAuthor(bs.getAuthor());
            bookDTO1.setBookName(bs.getBookName());
            bookDTO1.setId(bs.getId());

            return bookDTO1;
        }
    }

    @Override
    public String deleteBook(String bookName) throws BookException {
        Books name = bookRepository.findByBookName(bookName);
        if(Optional.of(name).isEmpty()){
            throw new BookException("SERVICE_BOOK_NOT_EXISTS!");
        }
        else {
            bookRepository.delete(name);
            return "Book Successfully Deleted !!!";
        }
    }

    @Override
    public String addUser(UserInfo userInfo) throws BookException {
        Optional<UserInfo> name = userRepository.findByUserName(userInfo.getUserName());
        if(name.isPresent()){
            throw new BookException("User_EXISTS");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
       userRepository.save(userInfo);
        return "User added";
    }
}
