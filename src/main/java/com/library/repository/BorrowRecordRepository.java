package com.library.repository;

import com.library.entity.BorrowRecord;
import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 借阅记录数据访问接口
 */
@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    
    List<BorrowRecord> findByUserAndStatus(User user, String status);
    
    List<BorrowRecord> findByUser(User user);
    
    List<BorrowRecord> findByUserOrderByBorrowDateDesc(User user);
    
    List<BorrowRecord> findByStatus(String status);
}
