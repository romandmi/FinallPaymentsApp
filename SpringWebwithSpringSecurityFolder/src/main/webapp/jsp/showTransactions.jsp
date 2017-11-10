<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
  <head><title>Transactions</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1><fmt:message key = "ListOfTransactions" /></h1>
<table class="table table-striped">
<thead>
  <tr>
        <td><fmt:message key = "Index" /></td>
        <td><fmt:message key = "ID" /></td>
        <td><fmt:message key = "Date" /></td>
        <td><fmt:message key = "Sum" /></td>
        <td><fmt:message key = "TypeOfTransaction" /></td>
        <td><fmt:message key = "CardID" /></td>
        <td><fmt:message key = "ClientID" /></td>
   </tr>
   </thead>
   <tbody>
  <c:forEach var="tr" items="${transactions}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${tr.id}"/></td>
      <td><c:out value="${tr.tr_date}"/></td>
      <td><c:out value="${tr.tr_sum}"/></td>
      <td><c:out value="${tr.tr_type}"/></td>
      <td><c:out value="${tr.card_id}"/></td>
      <td><c:out value="${tr.client_id}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>
  </body>
</html>