<%--
Created by IntelliJ IDEA.
User: user
Date: 11.10.2015
Time: 7:24
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <p> <a href="${pageContext.request.contextPath}/main/index.html">Home page</a></p>
  <p class="italic">Address page</p>
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
      width: 90px;
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

<form method="get"  action="${pageContext.request.contextPath}/main/address/list.html">
  Region: <th><input type="search" name="region" value="<%=request.getAttribute("region")%>"></th>
  City: <th><input type="search" name="city" value="<%=request.getAttribute("city")%>"></th>
  Street:<th><input type="search" name="street" value="<%=request.getAttribute("street")%>"></th>
  <input type="submit" value="Search">
  <input type="button" onclick="location.href='${pageContext.request.contextPath}/main/address/list.html';" value="Reset" />
</form>


<p>  <a href="${pageContext.request.contextPath}/main/address/add.html">Create a new address</a></p>

<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0">
  <thead>
  <tr>
    <%--<th>Id</th>--%>
    <th>Region</th>
    <th>City</th>
    <th>Street</th>
    <th>Home Number</th>
    <th>Flat Number</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="address" items="${addressList}">
    <tr>
      <%--<td>${address.id}</td>--%>
      <td>${address.region}</td>
      <td>${address.city}</td>
      <td>${address.street}</td>
      <td>${address.homeNumber}</td>
      <td>${address.flatNumber}</td>
      <td><a href="${pageContext.request.contextPath}/main/address/edit?id=${address.id}">Edit</a></td>
      <td><a href="${pageContext.request.contextPath}/main/address/delete?id=${address.id}">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>