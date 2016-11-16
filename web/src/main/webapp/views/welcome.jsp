<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Index Page</title>
</head>

<body>
<a href="${pageContext.request.contextPath}/students/all">All students</a>
<br/>
<a href="${pageContext.request.contextPath}/lessons/all">All lessons</a>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>

</body>

</html>