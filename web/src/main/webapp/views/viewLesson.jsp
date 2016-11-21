
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View lesson</title>
</head>
<body>
View lesson<br/>
<p style="color:red">${msgError}</p> <br/>

    id: ${lesson.id} <br/>
    Topic: ${lesson.topic} <br/>
    Description: ${lesson.description}  <br/>
    Duration:  ${lesson.duration}  <br/>
    Date:  ${lesson.date} <br/><br/>
    <a href="${pageContext.request.contextPath}/lessons/edit/${lesson.id}">Edit</a>
    <a href="${pageContext.request.contextPath}/lessons/del/${lesson.id}">Delete</a><br/>
    <a href="${pageContext.request.contextPath}/lessons/all"> All </a> <br/>
    <a href="${pageContext.request.contextPath}/"> Home </a>

</body>
</html>
