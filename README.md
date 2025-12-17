# 图书云借阅系统

## 项目简介

本项目是一个基于 Spring Boot + Maven 开发的图书云借阅系统，支持用户登录、图书管理、借阅管理等功能，并实现了管理员和普通用户的权限控制。

**开发者信息：**
- 姓名：张三
- 学号：2021001

## 功能模块

### 一、用户登录模块
- ✅ 用户登录
- ✅ 登录验证
- ✅ 注销登录

### 二、图书管理模块
- ✅ 新书推荐
- ✅ 图书借阅
- ✅ 当前借阅
- ✅ 借阅记录

### 三、访问权限控制
- ✅ 管理员权限（可管理图书、查看所有借阅记录）
- ✅ 普通用户权限（可借阅图书、查看个人借阅记录）

## 技术栈

- **后端框架：** Spring Boot 2.7.14
- **数据库：** MySQL 8.0
- **ORM框架：** Spring Data JPA / Hibernate
- **模板引擎：** Thymeleaf
- **构建工具：** Maven
- **开发语言：** Java 8

## 项目结构

```
javaweb/
├── src/
│   ├── main/
│   │   ├── java/com/library/
│   │   │   ├── BookLendingSystemApplication.java  # 主应用类
│   │   │   ├── config/                            # 配置类
│   │   │   │   └── DataInitializer.java          # 数据初始化
│   │   │   ├── controller/                        # 控制器
│   │   │   │   ├── AuthController.java           # 登录认证控制器
│   │   │   │   ├── BookController.java           # 图书管理控制器
│   │   │   │   └── BorrowController.java         # 借阅管理控制器
│   │   │   ├── entity/                            # 实体类
│   │   │   │   ├── User.java                     # 用户实体
│   │   │   │   ├── Book.java                     # 图书实体
│   │   │   │   └── BorrowRecord.java             # 借阅记录实体
│   │   │   ├── repository/                        # 数据访问层
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── BorrowRecordRepository.java
│   │   │   └── service/                           # 业务逻辑层
│   │   │       ├── UserService.java
│   │   │       ├── BookService.java
│   │   │       └── BorrowRecordService.java
│   │   └── resources/
│   │       ├── application.yml                    # 应用配置文件
│   │       └── templates/                         # 前端页面模板
│   │           ├── login.html                     # 登录页面
│   │           ├── home.html                      # 主页
│   │           ├── books/                         # 图书相关页面
│   │           │   ├── list.html
│   │           │   ├── recommended.html
│   │           │   ├── add.html
│   │           │   └── edit.html
│   │           └── borrow/                        # 借阅相关页面
│   │               ├── current.html
│   │               ├── history.html
│   │               └── all.html
└── pom.xml                                         # Maven配置文件
```

## 数据库配置

在运行项目前，需要在 MySQL 中创建数据库：

```sql
CREATE DATABASE library_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

然后修改 `src/main/resources/application.yml` 中的数据库连接配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/library_system?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
    username: root
    password: root  # 修改为你的数据库密码
```

## 运行项目

### 1. 克隆项目

```bash
git clone https://github.com/Super-egg-king/javaweb.git
cd javaweb
```

### 2. 配置数据库

确保 MySQL 数据库已安装并运行，创建 `library_system` 数据库。

### 3. 启动项目

使用 Maven 命令启动：

```bash
mvn spring-boot:run
```

或者使用 IDE（如 IntelliJ IDEA）打开项目，运行 `BookLendingSystemApplication` 主类。

### 4. 访问系统

在浏览器中访问：`http://localhost:8080`

## 默认账号

系统会自动初始化以下测试账号：

**管理员账号：**
- 用户名：admin
- 密码：admin123

**普通用户账号：**
- 用户名：user
- 密码：user123

## 主要功能说明

### 用户登录
- 支持用户名密码登录
- 登录后根据角色显示不同功能
- 支持退出登录

### 图书管理（管理员）
- 添加新图书
- 编辑图书信息
- 删除图书
- 设置图书为推荐

### 图书借阅（用户）
- 查看所有可借阅图书
- 查看新书推荐
- 借阅图书
- 归还图书
- 查看当前借阅情况
- 查看历史借阅记录

### 借阅管理（管理员）
- 查看所有用户的借阅记录
- 监控图书借阅状态

## 系统特色

1. **响应式设计**：前端页面采用响应式设计，适配不同屏幕尺寸
2. **权限控制**：清晰的管理员和用户权限划分
3. **状态管理**：自动识别图书借阅状态（借阅中、已逾期、已归还）
4. **数据初始化**：首次运行自动创建测试数据
5. **个性化标识**：页面中包含开发者姓名和学号信息

## 注意事项

- 本项目使用简单的明文密码验证，实际生产环境应使用加密方式
- 借阅期限默认为30天
- 图书库存会在借阅和归还时自动更新
- 系统使用 JPA 的 `ddl-auto: update` 配置，会自动创建和更新数据库表结构

## 开发环境建议

- JDK 1.8 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0 或更高版本
- IntelliJ IDEA（推荐）或 Eclipse

## License

本项目仅用于学习和教学目的。
