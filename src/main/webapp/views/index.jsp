<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Index Page</title>
</head>

<body>
Список всех студентов

<table>
    <thead>
    <td>ID</td>
    <td>firstname</td>
    <td><a href="/sort/${sortType}">lastname</a></td>
    <td>gender</td>
    <td>birthdate</td>
    <td>Controls</td>
    </thead>
    <tbody>
    <a href="add-student">Add</a>
    <br/>
    <form method="post" action="/filter">
        <label>Фильтр:</label>
        <input type="text" name="filter">
        <input type="submit" value="Filter">
    </form> <a href="/">Clear filter</a>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstname}</td>
            <td>${item.lastname}</td>
            <td>${item.gender}</td>
            <td>${item.birthdate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/view/${item.id}">View</a>
                <a href="${pageContext.request.contextPath}/edit/${item.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/del/${item.id}">Delete</a>
            </td>
        </tr>

    </c:forEach>
    </tbody>

</table>

</body>

</html>