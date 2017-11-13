<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<c:set var="current" value="${param.ddlLanguage}"/>
<c:if test="${not empty current}">
    <fmt:setLocale value = "current"/>
</c:if>
    <c:if test="${current == 'ru_RU'}">
   <fmt:setLocale value="ru_RU"/>
  </c:if>
  <c:if test="${current == 'en_US'}">
     <fmt:setLocale value="en_US"/>
   </c:if>

<html>
<head>
<title>Home</title>
<jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>

<jsp:include page="navigationAdminBar.jsp"></jsp:include>
<div class="container">
<fmt:bundle basename = "messages">
    <h2><fmt:message key = "WelcomeAdmin" /> <%= request.getUserPrincipal().getName() %></h2>
</fmt:bundle>
</div>
</body>
</html>