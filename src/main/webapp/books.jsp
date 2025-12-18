<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.entity.User" %>
<%@ page import="com.library.entity.Book" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    List<Book> books = (List<Book>) request.getAttribute("books");
    Boolean isRecommended = (Boolean) request.getAttribute("isRecommended");
    String keyword = (String) request.getAttribute("keyword");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= isRecommended != null && isRecommended ? "新书推荐" : "图书借阅" %> - 图书云借阅系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- 导航栏 -->
    <nav class="navbar">
        <div class="container">
            <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">
                图书云借阅系统
            </a>
            <ul class="navbar-nav">
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/bookList?action=recommended">新书推荐</a></li>
                <li><a href="${pageContext.request.contextPath}/bookList">图书借阅</a></li>
                <li><a href="${pageContext.request.contextPath}/currentBorrows">当前借阅</a></li>
                <li><a href="${pageContext.request.contextPath}/borrowHistory">借阅记录</a></li>
            </ul>
            <div class="navbar-user">
                <span class="user-info">
                    <strong><%= user.getName() %></strong> (<%= user.getStudentId() %>)
                </span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">注销</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <h2 class="card-title">
                <%= isRecommended != null && isRecommended ? "📚 新书推荐" : "📖 图书借阅" %>
            </h2>

            <!-- 搜索栏 -->
            <% if (isRecommended == null || !isRecommended) { %>
            <form action="${pageContext.request.contextPath}/bookList" method="get" class="search-bar">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" class="form-control" 
                       placeholder="搜索图书标题或作者..." 
                       value="<%= keyword != null ? keyword : "" %>">
                <button type="submit" class="btn btn-primary">搜索</button>
                <a href="${pageContext.request.contextPath}/bookList" class="btn btn-secondary">显示全部</a>
            </form>
            <% } %>

            <% if (books == null || books.isEmpty()) { %>
                <div class="alert alert-info">
                    暂无图书数据
                </div>
            <% } else { %>
                <div class="book-grid">
                    <% for (Book book : books) { %>
                        <div class="book-card">
                            <div class="book-title">
                                <%= book.getTitle() %>
                                <% if (book.getIsRecommended()) { %>
                                    <span class="recommended-badge">推荐</span>
                                <% } %>
                            </div>
                            <p class="book-author">作者：<%= book.getAuthor() %></p>
                            <div class="book-info">
                                <p>出版社：<%= book.getPublisher() != null ? book.getPublisher() : "未知" %></p>
                                <p>分类：<%= book.getCategory() %></p>
                                <p>ISBN：<%= book.getIsbn() != null ? book.getIsbn() : "未知" %></p>
                            </div>
                            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 15px;">
                                <span class="book-status <%= book.isAvailable() ? "status-available" : "status-unavailable" %>">
                                    <%= book.isAvailable() ? "可借阅 (" + book.getAvailableQuantity() + "本)" : "已借完" %>
                                </span>
                                <% if (book.isAvailable()) { %>
                                    <form action="${pageContext.request.contextPath}/borrow" method="post" 
                                          style="display: inline;">
                                        <input type="hidden" name="bookId" value="<%= book.getId() %>">
                                        <button type="submit" class="btn btn-success" 
                                                onclick="return confirm('确认借阅《<%= book.getTitle() %>》吗？');">
                                            立即借阅
                                        </button>
                                    </form>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } %>
        </div>
    </div>

    <!-- 页脚 -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 图书云借阅系统</p>
            <p class="personal-info">开发者：张三 (Zhang San) | 学号：2021001234</p>
        </div>
    </footer>
</body>
</html>
