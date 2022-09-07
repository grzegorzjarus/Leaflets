<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 07.09.2022
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Ilość ulotek: ${offer.quantity}</h3>
<h3>Waga 1000 sztuk: ${offer.kilogramPer1000Pieces}</h3>
<h3>Szerokość ulotki: ${offer.leafletWidth}</h3>
<h3>Wysokość ulotki: ${offer.leafletHeight}</h3>
<h3>Data początkowa: ${offer.earliestDistributionDate}</h3>
<h3>Data końcowa: ${offer.latestDistributionDate}</h3>

<h3>Rejon: ${offer.orderRegion}</h3>
<a href="<c:url value="/client/app/mainPage"/>">Powrót</a>
</body>
</html>
