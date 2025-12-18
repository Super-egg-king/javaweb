-- 创建数据库
CREATE DATABASE IF NOT EXISTS library_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library_system;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    student_id VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 图书表
CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(50),
    publisher VARCHAR(100),
    publish_date DATE,
    category VARCHAR(50),
    total_quantity INT NOT NULL DEFAULT 1,
    available_quantity INT NOT NULL DEFAULT 1,
    is_recommended BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_title (title),
    INDEX idx_category (category),
    INDEX idx_recommended (is_recommended)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 借阅记录表
CREATE TABLE IF NOT EXISTS borrow_records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP,
    return_date TIMESTAMP NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'borrowed',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    INDEX idx_user_id (user_id),
    INDEX idx_book_id (book_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员账户
INSERT INTO users (username, password, name, student_id, role) 
VALUES ('admin', 'admin123', '管理员', 'ADMIN001', 'admin')
ON DUPLICATE KEY UPDATE username=username;

-- 插入测试用户
INSERT INTO users (username, password, name, student_id, role) 
VALUES ('user1', 'user123', '张三', '2021001', 'user')
ON DUPLICATE KEY UPDATE username=username;

-- 插入示例图书数据
INSERT INTO books (title, author, isbn, publisher, publish_date, category, total_quantity, available_quantity, is_recommended) 
VALUES 
('Java编程思想', 'Bruce Eckel', '9787111213826', '机械工业出版社', '2007-06-01', '计算机', 5, 5, TRUE),
('深入理解Java虚拟机', '周志明', '9787111421900', '机械工业出版社', '2013-06-01', '计算机', 3, 3, TRUE),
('Effective Java', 'Joshua Bloch', '9787111255833', '机械工业出版社', '2009-01-01', '计算机', 4, 4, TRUE),
('Spring实战', 'Craig Walls', '9787115417305', '人民邮电出版社', '2016-04-01', '计算机', 3, 3, FALSE),
('算法导论', 'Thomas H. Cormen', '9787111407010', '机械工业出版社', '2012-12-01', '计算机', 2, 2, FALSE)
ON DUPLICATE KEY UPDATE title=title;
