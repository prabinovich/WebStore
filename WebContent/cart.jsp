<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="store.*, java.text.*, java.util.*" %>
<% ProductCatalog catalog = (ProductCatalog) getServletContext().getAttribute("catalog"); %>
<% ShoppingCart cart = (ShoppingCart) session.getAttribute("cart"); %>
<% DecimalFormat df = new DecimalFormat("#.00");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
</head>
<body>
	<h1>Shopping Cart</h1>
	<% if(cart.numProducts()>0) {%>
	<form action="UpdateCartServlet" method="POST">
	<table>
		<tr>
			<th>
			# of Items
			</th>
			<th>
			Item Name
			</th>
			<th>
			Item Cost
			</th>
		</tr>
		<%Iterator<String> iter = cart.getIterator(); %>
		<%while(iter.hasNext()) { %>
			<%String id = iter.next(); %>
			<%Product p = catalog.getProductByID(id); %>
			<tr>
				<td>
					<input type="text" value="<%=cart.numProductByID(id)%>" name="<%=id %>" />
				</td>
				<td>
					<%=p.getName()%>:
				</td>
				<td>
					$<%=df.format(p.getPrice()) %>
				</td>
			</tr>
		<%} %>
		<tr>
			<td>
			<div></div>
			</td>
			<td>
			<input type="submit" value="Update Cart" />
			</td>
			<td>
			Total: $<%= df.format(cart.calculateTotalCost()) %>
			</td>
		</tr>
	</table>
	</form>
	<% } else { %>
		<p>
		You have no items in your shopping cart
		</p>
	<% } %>
	<p>
	<a href="store.jsp">Continue Shopping</a>
	</p>
</body>
</html>