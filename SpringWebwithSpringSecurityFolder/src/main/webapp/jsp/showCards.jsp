<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
  <head><title>Cards List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1><fmt:message key = "ListOfCards" /></h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create_card"/>" role="button"><fmt:message key = "CreateBtn" /></a>
<table class="table table-striped">
<thead>
  <tr>
        <td><fmt:message key = "Index" /></td>
        <td><fmt:message key = "ID" /></td>
        <td><fmt:message key = "Number" /></td>
        <td><fmt:message key = "AccountID" /></td>
        <td><fmt:message key = "ClientID" /></td>
        <td></td>
        <td></td>
   </tr>
   </thead>
   <tbody>
  <c:forEach var="card" items="${cards}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${card.id}"/></td>
      <td><c:out value="${card.card_number}"/></td>
      <td><c:out value="${card.account_id}"/></td>
      <td><c:out value="${card.client_id}"/></td>
      <td><a class="btn btn-primary" href="<c:url value="/admin/update_card/${card.id}"/>" role="button"><fmt:message key = "UpdateBtn" /></a></td>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete_card/${card.id}"/>" role="button"><fmt:message key = "DeleteBtn" /></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="color: red"><c:out value="${error}"/></div>
</div>
  </body>
</html>