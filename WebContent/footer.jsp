<p>
<hr>
Application version 1.16 ; 
<% if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "")) { %>
Not logged in. <a href="login.jsp">login now</a>
<%} else { %>
Current user: <%=session.getAttribute("username")%> ; <a href="logout"> Logout</a>
<%}%>
