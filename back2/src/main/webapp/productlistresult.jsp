<%@page import="com.my.util.PageGroup"%>
<%@page import="com.my.product.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
<style>
*{
	box-sizing: border-box;
 }
 div.product{
    box-sizing : border-box;
    width:200px;
    display:inline-block;
}
div.product>ul{
    list-style-type: none; /*ul요소때문에 동그라미 점 생기는 거라 없애주는*/
    padding-left:0px;
}
div.product>ul>li>img{
    /*width:100px;*/
    width:80%;
}
div.product>ul>li>span{
    display:inline-block;
    width:80%;
    text-align: center;
}
.productlist>h3{
    width:200px;
    margin:10px auto;
    text-align: right;
    background: yellow;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(()=>{
	$('.productlist>.pagegroup>span').click((e)=>{
		//alert($(e.target).html()+":"+$(e.target).attr('class')+"페이지가 클릭되었습니다")
		const pg = $(e.target).attr('class') //pg1, pg2, ...
		const currentPage = pg.substr(2)     //1, 2, ...
		const url = './productlist?currentPage='+currentPage
		const $section = $('section')
		$section.load(url)
	})
})</script>
</head>
<body>
<div class = "productlist">
	<h3>상품목록</h3>
<%PageGroup<Product> pb = (PageGroup)request.getAttribute("pb");
//List<Product> list = (List)request.getAttribute("list"); 
List<Product> list = pb.getList();
for(Product p : list){
%>
	<div class="product">
		<ul>
			<li><img src = "./images/<%=p.getProdNo()%>.PNG" alt="<%=p.getProdName()%>"></li>
			<li><span><%=p.getProdName() %></span></li>
		</ul>
	</div>
<%}%>
	<div class="pagegroup">
		<%
		int startPage = pb.getStartPage();
		int endPage = pb.getEndPage();
		int totalPage = pb.getTotalPage();
		
		if(startPage > 1){
		%>[<span class="pg<%=startPage-1%>">PREV</span>]&nbsp;&nbsp;&nbsp;
		<%}
		for(int i=startPage; i<=endPage; i++){
		%><span class="pg<%=i%>">[<%=i%>]</span>&nbsp;&nbsp;&nbsp;
		<%} 
		
		if(endPage != totalPage){ //endPage < totalPage
		%>[<span class="pg<%=endPage+1%>">NEXT</span>]
		<%}
		%>
	</div>
</div>
</body>
</html>