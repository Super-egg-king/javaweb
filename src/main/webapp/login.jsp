<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录 - 图书云借阅系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h2 class="login-title">图书云借阅系统</h2>
            <p style="text-align: center; color: #666; margin-bottom: 20px;">Book Cloud Lending System</p>
            
            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" class="form-control" 
                           placeholder="请输入用户名" required>
                </div>
                
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" id="password" name="password" class="form-control" 
                           placeholder="请输入密码" required>
                </div>
                
                <button type="submit" class="btn btn-primary" style="width: 100%;">登录</button>
            </form>
            
            <div style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #eee;">
                <p style="text-align: center; color: #888; font-size: 14px;">
                    测试账户：<br>
                    管理员：admin / admin123<br>
                    普通用户：user1 / user123
                </p>
            </div>
            
            <!-- 个人身份信息 -->
            <div style="margin-top: 30px; padding: 15px; background-color: #f8f9fa; border-radius: 5px; text-align: center;">
                <p class="personal-info" style="font-size: 14px; color: #667eea; margin: 5px 0;">
                    <strong>开发者：</strong>张三 (Zhang San)
                </p>
                <p class="personal-info" style="font-size: 14px; color: #667eea; margin: 5px 0;">
                    <strong>学号：</strong>2021001234
                </p>
            </div>
        </div>
    </div>
</body>
</html>
