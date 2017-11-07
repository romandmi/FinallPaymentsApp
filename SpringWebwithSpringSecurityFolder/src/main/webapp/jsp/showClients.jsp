<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
  <head><title>Clent List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1>List of clients</h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create_client"/>" role="button">Create new</a>
<table class="table table-striped">
<thead>
  <tr>
        <td>Index</td>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Surname Name</td>
        <td></td>
        <td></td>
   </tr>
   </thead>
   <tbody>
  <c:forEach var="client" items="${clients}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${client.id}"/></td>
      <td><c:out value="${client.first_name}"/></td>
      <td><c:out value="${client.last_name}"/></td>
      <td><c:out value="${client.surname}"/></td>
      <td><a class="btn btn-primary" href="<c:url value="/admin/update_client/${client.id}"/>" role="button">Update</a></td>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete_client/${client.id}"/>" role="button">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="color: red"><c:out value="${error}"/></div>
</div>
  </body>
</html>