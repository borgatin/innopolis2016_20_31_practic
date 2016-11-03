<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>All students</title>
</head>

<body>
All students
<br/>
<a href="${pageContext.request.contextPath}/"> Home </a>

<form method="post" action="${pageContext.request.contextPath}/students/filter">
    <label>Фильтр:</label>
    <input type="text" name="filter">
    <input type="submit" value="Filter"><a href="${pageContext.request.contextPath}/students/all">Clear filter</a>
</form>
<br/>
<table border="1">
    <thead>
    <td>ID</td>
    <td>firstname</td>
    <td><a href="/students/sort/${sortType}">lastname</a></td>
    <td>gender</td>
    <td>birthdate</td>
    <td>lessons</td>
    <td>Controls</td>
    </thead>
    <tbody>
    <a href="${pageContext.request.contextPath}/students/add-student">Add</a>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstname}</td>
            <td>${item.lastname}</td>
            <td>${item.gender}</td>
            <td>${item.birthdate}</td>
            <td>${item.lessonsCount}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students/view/${item.id}">View</a>
                <a href="${pageContext.request.contextPath}/students/edit/${item.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/students/del/${item.id}">Delete</a>
            </td>
        </tr>

    </c:forEach>
    </tbody>

</table>

</body>

</html>