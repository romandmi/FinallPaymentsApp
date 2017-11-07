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
<form:form name="updateCard" method="POST">
    <div class="container">
        <h1>Update a card</h1>
        <div type="div-form">

                <h3>ID = <c:out value="${card.id}"/></h3>
                <p><input type="text" name="id" value="<c:out value="${card.id}"/>" readonly hidden/></p>

                <p>Card Number</p>
                <p><input class = "text-form" type="text" name="card_number" value="<c:out value="${card.card_number}"/>"/></p>

                <p>BankAccount Id</p>
                <p><input class = "text-form" type="text" name="account_id" value="<c:out value="${card.account_id}"/>"/></p>

                <p>Client Id</p>
                <p><input class = "text-form" type="text" name="client_id" value="<c:out value="${card.client_id}"/>"/></p>

                <br><input class ="submit-form" type="submit" value="Submit" />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>