<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Dish list</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Dishes</h2>
    <form method="post" action="dishes?action=filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="dishes/create">Add Dish</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Price</th>
            <th>Restaurant</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${dishes}" var="dish">
            <jsp:useBean id="dish" scope="page" type="ru.javawebinar.topjava.to.DishWithExceed"/>
            <tr class="${dish.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${dish.dateTime.toLocalDate()} ${dish.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(dish.getDateTime())%>--%>
                        <%--${fn:replace(dish.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(dish.dateTime)}
                </td>
                <td>${dish.description}</td>
                <td>${dish.price}</td>
                <td>${dish.restaurant}</td>
                <td><a href="dishes/update?id=${dish.id}">Update</a></td>
                <td><a href="dishes/delete?id=${dish.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <h3><a href="votes?action=all">Vote</a></h3>
</section>
</body>
</html>