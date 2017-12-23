<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<body>
<section>
    <form method="post" action="users">
        <b>Meals of&nbsp;</b>
        <select name="userId">
    <option value="100000" selected>User</option>
    <option value="100001">Admin</option>
</select>
        <button type="submit">Select</button>
</form>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="meals">Meals</a></li>
</ul>
</section>

</body>
</html>