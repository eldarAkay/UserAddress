<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>User page</title>
  <style>
    table {
      border: 1px solid black;
      border-collapse: collapse;

    }
    th {
      padding: 5px;
      text-align: left;
      background-color: #A7C942;
      color: #ffffff;
    }
    td {
      padding: 5px;
      text-align: left;
      color: #000000;
      background-color: #EAF2D3;
      border: 1px solid #98bf21;

    }
    a:link, a:visited {
      /*display: block;*/
      font-weight: bold;
      color: #ffffff;
      background-color: #98bf21;
      width: 70px;
      text-align: center;
      padding: 4px;
      text-decoration: none;
    }

    a:hover, a:active {
      background-color: #7A991A;
    }
    p.italic {
      font-style: italic;
      font-size: 20px;
    }
  </style>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/main/index.html">Home page</a></p>
<p class="italic">Users page</p>
<form id = "myForm" method="get" action="${pageContext.request.contextPath}/main/user/list.html">
  Last Name: <th><input type="search" name="lastName" value="<%=request.getAttribute("lastName")%>"></th>
  First Name: <th><input type="search" name="firstName" value="<%=request.getAttribute("firstName")%>"></th>
  Email:<th><input type="search" name="email" value="<%=request.getAttribute("email")%>"></th>
  <input type="submit" value="Search">
  <input type="button" onclick="location.href='${pageContext.request.contextPath}/main/user/list.html';" value="Reset" />
</form>



<br/>

<p><a href="${pageContext.request.contextPath}/main/user/add.html">Create a new user</a></p>

<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0">
  <thead>
  <tr>
    <%--<th>Id</th>--%>
    <th>Last Name</th>
    <th>First Name</th>
    <th>Patronymic</th>
    <th>Phone</th>
    <th>Email</th>
    <th>Birth Date</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="user" items="${userList}">
    <tr>
      <%--<td>${user.id}</td>--%>
      <td>${user.lastName}</td>
      <td>${user.firstName}</td>
      <td>${user.patronymic}</td>
      <td>${user.phone}</td>
      <td>${user.email}</td>
      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthDate}" /></td>
      <td><a href="${pageContext.request.contextPath}/main/user/edit?id=${user.id}">Edit</a></td>
      <td> <a href="${pageContext.request.contextPath}/main/user/delete?id=${user.id}">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>