<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 02.09.2022
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/client/login" method="post" modelAttribute="client">

    Login:
    <form:input path="email"/><br>

    Password:
    <form:password path="password"/>


    <input type="submit" value="submit">

</form:form>
</body>
</html>
