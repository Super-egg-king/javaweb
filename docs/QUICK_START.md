# 图书云借阅系统 - 快速开始指南

## 系统概述

这是一个完整的图书云借阅系统，包含用户登录、图书管理、借阅管理等功能。

**开发者：张三 (学号: 2021001)**

## 快速开始

### 1. 环境要求

- **JDK**: 1.8 或更高版本
- **Maven**: 3.6 或更高版本
- **MySQL**: 8.0 或更高版本
- **IDE**: IntelliJ IDEA（推荐）或 Eclipse

### 2. 数据库设置

#### 方式一：手动创建数据库

```bash
mysql -u root -p
```

在 MySQL 命令行中执行：

```sql
CREATE DATABASE library_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 方式二：使用提供的 SQL 脚本

```bash
mysql -u root -p < docs/database.sql
```

### 3. 配置数据库连接

编辑 `src/main/resources/application.yml`，修改数据库密码：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/library_system?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
    username: root
    password: your_password_here  # 修改为你的MySQL密码
```

### 4. 启动项目

#### 使用 Maven 命令行

```bash
cd javaweb
mvn spring-boot:run
```

#### 使用 IntelliJ IDEA

1. 打开项目
2. 等待 Maven 依赖下载完成
3. 找到 `BookLendingSystemApplication.java`
4. 点击运行按钮或右键选择 Run

#### 使用 Eclipse

1. 导入为 Maven 项目
2. 更新项目依赖
3. 找到 `BookLendingSystemApplication.java`
4. Run As > Java Application

### 5. 访问系统

启动成功后，在浏览器中访问：

```
http://localhost:8080
```

### 6. 登录系统

系统会自动创建两个测试账号：

**管理员账号：**
- 用户名: `admin`
- 密码: `admin123`
- 权限: 可管理图书、查看所有借阅记录

**普通用户账号：**
- 用户名: `user`
- 密码: `user123`
- 权限: 可借阅图书、查看个人借阅记录

## 主要功能

### 管理员功能
- ✅ 添加/编辑/删除图书
- ✅ 设置新书推荐
- ✅ 查看所有用户的借阅记录
- ✅ 管理图书库存

### 普通用户功能
- ✅ 查看图书列表
- ✅ 查看新书推荐
- ✅ 借阅图书
- ✅ 归还图书
- ✅ 查看当前借阅
- ✅ 查看借阅历史

## 系统截图位置

登录后，您将看到：
1. **登录页面** - 包含开发者姓名和学号
2. **主页** - 功能菜单（根据权限显示不同选项）
3. **图书列表** - 显示所有可借阅图书
4. **新书推荐** - 显示推荐图书
5. **当前借阅** - 显示用户当前借阅的图书
6. **借阅记录** - 显示历史借阅记录
7. **管理页面** - 管理员专用（添加/编辑图书等）

## 常见问题

### Q1: 启动时报错 "Access denied for user"
**A**: 检查 `application.yml` 中的数据库用户名和密码是否正确。

### Q2: 启动时报错 "Unknown database 'library_system'"
**A**: 确保已创建 `library_system` 数据库。

### Q3: 依赖下载慢
**A**: 可以配置 Maven 使用国内镜像，编辑 `~/.m2/settings.xml`。

### Q4: 数据库表未自动创建
**A**: 确保 `application.yml` 中 `spring.jpa.hibernate.ddl-auto` 设置为 `update`。

## 技术支持

如有问题，请检查：
1. MySQL 服务是否启动
2. 端口 8080 是否被占用
3. 控制台输出的错误信息

## 项目文件结构

```
javaweb/
├── src/main/java/com/library/
│   ├── BookLendingSystemApplication.java  # 主应用
│   ├── controller/                        # 控制器层
│   ├── service/                           # 业务逻辑层
│   ├── repository/                        # 数据访问层
│   ├── entity/                            # 实体类
│   └── config/                            # 配置类
├── src/main/resources/
│   ├── application.yml                    # 配置文件
│   └── templates/                         # 前端页面
├── docs/                                  # 文档
└── pom.xml                                # Maven配置
```

## 许可证

本项目仅用于教学和学习目的。
