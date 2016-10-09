<%--
  Created by IntelliJ IDEA.
  User: nzhou026
  Date: 10/9/2016
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>博文详情</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
    <div class="container">
        <h1>SpringMVC 博文详情</h1>
        <hr/>

        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <td>${blog.id}</td>
            </tr>
            <tr>
                <th>Title</th>
                <td>${blog.title}</td>
            </tr>
            <tr>
                <th>Author</th>
                <td>${blog.userByUserId.nickname}, ${blog.userByUserId.firstname} ${blog.userByUserId.lastname}</td>
            </tr>
            <tr>
                <th>Content</th>
                <td>${blog.content}</td>
            </tr>
            <tr>
                <th>Publish Date</th>
                <td><fmt:formatDate value="${blog.pubDate}" pattern="yyyy年MM月dd日"/></td>
            </tr>
        </table>
    </div>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
