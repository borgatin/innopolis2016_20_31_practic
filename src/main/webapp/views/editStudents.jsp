<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Index Page</title>
</head>

<body>
Добавление студента
<spring:form method="post"  modelAttribute="student" action="/update">

    ID: <spring:input readonly="true" path="id"/>   <br/>
    First Name: <spring:input path="firstname"/>   <br/>
    Last Name: <spring:input path="lastname"/>   <br/>
    Gender: <spring:input path="gender"/>   <br/>
    Birthdate: <spring:input path="birthdate"/>   <br/>
    <spring:button>Update</spring:button>
    <a href="${pageContext.request.contextPath}/"> All </a>

</spring:form>


</body>

</html>
