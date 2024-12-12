package com.book.api;

import com.book.dto.BookDTO;
import com.book.exception.BookException;
import com.book.security.UserInfo;
import com.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Valid
public class BookAPI {

    @Autowired
    BookService bookService;


    @PostMapping("/user/add")
    ResponseEntity<String> addUser(@RequestBody @Validated UserInfo userInfo) throws BookException{
        bookService.addUser(userInfo);
        return new ResponseEntity<>("User Successfully Added !!!", HttpStatus.CREATED);
    }

    @PostMapping("/book/add")
    @PreAuthorize("hasAuthorities(ROLE_ADMIN)")
    ResponseEntity<String> addBook(@RequestBody @Validated BookDTO bookDTO) throws BookException{
        bookService.addBook(bookDTO);
        return new ResponseEntity<>("Book Successfully Added !!!", HttpStatus.CREATED);
    }
    @GetMapping("/book/{bookName}")
    ResponseEntity<BookDTO> getBook(@PathVariable String bookName) throws BookException{
        BookDTO book = bookService.getBook(bookName);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @GetMapping("/book/all")
    ResponseEntity<List<BookDTO>> getAllBooks()throws BookException{
        List<BookDTO> allBooks = bookService.getAllBooks();

        return new ResponseEntity<>(allBooks,HttpStatus.OK);
    }
    @PutMapping("/book/update/{id}")
    ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO,@PathVariable Integer id) throws BookException{
        BookDTO bookDTO1 = bookService.updateBook(bookDTO, id);
        return new ResponseEntity<>(bookDTO1,HttpStatus.OK);
    }
    @DeleteMapping("/book/delete/{bookName}")
    ResponseEntity<String> deleteBook(@PathVariable String bookName) throws BookException{
        String s = bookService.deleteBook(bookName);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
