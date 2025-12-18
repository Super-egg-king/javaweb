package com.library.dao;

import com.library.entity.Book;
import com.library.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书数据访问对象
 */
public class BookDAO {

    /**
     * 查询所有图书
     */
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                books.add(extractBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 查询推荐图书
     */
    public List<Book> findRecommended() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE is_recommended = TRUE ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                books.add(extractBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 根据ID查询图书
     */
    public Book findById(Integer id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractBook(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据分类查询图书
     */
    public List<Book> findByCategory(String category) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE category = ? ORDER BY title";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, category);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(extractBook(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 搜索图书（按标题或作者）
     */
    public List<Book> search(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? ORDER BY title";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(extractBook(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 添加图书
     */
    public boolean insert(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, publisher, publish_date, category, " +
                    "total_quantity, available_quantity, is_recommended) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getPublisher());
            ps.setDate(5, book.getPublishDate());
            ps.setString(6, book.getCategory());
            ps.setInt(7, book.getTotalQuantity());
            ps.setInt(8, book.getAvailableQuantity());
            ps.setBoolean(9, book.getIsRecommended());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新图书信息
     */
    public boolean update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, publisher = ?, " +
                    "publish_date = ?, category = ?, total_quantity = ?, available_quantity = ?, " +
                    "is_recommended = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getPublisher());
            ps.setDate(5, book.getPublishDate());
            ps.setString(6, book.getCategory());
            ps.setInt(7, book.getTotalQuantity());
            ps.setInt(8, book.getAvailableQuantity());
            ps.setBoolean(9, book.getIsRecommended());
            ps.setInt(10, book.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新图书可用数量
     */
    public boolean updateAvailableQuantity(Integer bookId, int delta) {
        String sql = "UPDATE books SET available_quantity = available_quantity + ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, delta);
            ps.setInt(2, bookId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从ResultSet中提取Book对象
     */
    private Book extractBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));
        book.setPublishDate(rs.getDate("publish_date"));
        book.setCategory(rs.getString("category"));
        book.setTotalQuantity(rs.getInt("total_quantity"));
        book.setAvailableQuantity(rs.getInt("available_quantity"));
        book.setIsRecommended(rs.getBoolean("is_recommended"));
        book.setCreatedAt(rs.getTimestamp("created_at"));
        return book;
    }
}
