<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <link rel="stylesheet" href="resources/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
<c:forEach var="m" items="${meals}">
    <tr class="${m.isExcess() ? "red" : "green"}">
        <td>${m.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
        <td>${m.getDescription()}</td>
        <td>${m.getCalories()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
