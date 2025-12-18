# 图书云借阅系统部署指南 (Deployment Guide)

## 一、系统要求

### 必需软件
- **JDK**: 1.8 或更高版本
- **Maven**: 3.x
- **MySQL**: 8.0 或更高版本
- **Tomcat**: 9.x 或更高版本

### 可选软件
- **IDE**: IntelliJ IDEA 或 Eclipse (用于开发)

## 二、数据库配置

### 1. 安装MySQL数据库

确保MySQL已安装并正在运行。

### 2. 创建数据库

使用命令行或MySQL Workbench执行以下命令：

```sql
CREATE DATABASE library_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. 导入初始数据

执行 `src/main/resources/init.sql` 文件中的SQL语句：

```bash
mysql -u root -p library_system < src/main/resources/init.sql
```

或者在MySQL客户端中手动执行：

```sql
USE library_system;
-- 然后复制粘贴 init.sql 中的所有内容并执行
```

这将创建以下内容：
- 用户表 (users)
- 图书表 (books)
- 借阅记录表 (borrow_records)
- 默认管理员账户
- 测试用户账户
- 示例图书数据

### 4. 修改数据库连接配置

编辑 `src/main/resources/db.properties` 文件：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/library_system?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username=root
jdbc.password=你的MySQL密码
```

**重要提示**: 请将 `jdbc.password` 修改为你的MySQL实际密码。

## 三、项目构建

### 方式一：使用Maven命令行

1. 进入项目根目录：
```bash
cd javaweb
```

2. 清理并编译项目：
```bash
mvn clean compile
```

3. 打包项目为WAR文件：
```bash
mvn clean package
```

构建成功后，会在 `target` 目录下生成 `book-lending-system.war` 文件。

### 方式二：使用IDE

#### IntelliJ IDEA
1. 打开项目 (File -> Open -> 选择项目目录)
2. 等待Maven依赖自动下载
3. 点击右侧 Maven 面板
4. 执行 Lifecycle -> clean -> package

#### Eclipse
1. 导入项目 (File -> Import -> Existing Maven Projects)
2. 右键项目 -> Run As -> Maven build
3. Goals 输入: clean package
4. 点击 Run

## 四、部署到Tomcat

### 方式一：手动部署

1. 将 `target/book-lending-system.war` 复制到Tomcat的 `webapps` 目录

2. 启动Tomcat服务器：
   - Windows: 运行 `bin\startup.bat`
   - Linux/Mac: 运行 `bin/startup.sh`

3. 等待Tomcat自动解压WAR文件

4. 访问应用：
   ```
   http://localhost:8080/book-lending-system/
   ```

### 方式二：通过Tomcat Manager部署

1. 访问Tomcat Manager：
   ```
   http://localhost:8080/manager/html
   ```

2. 在 "WAR file to deploy" 部分选择 `book-lending-system.war` 文件

3. 点击 "Deploy" 按钮

4. 部署完成后，在应用列表中找到 `book-lending-system` 并点击链接访问

### 方式三：使用IDE集成部署

#### IntelliJ IDEA Ultimate
1. 配置Tomcat服务器 (Run -> Edit Configurations)
2. 添加 Tomcat Server -> Local
3. 在 Deployment 标签中添加 Artifact
4. 点击 Run 按钮启动

#### Eclipse
1. 配置Tomcat (Window -> Preferences -> Server -> Runtime Environments)
2. 右键项目 -> Run As -> Run on Server
3. 选择已配置的Tomcat服务器

## 五、访问系统

### 默认访问地址
```
http://localhost:8080/book-lending-system/
```

### 测试账户

#### 管理员账户
- **用户名**: admin
- **密码**: admin123
- **权限**: 可以查看所有用户的借阅记录，访问管理后台

#### 普通用户账户
- **用户名**: user1
- **密码**: user123
- **权限**: 只能查看自己的借阅信息

## 六、功能验证

### 1. 用户登录
- 访问系统首页，应该自动跳转到登录页面
- 使用测试账户登录
- 登录成功后跳转到系统首页

