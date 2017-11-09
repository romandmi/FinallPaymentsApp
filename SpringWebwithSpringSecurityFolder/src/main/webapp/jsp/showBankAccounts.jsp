<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<html>
  <head><title>Bank Accounts</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
  <div class="container">
    <h1><fmt:message key = "ListOfBankAccounts" /></h1>
    <a class="btn btn-success pull-right" href="<c:url value="/admin/create_acc"/>" role="button"><fmt:message key = "CreateBtn" /></a>
<table class="table table-striped">
<thead>
  <tr>
        <td><fmt:message key = "Index" /></td>
        <td><fmt:message key = "ID" /></td>
        <td><fmt:message key = "Balance" /></td>
        <td><fmt:message key = "Status" /></td>
        <td><fmt:message key = "Action" /></td>
        <td></td>
   </tr>
   </thead>
   <tbody>
  <c:forEach var="bankAcc" items="${bankAccs}" varStatus="status">
    <tr>
      <td><c:out value="${status.index + 1}"/></td>
      <td><c:out value="${bankAcc.id}"/></td>
      <td><c:out value="${bankAcc.balance}"/></td>
      <c:if test="${bankAcc.is_blocked == true}">
          <td>Blocked</td>
          <td><a class="btn btn-primary"
          href="<c:url value="/admin/show_bank-accounts/change_status/${bankAcc.id}"/>"
          role="button"><fmt:message key = "Unblock" /></a></td>
      </c:if>
      <c:if test="${bankAcc.is_blocked == false}">
           <td>Not Blocked</td>
           <td><a class="btn btn-warning"
            href="<c:url value="/admin/show_bank-accounts/change_status/${bankAcc.id}"/>"
           role="button"><fmt:message key = "Block" /></a></td>
      </c:if>
      <td><a class="btn btn-danger" href="<c:url value="/admin/delete_acc/${bankAcc.id}"/>" role="button"><fmt:message key = "DeleteBtn" /></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>
  </body>
</html>