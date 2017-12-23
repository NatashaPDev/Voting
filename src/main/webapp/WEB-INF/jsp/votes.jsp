<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Vote list</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Votes</h2>
    <hr/>
    <a href="votes?action=create">Add Vote</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Restaurant</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${votes}" var="vote">
            <jsp:useBean id="vote" scope="page" type="ru.javawebinar.topjava.to.VoteTO"/>
            <tr >
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(vote.dateTime)}
                </td>
                <td>${vote.restaurant}</td>
                <td><a href="votes?action=update&id=${vote.id}">Update</a></td>
                <td><a href="votes?action=delete&id=${vote.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>