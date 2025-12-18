package com.library.service;

import com.library.dao.BookDAO;
import com.library.entity.Book;

import java.util.List;

/**
 * 图书服务类
 */
public class BookService {
    private BookDAO bookDAO = new BookDAO();

    /**
     * 获取所有图书
     */
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    /**
     * 获取推荐图书
     */
    public List<Book> getRecommendedBooks() {
        return bookDAO.findRecommended();
    }

    /**
     * 根据ID获取图书
     */
    public Book getBookById(Integer id) {
        return bookDAO.findById(id);
    }

    /**
     * 根据分类获取图书
     */
    public List<Book> getBooksByCategory(String category) {
        return bookDAO.findByCategory(category);
    }

    /**
     * 搜索图书
     */
    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllBooks();
        }
        return bookDAO.search(keyword.trim());
    }

    /**
     * 添加图书（管理员功能）
     */
    public boolean addBook(Book book) {
        return bookDAO.insert(book);
    }

    /**
     * 更新图书信息（管理员功能）
     */
    public boolean updateBook(Book book) {
        return bookDAO.update(book);
    }

    /**
     * 检查图书是否可借
     */
    public boolean isBookAvailable(Integer bookId) {
        Book book = bookDAO.findById(bookId);
        return book != null && book.isAvailable();
    }
}
