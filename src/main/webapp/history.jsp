<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.entity.User" %>
<%@ page import="com.library.entity.BorrowRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    
    List<BorrowRecord> records = (List<BorrowRecord>) request.getAttribute("records");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>借阅记录 - 图书云借阅系统</title>
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
                    <strong><%= user.getName() %></strong> (<%= user.getStudentId() %>)
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
            <h2 class="card-title">
                📜 借阅记录
                <% if (user.isAdmin()) { %>
                    <span style="font-size: 16px; color: #dc3545;">(所有用户)</span>
                <% } %>
            </h2>

            <% if (records == null || records.isEmpty()) { %>
                <div class="alert alert-info">
                    暂无借阅记录
                </div>
            <% } else { %>
                <table class="table">
                    <thead>
                        <tr>
                            <% if (user.isAdmin()) { %>
                            <th>借阅人</th>
                            <% } %>
                            <th>书名</th>
                            <th>作者</th>
                            <th>借阅时间</th>
                            <th>应还时间</th>
                            <th>归还时间</th>
                            <th>状态</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (BorrowRecord record : records) { %>
                            <tr>
                                <% if (user.isAdmin()) { %>
                                <td><%= record.getUserName() %></td>
                                <% } %>
                                <td><%= record.getBookTitle() %></td>
                                <td><%= record.getBookAuthor() %></td>
                                <td><%= sdf.format(record.getBorrowDate()) %></td>
                                <td><%= sdf.format(record.getDueDate()) %></td>
                                <td>
                                    <%= record.getReturnDate() != null ? sdf.format(record.getReturnDate()) : "-" %>
                                </td>
                                <td>
                                    <% 
                                        String statusText = "";
                                        String statusClass = "";
                                        if ("returned".equals(record.getStatus())) {
                                            statusText = "已归还";
                                            statusClass = "status-available";
                                        } else if ("borrowed".equals(record.getStatus())) {
                                            long now = System.currentTimeMillis();
                                            long dueTime = record.getDueDate().getTime();
                                            if (now > dueTime) {
                                                statusText = "已逾期";
                                                statusClass = "status-unavailable";
                                            } else {
                                                statusText = "借阅中";
                                                statusClass = "status-available";
                                            }
                                        }
                                    %>
                                    <span class="book-status <%= statusClass %>">
                                        <%= statusText %>
                                    </span>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
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
