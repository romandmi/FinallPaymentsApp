<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>UTF-8"/>

<html>
<head>
<title>Home</title>
<jsp:include page="includeFile.jsp"></jsp:include>
</head>
<body>

<jsp:include page="navigationUserBar.jsp"></jsp:include>
<div class="container">
    <h2><fmt:message key = "WelcomeUser"/> <%= request.getUserPrincipal().getName() %></h2>
</div>
</body>
</html>