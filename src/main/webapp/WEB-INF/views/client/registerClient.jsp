

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/client/register" method="post" modelAttribute="client">

    <%--    <form:hidden path="id"/>--%>
    Company name:
    <form:input path="companyName"/>
    <form:errors path="companyName" cssClass="error-class"/><br>
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
    <form:password path="password"/><br>
    <%--    <form:errors path="password" cssClass="error-class"/>--%>
    <input type="submit" value="submit">
</form:form>

</body>
</html>
