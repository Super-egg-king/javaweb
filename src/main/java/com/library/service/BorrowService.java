package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.BorrowRecordDAO;
import com.library.entity.Book;
import com.library.entity.BorrowRecord;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * 借阅服务类
 */
public class BorrowService {
    private BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();
    private BookDAO bookDAO = new BookDAO();

    /**
     * 借阅图书
     */
    public boolean borrowBook(Integer userId, Integer bookId) {
        // Check if book is available
        Book book = bookDAO.findById(bookId);
        if (book == null || !book.isAvailable()) {
            return false;
        }

        // Calculate due date (30 days from now)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Timestamp dueDate = new Timestamp(calendar.getTimeInMillis());

        // Create borrow record
        BorrowRecord record = new BorrowRecord(userId, bookId, dueDate);
        
        // Update book available quantity
        boolean updated = bookDAO.updateAvailableQuantity(bookId, -1);
        if (!updated) {
            return false;
        }

        // Insert borrow record
        return borrowRecordDAO.insert(record);
    }

    /**
     * 归还图书
     */
    public boolean returnBook(Integer recordId) {
        BorrowRecord record = borrowRecordDAO.findById(recordId);
        if (record == null || !record.isBorrowed()) {
            return false;
        }

        // Update return date
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());
        boolean updated = borrowRecordDAO.updateReturnDate(recordId, returnDate);
        
        if (updated) {
            // Update book available quantity
            bookDAO.updateAvailableQuantity(record.getBookId(), 1);
        }

        return updated;
    }

    /**
     * 获取用户当前借阅的图书
     */
    public List<BorrowRecord> getCurrentBorrows(Integer userId) {
        return borrowRecordDAO.findCurrentBorrowsByUserId(userId);
    }

    /**
     * 获取用户的所有借阅记录
     */
    public List<BorrowRecord> getBorrowHistory(Integer userId) {
        return borrowRecordDAO.findAllByUserId(userId);
    }

    /**
     * 获取所有借阅记录（管理员功能）
     */
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordDAO.findAll();
    }

    /**
     * 根据ID获取借阅记录
     */
    public BorrowRecord getBorrowRecordById(Integer id) {
        return borrowRecordDAO.findById(id);
    }
}
