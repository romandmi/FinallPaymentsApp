<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
<head>
    <title>Add Funds</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<jsp:include page="navigationUserBar.jsp"></jsp:include>
<form:form name="addFunds" method="POST">
    <div class="container">
        <h1><fmt:message key = "AddFunds"/></h1>
        <div type="div-form">
                <p><fmt:message key = "CardNumber"/></p>
                <p><input class = "text-form" type="text" name="card_number" /></p>

                <p><fmt:message key = "Sum"/></p>
                <p><input class = "text-form" type="text" name="tr_sum"/></p>

                <br><input class ="submit-form" type="submit" value=<fmt:message key = "Submit"/> />
        </div>
        <div style="color: red"><c:out value="${error}"/></div>
    </div>
</form:form>
</body>
</html>
