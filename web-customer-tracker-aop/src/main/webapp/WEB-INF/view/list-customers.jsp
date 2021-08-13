<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customers List</title>
	
	<!-- reference style object -->
	<link type = "text/css"
			rel = "stylesheet"
			href ="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!-- input button -->
			<input type="button" value ="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class ="add-button"
			/>
			
			<!-- add a search button -->
			<form:form action="searchCustomer" method="GET">
				Search Customer: <input type="text" name="findName"/>
				
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
			<!-- customer table -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="temp" items="${customers}">
					<!-- create link -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value = "${temp.id}"></c:param>
					</c:url>
					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value = "${temp.id}"></c:param>
					</c:url>
					<tr>
						<td>${temp.firstName}</td>
						<td>${temp.lastName}</td>
						<td>${temp.email}</td>
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							onclick ="if(!(confirm('Are u sure u want to delete this Customer?'))) return false"
							>Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>