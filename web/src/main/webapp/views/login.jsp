<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>username</title>
    <form action="<c:url value='/j_spring_security_check'/>" method="POST">
        <p>Вход:</p>
        <div class="main">
            <div class="field">
                <label> <b>Логин:</b></label>
                <input type="text" size="20" name="j_username">
            </div>
            <div class="field">
                <label>  <b>Пароль:</b></label>
                <input type="password" size="20" name="j_password">
            </div>

            <input type="submit" value="Войти">
        </div>

    </form>

</head>
<body>

</body>
</html>
