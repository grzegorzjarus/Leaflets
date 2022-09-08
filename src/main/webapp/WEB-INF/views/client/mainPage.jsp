
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Strona główna aplikacji</title>
</head>
<body>
<a href="<c:url value="/client/app/showData"/>">Moje dane</a><br>
<a href="<c:url value="/client/app/editData"/>">Edycja danych</a><br>
<a href="<c:url value="/offer/create1"/>">Dodaj nowe zamówienie</a><br>
<a href="<c:url value="/client/app/allOffers"/>">Moje zamówienia</a><br><br>
<a href="<c:url value="/client/app/logout"/>">Wyloguj</a><br>
<%--<form action="/client/showData">--%>
<%--    <input type="submit" value="Moje dane" />--%>
<%--</form><br>--%>

<%--<form action="/client/editData">--%>
<%--    <input type="submit" value="Edycja danych" />--%>
<%--</form>--%>

<%--&lt;%&ndash;<form action="/client/offer/add">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="submit" value="Dodaj nowe zamówienie" />&ndash;%&gt;--%>
<%--&lt;%&ndash;</form><br>&ndash;%&gt;--%>
<%--<form action="/leaflet">--%>
<%--    <input type="submit" value="Dodaj nowe zamówienie" />--%>
<%--</form><br>--%>

<%--<form action="/client/offer/all">--%>
<%--    <input type="submit" value="Moje zamówienia" />--%>
<%--</form>--%>

<%--<form action="/client/logout">--%>
<%--    <input type="submit" value="Wyloguj" />--%>
<%--</form>--%>
</body>
</html>
