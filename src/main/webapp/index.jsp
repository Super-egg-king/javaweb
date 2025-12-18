<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页 - 图书云借阅系统</title>
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
                <% if (user.isAdmin()) { %>
                <li><a href="${pageContext.request.contextPath}/admin/index.jsp">管理后台</a></li>
                <% } %>
            </ul>
            <div class="navbar-user">
                <span class="user-info">
                    <strong><%= user.getName() %></strong> 
                    (<%= user.getStudentId() %>)
                    <% if (user.isAdmin()) { %>
                    <span style="color: #dc3545; margin-left: 5px;">[管理员]</span>
                    <% } %>
                </span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">注销</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <h1 class="card-title">欢迎使用图书云借阅系统</h1>
            
            <div style="margin: 30px 0;">
                <h3 style="color: #667eea; margin-bottom: 15px;">系统功能</h3>
                <div class="book-grid">
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📚 新书推荐</h3>
                        <p style="color: #666; margin-bottom: 15px;">查看最新推荐的优质图书</p>
                        <a href="${pageContext.request.contextPath}/bookList?action=recommended" 
                           class="btn btn-primary">前往查看</a>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📖 图书借阅</h3>
                        <p style="color: #666; margin-bottom: 15px;">浏览并借阅图书馆藏书</p>
                        <a href="${pageContext.request.contextPath}/bookList" 
                           class="btn btn-primary">开始借阅</a>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📋 当前借阅</h3>
                        <p style="color: #666; margin-bottom: 15px;">查看和管理当前借阅的图书</p>
                        <a href="${pageContext.request.contextPath}/currentBorrows" 
                           class="btn btn-primary">查看详情</a>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📜 借阅记录</h3>
                        <p style="color: #666; margin-bottom: 15px;">查看历史借阅记录</p>
                        <a href="${pageContext.request.contextPath}/borrowHistory" 
                           class="btn btn-primary">查看记录</a>
                    </div>
                </div>
            </div>

            <div style="margin-top: 30px; padding: 20px; background-color: #f8f9fa; border-radius: 8px;">
                <h3 style="color: #667eea; margin-bottom: 15px;">使用说明</h3>
                <ul style="line-height: 2; color: #666;">
                    <li>点击"新书推荐"查看图书馆最新推荐的优质图书</li>
                    <li>在"图书借阅"页面可以搜索和借阅图书</li>
                    <li>在"当前借阅"页面查看正在借阅的图书并进行归还</li>
                    <li>在"借阅记录"页面查看所有历史借阅记录</li>
                    <li>每本图书借阅期限为30天</li>
                    <% if (user.isAdmin()) { %>
                    <li style="color: #dc3545;">您是管理员，可以访问"管理后台"查看所有借阅记录</li>
                    <% } %>
                </ul>
            </div>
        </div>
    </div>

    <!-- 页脚 - 包含个人身份信息 -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 图书云借阅系统 Book Cloud Lending System</p>
            <p class="personal-info">
                开发者：张三 (Zhang San) | 学号：2021001234
            </p>
        </div>
    </footer>
</body>
</html>
