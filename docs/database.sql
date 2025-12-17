-- 创建数据库
CREATE DATABASE IF NOT EXISTS library_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE library_system;

-- 注意：以下表结构会由 Spring Boot JPA 自动创建
-- 本脚本仅用于参考，实际运行时 JPA 会自动管理表结构

-- 用户表
-- CREATE TABLE IF NOT EXISTS users (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(100) NOT NULL,
--     real_name VARCHAR(50) NOT NULL,
--     student_id VARCHAR(20),
--     email VARCHAR(50),
--     phone VARCHAR(20),
--     role VARCHAR(20) NOT NULL,
--     enabled BOOLEAN NOT NULL DEFAULT TRUE,
--     create_time DATETIME,
--     update_time DATETIME
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 图书表
-- CREATE TABLE IF NOT EXISTS books (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     isbn VARCHAR(13) NOT NULL,
--     title VARCHAR(200) NOT NULL,
--     author VARCHAR(100),
--     publisher VARCHAR(100),
--     publish_date VARCHAR(50),
--     category VARCHAR(50),
--     description TEXT,
--     total_copies INT NOT NULL DEFAULT 0,
--     available_copies INT NOT NULL DEFAULT 0,
--     recommended BOOLEAN NOT NULL DEFAULT FALSE,
--     create_time DATETIME,
--     update_time DATETIME
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 借阅记录表
-- CREATE TABLE IF NOT EXISTS borrow_records (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     user_id BIGINT NOT NULL,
--     book_id BIGINT NOT NULL,
--     borrow_date DATETIME NOT NULL,
--     due_date DATETIME NOT NULL,
--     return_date DATETIME,
--     status VARCHAR(20) NOT NULL,
--     remarks TEXT,
--     create_time DATETIME,
--     update_time DATETIME,
--     FOREIGN KEY (user_id) REFERENCES users(id),
--     FOREIGN KEY (book_id) REFERENCES books(id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
