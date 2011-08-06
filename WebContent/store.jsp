<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="store.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Store</title>
</head>
<body>
	<h1>Student Store</h1>
	<p>
		Items available:
	</p>
	<ul>
		<% ProductCatalog catalog = (ProductCatalog) getServletContext().getAttribute("catalog");%>
		<% ArrayList<Product> list = catalog.getProductList(); %>
		<% for(int i=0 ; i<list.size() ; i++) {%>
		<li><a href="show-product.jsp?id=<%=list.get(i).getID() %>"><%= list.get(i).getName() %></a></li>
		<% } %>
	</ul>
</body>
</html>