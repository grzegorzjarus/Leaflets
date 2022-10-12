
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/offer/create1" method="post" modelAttribute="offer">
  Data początkowa:
  <input type="date" name="earliestDistributionDate">
  <form:errors path="earliestDistributionDate" cssClass="error-class"/>
  <br>
  Data końcowa:
  <input type="date" name="latestDistributionDate">
  <form:errors path="latestDistributionDate" cssClass="error-class"/>
  <br>
  Ilość:
  <input type="text" name="quantity">
  <form:errors path="quantity" cssClass="error-class"/>
  <br>
  Szerokość ulotki:
  <input type="text" name="leafletWidth">
  <br>
  Wysoość ulotki:
  <input type="text" name="leafletHeight">
  <br>
  Waga 1000szt:
  <input type="text" name="kilogramPer1000Pieces">
  <br>
  Nazwa:
  <input type="text" name="orderRegion.name">
  <br>
  Uwagi:
  <input type="text" name="sometext">
  <br>
  <input type="submit" value="Dalej">
</form:form>

</body>
</html>
