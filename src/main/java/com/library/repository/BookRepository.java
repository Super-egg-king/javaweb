package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图书数据访问接口
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByRecommendedTrue();
    
    List<Book> findByTitleContaining(String title);
    
    List<Book> findByAuthorContaining(String author);
    
    List<Book> findByCategory(String category);
}
