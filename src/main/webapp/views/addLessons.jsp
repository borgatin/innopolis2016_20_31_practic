<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Add lesson</title>
</head>

<body>
Adding lesson
<spring:form method="post"  modelAttribute="lesson" action="/lessons/add">
    Topic: <spring:input  path="topic"/>   <br/>
    Description: <spring:input path="description"/>   <br/>
    Duration: <spring:input path="duration"/>   <br/>
    Date: <spring:input path="date"/>   <br/>
    <spring:button>Add</spring:button>
    <a href="${pageContext.request.contextPath}/lessons/all"> All </a>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</spring:form>

</body>

</html>
