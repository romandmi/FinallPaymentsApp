<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">PaymentsApp</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav" style="float: none!Important;">
            <li><a href="/SpringWebwithSpringSecurity/admin">Home</a></li>
            <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage...
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_users">Manage users</a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_clients">Manage clients</a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_cards">Manage cards</a></li>
                      <li><a href="/SpringWebwithSpringSecurity/admin/show_bank-accounts">Manage Bank Accounts</a></li>
                    </ul>
             </li>
            <!-- <li><a href="/SpringWebwithSpringSecurity/admin/delete_users">Multiple users deleting</a></li> -->
            <li><a href="/SpringWebwithSpringSecurity/admin/find">Find user</a></li>
            <li><a href="/SpringWebwithSpringSecurity/admin/show_transactions">Show transactions</a></li>
            <li><a href="/SpringWebwithSpringSecurity/admin/about">About</a></li>
            <li style ="float:right;"><a href="/SpringWebwithSpringSecurity/logout">Logout</a></li>
            <li style="float:right;"><a href="/SpringWebwithSpringSecurity/admin/settings">Hello, <%= request.getUserPrincipal().getName() %></a></li>
          </ul>
        </div>
  </div>
</div>
<br><br>