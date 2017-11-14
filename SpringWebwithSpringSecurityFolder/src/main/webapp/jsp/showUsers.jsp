<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
  <head><title>Users List</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">


  	<fmt:bundle basename = "messages">
    <h1><fmt:message key = "ListOfUsers" /><br/></h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create"/>" role="button"><fmt:message key = "CreateBtn" /><br/></a>
    </fmt:bundle>
<table class="table table-striped">
<thead>
  <tr>


        <fmt:bundle basename = "messages">
         <td><fmt:message key = "Index" /><br/></td>
         <td><fmt:message key = "ID" /><br/></td>
         <td><fmt:message key = "Login" /><br/></td>
         <td><fmt:message key = "Role"/><br/></td>
     	</fmt:bundle>

   </tr>
   </thead>
   <tbody>
  <c:forEach var="user" items="${users}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${user.id}"/></td>
      <td><c:out value="${user.login}"/></td>
      <td><c:out value="${user.is_admin}"/></td>
      
      <fmt:bundle basename = "messages">
      <td><a class="btn btn-primary" href="<c:url value="/admin/update/${user.id}"/>" role="button"><fmt:message key = "UpdateBtn" /><br/></a></td>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete/${user.id}"/>" role="button"><fmt:message key = "DeleteBtn" /><br/></a></td>
      </fmt:bundle>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="color: red"><c:out value="${error}"/></div>
</div>
  </body>
</html>