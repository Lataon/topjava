<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<html>
<head>
    <title>${meal.getId()!=null ? "Update" : "Add"} meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal.getId()!=null ? "Update" : "Add"} meal</h2>

<form method="POST" action='meals' name="frmAddMeal" >
    <input type="hidden" readonly="readonly" name="mealId"
                     value="<c:out value="${meal.getId()}" />" />
    Date Time : <input
        type="text" name="dateTime"
        value="${
            meal.getId()!=null ?
            meal.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) :
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            }" /> <br />

    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.getDescription()}" />" /> <br />

    Calories : <input type="text" name="calories"
                   value="<c:out value="${
                   meal.getId()!=null ?
                   meal.getCalories():
                   0}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
