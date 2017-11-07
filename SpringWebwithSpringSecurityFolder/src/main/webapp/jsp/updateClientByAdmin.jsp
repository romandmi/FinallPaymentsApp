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

                <h3>ID = <c:out value="${client.id}"/></h3>
                <p><input type="text" name="id" value="<c:out value="${client.id}"/>" readonly hidden/></p>

                <p>First Name</p>
                <p><input class = "text-form" type="text" name="first_name" value="<c:out value="${client.first_name}"/>"/></p>

                <p>Last Name</p>
                <p><input class = "text-form" type="text" name="last_name" value="<c:out value="${client.last_name}"/>"/></p>

                <p>Surname</p>
                <p><input class = "text-form" type="text" name="surname" value="<c:out value="${client.surname}"/>"/></p>

                <p>UserId</p>
                <p><input class = "text-form" type="text" name="user_id" value="<c:out value="${client.user_id}"/>"/></p>

                <br><input class ="submit-form" type="submit" value="Submit" />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>