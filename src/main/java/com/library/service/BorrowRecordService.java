package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.entity.User;
import com.library.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 借阅记录服务类
 */
@Service
@Transactional
public class BorrowRecordService {
    
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;
    
    @Autowired
    private BookService bookService;
    
    /**
     * 借阅图书
     */
    public BorrowRecord borrowBook(User user, Long bookId) {
        Optional<Book> bookOpt = bookService.findById(bookId);
        if (!bookOpt.isPresent()) {
            throw new RuntimeException("图书不存在");
        }
        
        Book book = bookOpt.get();
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("图书库存不足");
        }
        
        // 创建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDateTime.now());
        record.setDueDate(LocalDateTime.now().plusDays(30)); // 借阅期限30天
        record.setStatus("BORROWED");
        
        // 减少可用库存
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookService.save(book);
        
        return borrowRecordRepository.save(record);
    }
    
    /**
     * 归还图书
     */
    public BorrowRecord returnBook(Long recordId) {
        Optional<BorrowRecord> recordOpt = borrowRecordRepository.findById(recordId);
        if (!recordOpt.isPresent()) {
            throw new RuntimeException("借阅记录不存在");
        }
        
        BorrowRecord record = recordOpt.get();
        if (!"BORROWED".equals(record.getStatus())) {
            throw new RuntimeException("该图书已归还");
        }
        
        // 更新借阅记录
        record.setReturnDate(LocalDateTime.now());
        record.setStatus("RETURNED");
        
        // 增加可用库存
        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.save(book);
        
        return borrowRecordRepository.save(record);
    }
    
    /**
     * 获取用户当前借阅的图书
     */
    public List<BorrowRecord> getCurrentBorrowRecords(User user) {
        return borrowRecordRepository.findByUserAndStatus(user, "BORROWED");
    }
    
    /**
     * 获取用户所有借阅记录
     */
    public List<BorrowRecord> getAllBorrowRecords(User user) {
        return borrowRecordRepository.findByUserOrderByBorrowDateDesc(user);
    }
    
    /**
     * 获取所有借阅记录（管理员）
     */
    public List<BorrowRecord> getAllRecords() {
        return borrowRecordRepository.findAll();
    }
}
