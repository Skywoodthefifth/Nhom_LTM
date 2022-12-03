<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
int ID_Account = (Integer) request.getAttribute("ID_Account");
%>
<%
String username = (String) request.getAttribute("username");
%>
<html>
<head>
<script>
	function myFunction() {
		if (document.getElementById("proverty").value == "1") {
			document.getElementById("searchvalue").type = "number";
		} else {
			document.getElementById("searchvalue").type = "text";
		}
	}
</script>
<title>Book Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
p {
	background-image: url('the_jpeg.jpg');
}
</style>
</head>
<body>

	<header>
		<c:set var="username" value='<%=session.getAttribute("username")%>' />
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: orange">
			<div>
				<a href="<%=request.getContextPath()%>/BookManagement"
					class="navbar-brand"> Book Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/BookManagement"
					class="nav-link">Books</a></li>
			</ul>

			<%
			if (ID_Account == -1) {
			%>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/?action=login"
					class="nav-link">Login</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/?action=register"
					class="nav-link">Register</a></li>
			</ul>
			<%
			} else {
			%>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/?action=logout"
					class="nav-link">Log out</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a class="nav-link">Xin chao <%=username%></a></li>
			</ul>
			<%
			}
			%>



		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<h3 class="text-center">List of Books</h3>
			<hr>
			<div class="container text-left">
				<%
				if (ID_Account != -1) {
				%>
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Book</a>
				<%
				}
				%>
				<form action="search" method="get">
					<label for="proverty">Chon thuoc tinh can search:</label> <select
						name="proverty" id="proverty" onchange="myFunction()">
						<option value="1">Search theo Ma Sach</option>
						<option value="2">Search theo Ten Sach</option>
					</select> <input type="number" name="searchvalue" id="searchvalue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label>Chon the loai
						sach:</label> <select name="searchvalue2" id="searchvalue2">
						<option value="0" selected>All</option>
						<c:forEach var="cate" items="${ListCategory}">
							<option value="${cate.getID_Category()}">${cate.getCategory()}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="action" value="search">
					<button type="submit" class="btn btn-success">Search</button>
				</form>

			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id_Book</th>
						<th>Book_title</th>
						<th>Category</th>
						<th>quantity</th>
						<th>publisher</th>
						<th>publish_date</th>
						<%
						if (ID_Account != -1) {
						%>
						<th>Actions</th>
						<%
						}
						%>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="Book" items="${listBook}">

						<tr>
							<td><c:out value="${Book.getID_Book()}" /></td>
							<td><c:out value="${Book.getBook_title()}" /></td>
							<td><c:out value="${Book.getCategory()}" /></td>
							<td><c:out value="${Book.getQuantity()}" /></td>
							<td><c:out value="${Book.getPublisher()}" /></td>
							<td><c:out value="${Book.getPublish_date()}" /></td>
							<%
							if (ID_Account != -1) {
							%>
							<td><a
								href="<%=request.getContextPath()%>/edit?ID_Book=<c:out value='${Book.getID_Book()}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/delete?ID_Book=<c:out value='${Book.getID_Book()}' />">Delete</a></td>
							<%
							}
							%>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
				<%
				if (ID_Account != -1) {
				%>
				<br>
				<br>
				<div class="container">
				<h3 class="text-center">Lich su dang nhap cua tai khoan</h3>
				<hr>
				<br>
					<table class="table table-bordered">
					<thead>
					<tr>
					<th>Ngay dang nhap</th>
					</tr>
					</thead>
					<tbody>

					<c:forEach var="date" items="${loginhistory}">

					<tr>
						<td><c:out value="${date.getLoginDate()}" /></td>
					</tr>
					</c:forEach>
				
				<%} %>
</body>
</html>