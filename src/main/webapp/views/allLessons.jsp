<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All lessons</title>
</head>
<body>


All lessons
<br/>
<a href="${pageContext.request.contextPath}/"> Home </a>

<form method="post" action="${pageContext.request.contextPath}/lessons/filter">
    <label>Фильтр:</label>
    <input type="text" name="filter">
    <input type="submit" value="Filter">
</form> <a href="${pageContext.request.contextPath}/lessons/all">Clear filter</a>
<br/>
<table>
    <thead>
    <td>ID</td>
    <td><a href="/lessons/sort/${sortType}">topic</a></td>
    <td>description</td>
    <td>duration</td>
    <td>date</td>
    <td>Controls</td>
    </thead>
    <tbody>
    <a href="${pageContext.request.contextPath}/lessons/add-lesson">Add</a>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.topic}</td>
            <td>${item.description}</td>
            <td>${item.duration}</td>
            <td>${item.date}</td>
            <td>
                <a href="${pageContext.request.contextPath}/lessons/view/${item.id}">View</a>
                <a href="${pageContext.request.contextPath}/lessons/edit/${item.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/lessons/del/${item.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/lessons/edit-students/${item.id}">Students</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
