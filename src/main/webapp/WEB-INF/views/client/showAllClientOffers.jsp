<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 08.09.2022
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <thead bgcolor="red">
    <th>ID</th>
    <th>Data początkowa</th>
    <th>Data Końcowa</th>
    <th>Nazwa</th>
    <th>Ilość ulotek</th>
    <th>Status oferty</th>
<%--    <th>author</th>--%>
<%--    <th>publisher</th>--%>
<%--    <th>type</th>--%>

    </thead>
    <tbody>
    <c:forEach items="${offers}" var="offer">
        <tr>
            <td><c:out value="${offer.id}"/></td>
            <td><c:out value="${offer.earliestDistributionDate}"/></td>
            <td><c:out value="${offer.latestDistributionDate}"/></td>
            <td><c:out value="${offer.orderRegion.name}"/></td>
            <td><c:out value="${offer.quantity}"/></td>
            <td><c:out value="${offer.status}"/></td>
<%--            <td><c:out value="${book.publisher}"/></td>--%>
<%--            <td><c:out value="${book.type}"/></td>--%>
            <td> <a href="<c:url value="/offer/showOffer/${offer.id}"/>">Pokaż ofertę</a></td>

<%--            <td><a href="<c:url value="/admin/books/edit/${book.id}"/>">Edytuj</a></td>--%>
<%--            <td><a href="<c:url value="/admin/books/delete/${book.id}"/>">Usuń</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="<c:url value="/client/app/allOffers"/>">Wszystkie</a>
<a href="<c:url value="/client/app/allOffers/awaiting"/>">Oczekujące</a>
<a href="<c:url value="/client/app/allOffers/active"/>">Aktywne</a>
<a href="<c:url value="/client/app/allOffers/finished"/>">Zakończone</a><br>
<a href="<c:url value="/client/app/mainPage"/>">Powrót</a>
</body>
</html>
