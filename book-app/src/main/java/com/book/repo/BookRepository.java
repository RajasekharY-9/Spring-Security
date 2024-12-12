package com.book.repo;

import com.book.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Books ,Integer> {

    Books findByBookName(String bookName);
}
