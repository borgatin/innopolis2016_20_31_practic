<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Add student</title>
</head>

<body>
Adding student
<spring:form method="post"  modelAttribute="student" action="/students/add">
    First Name: <spring:input  path="firstname"/>   <br/>
    Last Name: <spring:input path="lastname"/>   <br/>
    Gender: <spring:input path="gender"/>   <br/>
    Birthdate: <spring:input path="birthdate"/>   <br/>
    <spring:button>Add</spring:button>
    <br/>
    <a href="${pageContext.request.contextPath}/students/all"> All </a><br/>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</spring:form>

</body>

</html>
