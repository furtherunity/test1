<%--
  Created by IntelliJ IDEA.
  User: 王梓灿
  Date: 2023/3/20
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>、
    <link rel="stylesheet" href="login.css" />
</head>
<body>
<form method="post" action="login_yz.jsp">
<div class="container">
    <div>
        <h2>登录</h2>
    </div>
    <div class="register">
        <span>
            <i></i>
            <input type="text" placeholder="用户" name="username"/>
        </span><br />
        <span>
            <i></i>
            <input type="password" placeholder="密码" name="userpwd"/>
        </span><br />
        <button>登 录</button>
    </div>
</div>
</form>
</body>
</html>
