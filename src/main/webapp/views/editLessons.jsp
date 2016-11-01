<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Edit lesson</title>
</head>

<body>
Edit lesson
<spring:form method="post"  modelAttribute="lesson" action="/lessons/update">

    ID: <spring:input readonly="true" path="id"/>   <br/>
    Topic: <spring:input path="topic"/>   <br/>
    Description: <spring:input path="description"/>   <br/>
    Duration: <spring:input path="duration"/>   <br/>
    Date: <spring:input path="date"/>   <br/>
    <spring:button>Update</spring:button>
    <br/>
    <a href="${pageContext.request.contextPath}/lessons/all"> All </a><br/>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</spring:form>


</body>

</html>
