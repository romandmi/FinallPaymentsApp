<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>
<br><br><br>
<div class="container">
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
</c:if>
<div type="div-login">
	<form name="loginForm" action="authenticateUser" method="post">
		<p> Login</p>
		<p><input class = "text-login" type="text" name="username" /></p>
		<p>Password </p>
		<p><input
			type="password" class = "text-login" name="password" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" /></p>
			<br> <input class ="submit-login" type="submit" value="Submit">
	</form>
</div>
</div>
</body>
</html>