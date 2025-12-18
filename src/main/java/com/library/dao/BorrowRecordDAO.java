package com.library.dao;

import com.library.entity.BorrowRecord;
import com.library.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 借阅记录数据访问对象
 */
public class BorrowRecordDAO {

    /**
     * 添加借阅记录
     */
    public boolean insert(BorrowRecord record) {
        String sql = "INSERT INTO borrow_records (user_id, book_id, due_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, record.getUserId());
            ps.setInt(2, record.getBookId());
            ps.setTimestamp(3, record.getDueDate());
            ps.setString(4, record.getStatus());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新借阅记录（归还图书）
     */
    public boolean updateReturnDate(Integer recordId, Timestamp returnDate) {
        String sql = "UPDATE borrow_records SET return_date = ?, status = 'returned' WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setTimestamp(1, returnDate);
            ps.setInt(2, recordId);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询用户当前借阅的图书
     */
    public List<BorrowRecord> findCurrentBorrowsByUserId(Integer userId) {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT br.*, b.title as book_title, b.author as book_author, u.name as user_name " +
                    "FROM borrow_records br " +
                    "JOIN books b ON br.book_id = b.id " +
                    "JOIN users u ON br.user_id = u.id " +
                    "WHERE br.user_id = ? AND br.status = 'borrowed' " +
                    "ORDER BY br.borrow_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    records.add(extractBorrowRecord(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 查询用户的所有借阅记录（包括已归还）
     */
    public List<BorrowRecord> findAllByUserId(Integer userId) {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT br.*, b.title as book_title, b.author as book_author, u.name as user_name " +
                    "FROM borrow_records br " +
                    "JOIN books b ON br.book_id = b.id " +
                    "JOIN users u ON br.user_id = u.id " +
                    "WHERE br.user_id = ? " +
                    "ORDER BY br.borrow_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    records.add(extractBorrowRecord(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 查询所有借阅记录（管理员使用）
     */
    public List<BorrowRecord> findAll() {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT br.*, b.title as book_title, b.author as book_author, u.name as user_name " +
                    "FROM borrow_records br " +
                    "JOIN books b ON br.book_id = b.id " +
                    "JOIN users u ON br.user_id = u.id " +
                    "ORDER BY br.borrow_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                records.add(extractBorrowRecord(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * 根据ID查询借阅记录
     */
    public BorrowRecord findById(Integer id) {
        String sql = "SELECT br.*, b.title as book_title, b.author as book_author, u.name as user_name " +
                    "FROM borrow_records br " +
                    "JOIN books b ON br.book_id = b.id " +
                    "JOIN users u ON br.user_id = u.id " +
                    "WHERE br.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractBorrowRecord(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从ResultSet中提取BorrowRecord对象
     */
    private BorrowRecord extractBorrowRecord(ResultSet rs) throws SQLException {
        BorrowRecord record = new BorrowRecord();
        record.setId(rs.getInt("id"));
        record.setUserId(rs.getInt("user_id"));
        record.setBookId(rs.getInt("book_id"));
        record.setBorrowDate(rs.getTimestamp("borrow_date"));
        record.setDueDate(rs.getTimestamp("due_date"));
        record.setReturnDate(rs.getTimestamp("return_date"));
        record.setStatus(rs.getString("status"));
        
        // Extended fields
        record.setBookTitle(rs.getString("book_title"));
        record.setBookAuthor(rs.getString("book_author"));
        record.setUserName(rs.getString("user_name"));
        
        return record;
    }
}
