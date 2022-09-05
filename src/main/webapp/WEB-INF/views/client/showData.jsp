<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 03.09.2022
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Moje dane</title>
</head>
<body>

   <h3>Nazwa firmy: ${client.companyName}</h3>
   <h3>Imię: ${client.firstName}</h3>
   <h3>Nazwisko: ${client.surname}</h3>
   <h3>Email: ${client.email}</h3>
   <h3>Numer telefonu: ${client.phoneNumber}</h3>
   <h3>Hasło: ${client.password}</h3>
   <a href="<c:url value="/client/app/mainPage"/>">Powrót</a>
</body>
</html>
