# 图书云借阅系统 (Book Cloud Lending System)

一个基于Java Web的图书借阅管理系统，实现了完整的用户登录、图书管理和借阅功能。

## 开发者信息 (Developer Information)

- **姓名 (Name):** 张三 (Zhang San)
- **学号 (Student ID):** 2021001234

## 项目特点 (Features)

### 一、用户登录模块
- ✅ 用户登录：支持用户名和密码登录
- ✅ 登录验证：完整的身份验证机制
- ✅ 注销登录：安全退出系统
- ✅ Session管理：30分钟会话超时

### 二、图书管理模块
- ✅ 新书推荐：展示推荐的优质图书
- ✅ 图书借阅：浏览和借阅图书
- ✅ 当前借阅：查看和管理当前借阅的图书
- ✅ 借阅记录：完整的借阅历史记录

### 三、访问权限控制
- ✅ 管理员权限：可查看所有用户借阅记录
- ✅ 普通用户权限：只能查看自己的借阅信息
- ✅ Filter过滤器：自动拦截未登录用户

### 四、个人身份元素
- ✅ 所有页面都包含开发者姓名和学号
- ✅ 登录页面特别标注个人信息
- ✅ 页脚统一展示个人身份信息

## 技术栈 (Technology Stack)

- **后端:** Java Servlet 4.0, JSP 2.3
- **数据库:** MySQL 8.0
- **构建工具:** Maven 3.x
- **服务器:** Tomcat 9.x
- **前端:** HTML5, CSS3, JavaScript
- **依赖管理:** JDBC, JSTL

## 项目结构 (Project Structure)

```
javaweb/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/library/
│   │   │       ├── entity/          # 实体类
│   │   │       │   ├── User.java
│   │   │       │   ├── Book.java
│   │   │       │   └── BorrowRecord.java
│   │   │       ├── dao/             # 数据访问层
│   │   │       │   ├── UserDAO.java
│   │   │       │   ├── BookDAO.java
│   │   │       │   └── BorrowRecordDAO.java
│   │   │       ├── service/         # 业务逻辑层
│   │   │       │   ├── UserService.java
│   │   │       │   ├── BookService.java
│   │   │       │   └── BorrowService.java
│   │   │       ├── servlet/         # 控制器
│   │   │       │   ├── LoginServlet.java
│   │   │       │   ├── LogoutServlet.java
│   │   │       │   ├── BookListServlet.java
│   │   │       │   ├── BorrowServlet.java
│   │   │       │   ├── ReturnBookServlet.java
│   │   │       │   ├── CurrentBorrowsServlet.java
│   │   │       │   └── BorrowHistoryServlet.java
│   │   │       ├── filter/          # 过滤器
│   │   │       │   └── LoginFilter.java
│   │   │       └── util/            # 工具类
│   │   │           └── DBUtil.java
│   │   ├── resources/
│   │   │   ├── db.properties        # 数据库配置
│   │   │   └── init.sql             # 数据库初始化脚本
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── css/
│   │       │   └── style.css
│   │       ├── admin/
│   │       │   └── index.jsp
│   │       ├── login.jsp            # 登录页面
│   │       ├── index.jsp            # 首页
│   │       ├── books.jsp            # 图书列表
│   │       ├── borrow.jsp           # 当前借阅
│   │       └── history.jsp          # 借阅记录
│   └── test/
│       └── java/
├── pom.xml                          # Maven配置
└── README.md

```

## 快速开始 (Quick Start)

### 1. 环境准备

- JDK 1.8+
- Maven 3.x
- MySQL 8.0+
- Tomcat 9.x

### 2. 数据库配置

```bash
# 创建数据库并导入初始数据
mysql -u root -p < src/main/resources/init.sql
```

或手动执行：
```sql
CREATE DATABASE library_system;
USE library_system;
-- 然后执行 src/main/resources/init.sql 中的所有语句
```

### 3. 修改数据库配置

编辑 `src/main/resources/db.properties`：
```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/library_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username=root
jdbc.password=your_password
```

### 4. 编译项目

```bash
mvn clean package
```

### 5. 部署运行

将生成的 `target/book-lending-system.war` 文件部署到Tomcat的webapps目录下，然后启动Tomcat。

访问：`http://localhost:8080/book-lending-system/`

## 测试账户 (Test Accounts)

### 管理员账户
- 用户名: `admin`
- 密码: `admin123`
- 权限: 可以查看所有用户的借阅记录

### 普通用户账户
- 用户名: `user1`
- 密码: `user123`
- 权限: 只能查看自己的借阅信息

## 主要功能说明

### 用户登录
- 访问系统时会自动跳转到登录页面
- 输入用户名和密码进行登录
- 登录成功后跳转到首页

### 图书借阅
1. 点击"新书推荐"查看推荐图书
2. 在"图书借阅"页面搜索或浏览图书
3. 点击"立即借阅"按钮借阅图书
4. 每本图书借阅期限为30天

### 当前借阅
- 查看正在借阅的图书
- 显示借阅时间和应还时间
- 可以直接归还图书

### 借阅记录
- 查看所有历史借阅记录
- 显示借阅状态（借阅中、已归还、已逾期）
- 管理员可以查看所有用户的借阅记录

### 权限控制
- 使用Filter过滤器实现访问控制
- 未登录用户自动跳转到登录页面
- 非管理员用户无法访问管理后台

## 数据库表结构

### users (用户表)
- id: 主键
- username: 用户名（唯一）
- password: 密码
- name: 姓名
- student_id: 学号
- role: 角色（admin/user）
- created_at: 创建时间

### books (图书表)
- id: 主键
- title: 书名
- author: 作者
- isbn: ISBN
- publisher: 出版社
- publish_date: 出版日期
- category: 分类
- total_quantity: 总数量
- available_quantity: 可借数量
- is_recommended: 是否推荐
- created_at: 创建时间

### borrow_records (借阅记录表)
- id: 主键
- user_id: 用户ID（外键）
- book_id: 图书ID（外键）
- borrow_date: 借阅时间
- due_date: 应还时间
- return_date: 归还时间
- status: 状态（borrowed/returned）

## 界面截图

系统包含以下页面：
1. **登录页面** - 包含个人身份信息（姓名、学号）
2. **首页** - 展示系统功能模块
3. **新书推荐** - 展示推荐图书
4. **图书借阅** - 浏览和借阅图书
5. **当前借阅** - 管理当前借阅
6. **借阅记录** - 查看历史记录
7. **管理后台** - 管理员专用页面

所有页面的页脚都包含开发者的姓名和学号信息。

## 开发说明

本项目严格按照要求实现了以下功能：

✅ **用户登录模块**
- 用户登录功能
- 登录验证机制
- 注销登录功能

✅ **图书管理模块**
- 新书推荐展示
- 图书借阅功能
- 当前借阅查看
- 借阅记录查询

✅ **访问权限控制**
- 管理员权限管理
- 普通用户权限管理
- Filter过滤器实现

✅ **个人身份元素**
- 所有前端页面都包含开发者姓名：张三 (Zhang San)
- 所有前端页面都包含学号：2021001234
- 登录页面特别标注个人信息区域

## 许可证 (License)

此项目仅用于学习和教育目的。

---

**开发者:** 张三 (Zhang San)  
**学号:** 2021001234  
**日期:** 2024
