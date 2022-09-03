<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Edycja danych</title>
</head>
<body>
<%--<c:url var="edit_url" value="/admin/books/edit"/>--%>
<form:form method="post" modelAttribute="client" action="/client/editData">
  <form:hidden path="id"/>
  <form:input path="companyName"/>
  <form:errors path="companyName"/><br/>
  <form:input path="email"/>
  <form:errors path="email"/><br/>
  <form:input path="firstName"/>
  <form:errors path="firstName"/><br/>
  <form:input path="surname"/>
  <form:errors path="surname"/><br/>
  <form:input path="phoneNumber"/>
  <form:errors path="phoneNumber"/><br/>
  <form:password path="password"/>
  <form:errors path="password"/><br/>
  <input type="submit" value="Save">
</form:form>
<a href="<c:url value="/client/mainPage"/>">Powrót</a>

</body>
</html>