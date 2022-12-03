<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="bean.*"%>
<html>
<head>
<title>Book Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: orange">
			<div>
				<a href="<%=request.getContextPath()%>/BookManagement"
					class="navbar-brand"> Book Management </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${book != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${book != null}">
            			Edit book
            		</c:if>
						<c:if test="${book == null}">
            			Add New book
            		</c:if>
					</h2>
				</caption>

				<c:if test="${book != null}">
					<fieldset class="form-group">
						<label>ID_Book</label> <input type="text" name="ID_Book"
							value="<c:out value='${book.getID_Book()}' />" readonly />
					</fieldset>
				</c:if>

				<fieldset class="form-group">
					<label>Book Name</label> <input type="text"
						value="<c:out value='${book.getBook_title()}' />"
						class="form-control" name="Book_title" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Book Quantity</label> <input type="text"
						value="<c:out value='${book.getQuantity()}' />"
						class="form-control" name="quantity">
				</fieldset>
				<fieldset class="form-group">
					<label>Category</label> <select name="Category" id="Category"
						class="form-control">
						<c:forEach var="cate" items="${ListCategory}">

							<c:if test="${cate.getCategory() == book.getCategory()}">
								<option value="${cate.getCategory()}" selected>${cate.getCategory()}</option>
							</c:if>

							<c:if test="${cate.getCategory() != book.getCategory()}">
								<option value="${cate.getCategory()}">${cate.getCategory()}</option>
							</c:if>

						</c:forEach>
					</select>
				</fieldset>
				<fieldset class="form-group">
					<label>Publisher</label> <input type="text"
						value="<c:out value='${book.getPublisher()}' />"
						class="form-control" name="publisher">
				</fieldset>
				<fieldset class="form-group">
					<label>Publish date</label> <input type="date"
						value="<c:out value='${book.getPublish_date()}' />"
						class="form-control" name="publish_date">
				</fieldset>
				<c:if test="${book != null}">
					<input type="hidden" name="action" value="update">
				</c:if>
				<c:if test="${book == null}">
					<input type="hidden" name="action" value="insert">
				</c:if>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>