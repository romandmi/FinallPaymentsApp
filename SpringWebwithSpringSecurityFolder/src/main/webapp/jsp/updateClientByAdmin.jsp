<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
<head>
    <title>Update</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationAdminBar.jsp"></jsp:include>
<form:form name="updateClient" method="POST">
    <div class="container">
        <h1><fmt:message key = "UpdateClient"/></h1>
        <div type="div-form">

                <h3><fmt:message key = "ID"/> = <c:out value="${client.id}"/></h3>
                <p><input type="text" name="id" value="<c:out value="${client.id}"/>" readonly hidden/></p>

                <p><fmt:message key = "FirstName"/></p>
                <p><input class = "text-form" type="text" name="first_name" value="<c:out value="${client.first_name}"/>"/></p>

                <p><fmt:message key = "LastName"/></p>
                <p><input class = "text-form" type="text" name="last_name" value="<c:out value="${client.last_name}"/>"/></p>

                <p><fmt:message key = "Surname"/></p>
                <p><input class = "text-form" type="text" name="surname" value="<c:out value="${client.surname}"/>"/></p>

                <p><fmt:message key = "UserID"/></p>
                <p><input class = "text-form" type="text" name="user_id" value="<c:out value="${client.user_id}"/>"/></p>

                <br><input class ="submit-form" type="submit" value=<fmt:message key = "Submit"/> />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>