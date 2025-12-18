<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !user.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理后台 - 图书云借阅系统</title>
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
                <li><a href="${pageContext.request.contextPath}/admin/index.jsp">管理后台</a></li>
            </ul>
            <div class="navbar-user">
                <span class="user-info">
                    <strong><%= user.getName() %></strong> (<%= user.getStudentId() %>)
                    <span style="color: #dc3545; margin-left: 5px;">[管理员]</span>
                </span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">注销</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <h2 class="card-title">🔧 管理后台</h2>
            
            <div style="margin: 30px 0;">
                <h3 style="color: #667eea; margin-bottom: 15px;">管理功能</h3>
                <div class="book-grid">
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📊 借阅统计</h3>
                        <p style="color: #666; margin-bottom: 15px;">查看所有用户的借阅记录和统计信息</p>
                        <a href="${pageContext.request.contextPath}/borrowHistory" 
                           class="btn btn-primary">查看记录</a>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">👥 用户管理</h3>
                        <p style="color: #666; margin-bottom: 15px;">管理系统用户信息</p>
                        <button class="btn btn-secondary" disabled>开发中</button>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">📚 图书管理</h3>
                        <p style="color: #666; margin-bottom: 15px;">添加、编辑、删除图书信息</p>
                        <button class="btn btn-secondary" disabled>开发中</button>
                    </div>
                    
                    <div class="book-card">
                        <h3 style="color: #667eea; margin-bottom: 10px;">⚙️ 系统设置</h3>
                        <p style="color: #666; margin-bottom: 15px;">配置系统参数</p>
                        <button class="btn btn-secondary" disabled>开发中</button>
                    </div>
                </div>
            </div>

            <div style="margin-top: 30px; padding: 20px; background-color: #fff3cd; border-radius: 8px; border: 1px solid #ffc107;">
                <h4 style="color: #856404; margin-bottom: 10px;">⚠️ 管理员权限说明</h4>
                <ul style="line-height: 2; color: #856404;">
                    <li>管理员可以查看所有用户的借阅记录</li>
                    <li>管理员权限受到严格保护，只有管理员账户可以访问此页面</li>
                    <li>请妥善保管管理员账户信息</li>
                </ul>
            </div>
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
