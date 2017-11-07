<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationAdminBar.jsp"></jsp:include>
<form:form name="getNewClient" method="POST">
    <div class="container">
        <h1>Create a new client</h1>
        <div type="div-form">
                <p>Login</p>
                <p><input class = "text-form" type="text" name="login" /></p>

                <p>Password</p>
                <p><input class = "text-form" type="password" name="password" /></p>

                <p>Role</p>
                <p><input class = "text-form" type="text" name="is_admin" /></p>

                <br><input class ="submit-form" type="submit" value="Submit" />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
