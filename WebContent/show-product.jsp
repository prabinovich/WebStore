<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="store.*, java.util.*, java.text.*" %>
<% ProductCatalog catalog = (ProductCatalog) getServletContext().getAttribute("catalog"); %>
<% Product p = catalog.getProductByID(request.getParameter("id"));  %>
<% DecimalFormat df = new DecimalFormat("#.00");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= p.getName() %></title>
</head>
<body>
	<h1><%= p.getName() %></h1>
	<img src="images/<%=p.getImage() %>"/>
	<form action="AddItemServlet" method="POST">
	<table>
		<tr>
			<td>
			$<%= df.format(p.getPrice()) %>
			</td>
			<td>
			<input type="submit" value="Add to Cart"/>
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="<%= p.getID() %>"/>
	</form>
</body>
</html>