### 2. 新书推荐
- 点击导航栏的"新书推荐"
- 查看系统推荐的图书列表
- 标记为推荐的图书会显示"推荐"标签

### 3. 图书借阅
- 点击导航栏的"图书借阅"
- 浏览图书列表
- 使用搜索功能查找特定图书
- 点击"立即借阅"按钮借阅图书

### 4. 当前借阅
- 点击导航栏的"当前借阅"
- 查看正在借阅的图书
- 查看借阅时间和应还时间
- 点击"归还"按钮归还图书

### 5. 借阅记录
- 点击导航栏的"借阅记录"
- 查看所有历史借阅记录
- 查看借阅状态（借阅中、已归还、已逾期）

### 6. 管理员功能
- 使用管理员账户登录
- 访问"管理后台"
- 查看所有用户的借阅记录

### 7. 注销登录
- 点击右上角的"注销"按钮
- 系统退出登录，跳转到登录页面

## 七、常见问题解决

### 1. 数据库连接失败

**问题**: 启动时出现数据库连接错误

**解决方案**:
- 检查MySQL服务是否正在运行
- 验证 `db.properties` 中的数据库配置是否正确
- 确保数据库 `library_system` 已创建
- 检查MySQL用户名和密码是否正确
- 确认MySQL端口号（默认3306）是否正确

### 2. 端口冲突

**问题**: Tomcat启动失败，提示端口被占用

**解决方案**:
- 修改Tomcat的 `conf/server.xml` 文件
- 将默认端口8080改为其他端口（如8081）
- 或者关闭占用8080端口的其他程序

### 3. WAR包部署失败

**问题**: WAR文件没有自动解压或应用无法访问

**解决方案**:
- 检查Tomcat日志文件 (`logs/catalina.out` 或 `logs/localhost.log`)
- 确保Tomcat有足够的权限访问webapps目录
- 尝试删除webapps目录下的旧版本并重新部署
- 检查是否有编译错误或依赖缺失

### 4. 页面显示乱码

**问题**: 中文字符显示为乱码

**解决方案**:
- 确保所有JSP文件的编码为UTF-8
- 检查Tomcat的 `conf/server.xml` 中的URIEncoding设置
- 在Connector标签中添加: `URIEncoding="UTF-8"`

### 5. Maven依赖下载失败

**问题**: 编译时提示无法下载依赖

**解决方案**:
- 检查网络连接
- 配置Maven使用国内镜像（如阿里云镜像）
- 在 `settings.xml` 中添加镜像配置：
```xml
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>central</mirrorOf>
</mirror>
```

## 八、开发和调试

### 开发模式

在开发模式下，可以使用IDE的热部署功能：

1. **IntelliJ IDEA**:
   - 使用 Tomcat 配置的 "Update classes and resources" 功能
   - 修改代码后自动重新加载

2. **Eclipse**:
   - 启用 "Automatically publish" 选项
   - 保存文件后自动同步到服务器

### 调试模式

使用IDE的调试功能：

1. 配置Tomcat以调试模式启动
2. 在代码中设置断点
3. 启动调试会话
4. 访问相应的URL触发断点

### 日志查看

- **Tomcat日志**: `tomcat/logs/catalina.out`
- **应用日志**: 查看控制台输出或配置日志框架

## 九、性能优化建议

1. **数据库连接池**: 已配置Apache DBCP2连接池
2. **Session管理**: Session超时时间设置为30分钟
3. **静态资源缓存**: 可以在生产环境配置静态资源缓存
4. **数据库索引**: 已在关键字段上创建索引

## 十、安全建议

1. **生产环境配置**:
   - 修改默认管理员密码
   - 使用强密码策略
   - 启用HTTPS
   - 定期备份数据库

2. **数据库安全**:
   - 不要使用root账户连接数据库
   - 创建专用的数据库用户
   - 限制数据库用户权限

3. **应用安全**:
   - 启用Tomcat安全配置
   - 配置防火墙规则
   - 定期更新依赖库

## 十一、联系方式

如有问题，请联系：
- **开发者**: 张三 (Zhang San)
- **学号**: 2021001234

---

**注意**: 本系统为学习项目，建议在开发和测试环境中使用。
