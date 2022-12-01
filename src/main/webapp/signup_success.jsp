<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="en" >
<head>
  <meta charset="ISO-8859-1">
  <title>Registration Success</title>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" />
</head>
<body>
<div class="container text-center">
  <h3>You have signed up successfully!</h3>
  <h4><a href="<%=request.getContextPath()%>/BookManagement?action=login" >Click here to Login</a></h4>
  <h4><a href="<%=request.getContextPath()%>/BookManagement" id="back">Back to article...</a></h4>
</div>

</body>
</html>