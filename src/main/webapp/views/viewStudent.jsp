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
    <title>Title</title>
</head>
<body>
Просмотр студента<br/><br/>

    id: ${student.id} <br/>
    First Name: ${student.firstname} <br/>
    Last Name: ${student.lastname}  <br/>
    Gender:  ${student.gender}  <br/>
    Birthdate:  ${student.birthdateStr} <br/><br/>
    <a href="${pageContext.request.contextPath}/edit/${student.id}">Edit</a>
    <a href="${pageContext.request.contextPath}/del/${student.id}">Delete</a>
    <a href="${pageContext.request.contextPath}/"> All </a>

</body>
</html>
