<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
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
    <form method="post" action="/lessons/${lesson.id}/add-student">
    <select name="student">
        <c:forEach items="${list}" var="student">
            <option value="${student.id}">${student.firstname} ${student.lastname}</option>
        </c:forEach>
    </select>
        <input type="submit" value="Add">
    </form>

    </body>
</html>
