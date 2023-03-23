<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: 王梓灿
  Date: 2023/3/20
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="login.css" />
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String username=request.getParameter("username");
    String userpwd=request.getParameter("userpwd");
    String driver="com.mysql.cj.jdbc.Driver";//驱动程序
    String URL="jdbc:mysql://localhost:3306/buy?server Timezone=UTC";//URL指向要访问的数据库名
    String user="root";//数据库用户名和密码
    String password="123457";
    try {
        Class.forName(driver);//将驱动类加载到内存
        Connection conn= DriverManager.getConnection(URL, user, password);//获取连接
        Statement stmt=conn.createStatement();//通过连接创建statement
        ResultSet rs=stmt.executeQuery("select * from user where username='"+username+"' and password='"+userpwd+"'");//执行查询语句并保存结果
        if(rs!=null)
            out.print("<br>");
            out.print("<br>");
            out.print("<br>");
            out.print("<h2 class=\"register container\">登录成功</h2>");
            out.print("<br>");
            out.print("<a href='modelPanel.jsp'><h2 class=\"register container\">进入答题模块</h2></a>");
            if(stmt!=null)stmt.close();
        else out.print("登录失败");
        if(conn!=null)conn.close();
    }catch(ClassNotFoundException e){
        System.out.println("数据驱动错误");
    }catch(SQLException e) {
        e.printStackTrace();
    }
%>
</body>
</html>
