<%@page import="com.my.product.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
</head>
<body>
<%List<Product> list = (List)request.getAttribute("list"); 
for(Product p : list){
%>
<div>
	<ul>
		<li><img src = "./images/<%= p.getProdNo() %>.PNG"></li>
		<li><%=p.getProdName() %></li>
	</ul>
</div>
<%	}
%>
</body>
</html>