<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
<head>
    <title>Create new</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationAdminBar.jsp"></jsp:include>
<form:form name="getNewClient" method="POST">
    <div class="container">
        <h1><fmt:message key = "CreateUser"/></h1>
        <div type="div-form">
                <p><fmt:message key = "Login"/></p>
                <p><input class = "text-form" type="text" name="login" /></p>

                <p><fmt:message key = "Password"/></p>
                <p><input class = "text-form" type="password" name="password" /></p>

                <p><fmt:message key = "Role"/></p>
                <p><input type="radio" name="is_admin" value="user" checked />  <fmt:message key = "user"/></p>
                <p><input type="radio" name="is_admin" value="admin" />  <fmt:message key = "admin"/></p>

                <br><input class ="submit-form" type="submit" value=<fmt:message key = "Submit"/> />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
