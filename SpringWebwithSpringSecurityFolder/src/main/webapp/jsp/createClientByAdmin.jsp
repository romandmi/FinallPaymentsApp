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
<form:form name="createClient" method="POST">
    <div class="container">
        <h1><fmt:message key = "CreateClient"/></h1>
        <div type="div-form">
                <p><fmt:message key = "FirstName"/></p>
                <p><input class = "text-form" type="text" name="first_name" /></p>

                <p><fmt:message key = "LastName"/></p>
                <p><input class = "text-form" type="text" name="last_name" /></p>

                <p><fmt:message key = "Surname"/></p>
                <p><input class = "text-form" type="text" name="surname" /></p>

                <p><fmt:message key = "UserID"/></p>
                <p><input class = "text-form" type="text" name="user_id" /></p>

                <br><input class ="submit-form" type="submit" value=<fmt:message key = "Submit"/> />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
