<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
  <head><title>Cards List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1>List of cards</h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create_card"/>" role="button">Create new</a>
<table class="table table-striped">
<thead>
  <tr>
        <td>Index</td>
        <td>ID</td>
        <td>Number</td>
        <td>AccountId</td>
        <td>ClientId</td>
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
      <td><a class="btn btn-primary" href="<c:url value="/admin/update_card/${card.id}"/>" role="button">Update</a></td>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete_card/${card.id}"/>" role="button">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="color: red"><c:out value="${error}"/></div>
</div>
  </body>
</html>