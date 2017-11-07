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
<form:form name="createCard" method="POST">
    <div class="container">
        <h1>Create a new card</h1>
        <div type="div-form">
                <p>Card Number</p>
                <p><input class = "text-form" type="text" name="card_number" /></p>

                <p>BankAccount Id</p>
                <p><input class = "text-form" type="text" name="account_id" /></p>

                <p>Client Id</p>
                <p><input class = "text-form" type="text" name="client_id" /></p>

                <br><input class ="submit-form" type="submit" value="Submit" />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
