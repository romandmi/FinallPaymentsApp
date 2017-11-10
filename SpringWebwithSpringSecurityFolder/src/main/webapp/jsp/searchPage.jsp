<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Search</title>
    <jsp:include page="includeFile.jsp"></jsp:include>
  </head>
  <body>
  <jsp:include page="navigationAdminBar.jsp"></jsp:include>
    <div class="container">
    <h1><fmt:message key = "Search" /></h1>
        <div type="div-form">
            <form:form name="/admin/find" method="POST">
            <input type="text-search" id="login" name="login" placeholder=<fmt:message key = "EnterLogin" />>
            <br><br>
            <input class ="submit-search" type="submit" value=<fmt:message key = "Search" />>
          </form:form>
        </div>
    </div>
  </body>
</html>