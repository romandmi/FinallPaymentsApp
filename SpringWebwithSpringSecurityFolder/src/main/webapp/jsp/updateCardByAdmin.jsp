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
<form:form name="updateCard" method="POST">
    <div class="container">
        <h1><fmt:message key = "UpdateCard"/></h1>
        <div type="div-form">

                <h3><fmt:message key = "ID"/> = <c:out value="${card.id}"/></h3>
                <p><input type="text" name="id" value="<c:out value="${card.id}"/>" readonly hidden/></p>

                <p><fmt:message key = "Number"/></p>
                <p><input class = "text-form" type="text" name="card_number" value="<c:out value="${card.card_number}"/>"/></p>

                <p><fmt:message key = "AccountID"/></p>
                <p><input class = "text-form" type="text" name="account_id" value="<c:out value="${card.account_id}"/>"/></p>

                <p><fmt:message key = "ClientID"/></p>
                <p><input class = "text-form" type="text" name="client_id" value="<c:out value="${card.client_id}"/>"/></p>

                <br><input class ="submit-form" type="submit" value=<fmt:message key = "Submit"/> />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>