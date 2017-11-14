<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
  <head><title>Client List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1><fmt:message key = "ListOfClients"/></h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create_client"/>" role="button"><fmt:message key = "CreateBtn"/></a>
<table class="table table-striped">
<thead>
  <tr>
        <td><fmt:message key = "Index"/></td>
        <td><fmt:message key = "ID"/></td>
        <td><fmt:message key = "FirstName"/></td>
        <td><fmt:message key = "LastName"/></td>
        <td><fmt:message key = "Surname"/></td>
        <td></td>
        <td></td>
   </tr>
   </thead>
   <tbody>
  <c:forEach var="client" items="${clients}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${client.id}"/></td>

      <td><a class="btn btn-primary" href="<c:url value="/admin/update_client/${client.id}"/>" role="button"><fmt:message key = "UpdateBtn"/></a></td>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete_client/${client.id}"/>" role="button"><fmt:message key = "DeleteBtn"/></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="color: red"><c:out value="${error}"/></div>
</div>
  </body>
</html>