<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationAdminBar.jsp"></jsp:include>
<form:form name="updateClient" method="POST">
    <div class="container">
        <h1>Update a client</h1>
        <div type="div-form">
            
                <h3>ID = <c:out value="${user.id}"/></h3>
                <p><input type="text" name="id" value="<c:out value="${user.id}"/>" readonly hidden/></p>

                <p>Login</p>
                <p><input class = "text-form" type="text" name="login" value="<c:out value="${user.login}"/>" /></p>

                <p>Password</p>
                <p><input class = "text-form" type="text" name="password" value="<c:out value="${user.password}"/>" /></p>

                <p>Role</p>
                <p><input class = "text-form" type="text" name="is_admin" value="<c:out value="${user.is_admin}"/>" /></p>

                <br><input class ="submit-form" type="submit" value="Submit" /></br>
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>