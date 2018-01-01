<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<body>


<section>
    <jsp:useBean id="dish" type="ru.natashapetrenko.voting.model.Dish" scope="request"/>
    <h3>Dish</h3>
    <hr>
    <form method="post" action="update">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="datetime-local" value="${dish.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${dish.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Restaurant:</dt>
            <dd><input type="text" value="${dish.restaurant}" size=40 name="restaurant" required></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="number" value="${dish.price}" name="price" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>

</body>
</html>
