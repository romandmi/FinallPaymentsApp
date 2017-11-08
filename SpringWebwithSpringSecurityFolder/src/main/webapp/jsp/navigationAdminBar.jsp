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

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">PaymentsApp</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav" style="float: none!Important;">
            <fmt:bundle basename = "messages">
            <li><a href="/SpringWebwithSpringSecurity/admin"><fmt:message key = "Home"/></a></li>
            <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key = "Manage"/>
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_users"><fmt:message key = "ManageUsers"/></a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_clients"><fmt:message key = "ManageClients"/></a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_cards"><fmt:message key = "ManageCards"/></a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_bank-accounts"><fmt:message key = "ManageBankAccounts"/></a></li>
                    </ul>
             </li>
            <!-- <li><a href="/SpringWebwithSpringSecurity/admin/delete_users">Multiple users deleting</a></li> -->
            <li><a href="/SpringWebwithSpringSecurity/admin/find"><fmt:message key = "FindUser"/></a></li>
            <li><a href="/SpringWebwithSpringSecurity/admin/show_transactions"><fmt:message key = "ShowTransactions"/></a></li>
            <li><a href="/SpringWebwithSpringSecurity/admin/about"><fmt:message key = "About"/></a></li>
            <li style ="float:right;"><a href="/SpringWebwithSpringSecurity/logout"><fmt:message key = "Logout"/></a></li>
            <li style="float:right;"><a href="#"><fmt:message key = "Hello"/>, <%= request.getUserPrincipal().getName() %></a></li>
            
            <li>           
            	<form>
            		<select name="ddlLanguage">
              			<option value="en_US">English</option>
              			<option value="ru_RU">Russian</option>
            		</select>
            		<input type = "submit" value = "Submit"/>
          		</form> 
          </li>
            </fmt:bundle>
            
          </ul>
        </div>
  </div>
</div>
<br><br>
<br>
