<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student by lesson</title>
</head>
<body>


Student by lesson ${lesson.topic}
<br/>
<a href="${pageContext.request.contextPath}/"> Home </a>
<br/>



<table>
    <thead>
    <td>ID</td>
    <td>firstname</td>
    <td>lastname</td>
    <td>gender</td>
    <td>birthdate</td>
    <td>Controls</td>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstname}</td>
            <td>${item.lastname}</td>
            <td>${item.gender}</td>
            <td>${item.birthdate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/lessons/${lesson.id}/del/${item.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    ${btnAddStudents}
    </tbody>

</table>
</body>
</html>
