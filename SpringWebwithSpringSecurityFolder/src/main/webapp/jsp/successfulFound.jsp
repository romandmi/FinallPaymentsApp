<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
  <head><title>Found</title></head>
  <body>
    <h1>Search result</h1>
    <p><c:out value="${login}"/></p>
    <div style="color: red"><c:out value="${error}"/></div>
  </body>
</html>