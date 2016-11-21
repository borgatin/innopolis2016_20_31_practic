<%--
  Created by IntelliJ IDEA.
  User: avborg
  Date: 31.10.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View student</title>
</head>
<body>
View student<br/>
<p style="color:red">${msgError}</p> <br/>

    id: ${student.id} <br/>
    First Name: ${student.firstname} <br/>
    Last Name: ${student.lastname}  <br/>
    Gender:  ${student.gender}  <br/>
    Birthdate:  ${student.birthdate} <br/><br/>
    <a href="${pageContext.request.contextPath}/students/edit/${student.id}">Edit</a>
    <a href="${pageContext.request.contextPath}/students/del/${student.id}">Delete</a><br/>
    <a href="${pageContext.request.contextPath}/students/all"> All </a> <br/>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</body>
</html>
