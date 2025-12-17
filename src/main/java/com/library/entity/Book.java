package com.library.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 图书实体类
 */
@Data
@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 13)
    private String isbn;
    
    @Column(nullable = false, length = 200)
    private String title;
    
    @Column(length = 100)
    private String author;
    
    @Column(length = 100)
    private String publisher;
    
    @Column(name = "publish_date")
    private String publishDate;
    
    @Column(length = 50)
    private String category;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private Integer totalCopies = 0;
    
    @Column(nullable = false)
    private Integer availableCopies = 0;
    
    /**
     * 是否为新书推荐
     */
    @Column(nullable = false)
    private Boolean recommended = false;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    
    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}
