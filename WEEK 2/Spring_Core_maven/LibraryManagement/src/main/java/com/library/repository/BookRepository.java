package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public BookRepository() {
        System.out.println("BookRepository created.");
    }
}

package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}