<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 02.09.2022
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/registerDistributor" method="post" modelAttribute="distributor">

    <%--    <form:hidden path="id"/>--%>

    First name:
    <form:input path="firstName"/><br>
    Surname:
    <form:input path="surname"/><br>
    <%--    <form:errors path="email" cssClass="error-class"/>--%>
    Phone number:
    <form:input path="phoneNumber"/><br>
    Email:
    <form:input path="email"/><br>
    <%--    <form:errors path="email" cssClass="error-class"/>--%>
    Password:
    <form:input path="password"/><br>
    <%--    <form:errors path="password" cssClass="error-class"/>--%>
    <input type="submit" value="submit">
</form:form>

</body>
</html>
