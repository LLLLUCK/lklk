<%--
  Created by IntelliJ IDEA.
  User: Liuke
  Date: 2021/11/26
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>hello</title>
</head>
<body>
<h1>hello,${sessionScope.user.username}</h1>
<div>
    <p>${CRUDmsg}</p>
    <a href="${pageContext.request.contextPath}/user/welcome.do">返回欢迎页面</a>
</div>
    <form action="${pageContext.request.contextPath}/user/upload2.do" enctype="multipart/form-data" method="post">
        上传图片1:<input type="file" name="images"><br>
        上传图片2:<input type="file" name="images"><br>
        上传图片3:<input type="file" name="images"><br>
        <input type="submit" value="上传">
    </form>
</div>

<h2>点击图片下载</h2>
<div>
    <a href="${pageContext.request.contextPath}/user/load.do/8.jpg">
        <img src="${pageContext.request.contextPath}/images/8.jpg" width="400px"></a>

    <a href="${pageContext.request.contextPath}/user/load.do/9.jpg">
        <img src="${pageContext.request.contextPath}/images/9.jpg" width="400px"></a>

    <a href="${pageContext.request.contextPath}/user/load.do/10.jpg">
        <img src="${pageContext.request.contextPath}/images/10.jpg" width="400px"></a>

    < <a href="${pageContext.request.contextPath}/user/load.do/11.jpg">
    <img src="${pageContext.request.contextPath}/images/11.jpg" width="400px"></a>
</div>
</body>
</html>
