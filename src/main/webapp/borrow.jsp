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
    <title>当前借阅 - 图书云借阅系统</title>
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
            <h2 class="card-title">📋 当前借阅</h2>

            <% if (records == null || records.isEmpty()) { %>
                <div class="alert alert-info">
                    您当前没有借阅任何图书
                </div>
            <% } else { %>
                <table class="table">
                    <thead>
                        <tr>
                            <th>书名</th>
                            <th>作者</th>
                            <th>借阅时间</th>
                            <th>应还时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (BorrowRecord record : records) { %>
                            <tr>
                                <td><%= record.getBookTitle() %></td>
                                <td><%= record.getBookAuthor() %></td>
                                <td><%= sdf.format(record.getBorrowDate()) %></td>
                                <td><%= sdf.format(record.getDueDate()) %></td>
                                <td>
                                    <% 
                                        long now = System.currentTimeMillis();
                                        long dueTime = record.getDueDate().getTime();
                                        boolean isOverdue = now > dueTime;
                                    %>
                                    <span class="book-status <%= isOverdue ? "status-unavailable" : "status-available" %>">
                                        <%= isOverdue ? "已逾期" : "借阅中" %>
                                    </span>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/return" method="post" 
                                          style="display: inline;">
                                        <input type="hidden" name="recordId" value="<%= record.getId() %>">
                                        <button type="submit" class="btn btn-success" 
                                                onclick="return confirm('确认归还《<%= record.getBookTitle() %>》吗？');">
                                            归还
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
            
            <div style="margin-top: 20px;">
                <a href="${pageContext.request.contextPath}/bookList" class="btn btn-primary">继续借阅</a>
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
