package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 图书服务类
 */
@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    
    /**
     * 获取新书推荐列表
     */
    public List<Book> getRecommendedBooks() {
        return bookRepository.findByRecommendedTrue();
    }
    
    /**
     * 搜索图书
     */
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }
    
    /**
     * 按分类查询图书
     */
    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }
    
    /**
     * 初始化默认图书数据
     */
    public void initDefaultBooks() {
        if (bookRepository.count() == 0) {
            // 添加一些示例图书
            Book book1 = new Book();
            book1.setIsbn("9787115428028");
            book1.setTitle("Java核心技术 卷I");
            book1.setAuthor("Cay S. Horstmann");
            book1.setPublisher("人民邮电出版社");
            book1.setPublishDate("2016-09-01");
            book1.setCategory("编程语言");
            book1.setDescription("Java领域经典著作，深入浅出地介绍了Java核心技术");
            book1.setTotalCopies(10);
            book1.setAvailableCopies(10);
            book1.setRecommended(true);
            save(book1);
            
            Book book2 = new Book();
            book2.setIsbn("9787115545954");
            book2.setTitle("Spring Boot实战");
            book2.setAuthor("Craig Walls");
            book2.setPublisher("人民邮电出版社");
            book2.setPublishDate("2020-10-01");
            book2.setCategory("编程框架");
            book2.setDescription("Spring Boot实战指南，帮助开发者快速上手");
            book2.setTotalCopies(8);
            book2.setAvailableCopies(8);
            book2.setRecommended(true);
            save(book2);
            
            Book book3 = new Book();
            book3.setIsbn("9787111544937");
            book3.setTitle("算法导论");
            book3.setAuthor("Thomas H. Cormen");
            book3.setPublisher("机械工业出版社");
            book3.setPublishDate("2012-12-01");
            book3.setCategory("算法");
            book3.setDescription("算法领域的经典教材");
            book3.setTotalCopies(5);
            book3.setAvailableCopies(5);
            book3.setRecommended(false);
            save(book3);
        }
    }
}
