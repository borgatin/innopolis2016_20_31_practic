<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Edit student</title>
</head>

<body>
Edit student
<spring:form method="post"  modelAttribute="student" action="${pageContext.request.contextPath}/students/update">

    ID: <spring:input readonly="true" path="id"/>   <br/>
    First Name: <spring:input path="firstname"/>   <br/>
    Last Name: <spring:input path="lastname"/>   <br/>
    <%--Gender: <spring:input path="gender"/>   <br/>--%>
    Male:&nbsp;&nbsp; <spring:radiobutton path="gender"  value="male"/>   <br/>
    Female: <spring:radiobutton path="gender"  value="female"/>   <br/>
    Birthdate: <spring:input path="birthdate"/>   <br/>
    <spring:button>Update</spring:button>
    <br/>
    <a href="${pageContext.request.contextPath}/students/all"> All </a><br/>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</spring:form>


</body>

</html>
