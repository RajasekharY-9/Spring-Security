package com.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    Integer id;
@NotNull(message = "{book.bookname.empty}")
    String bookName;
    @NotNull(message = "{book.author.empty}")
    String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookDTO(Integer id, String bookName, String author) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public BookDTO() {
    }
}
