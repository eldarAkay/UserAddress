<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <style>
    .error {
      color: red; font-weight: bold;
    }
  </style>
</head>
<body>

<h2>Edit Existing Record</h2>

<c:url var="saveUrl" value="/main/address/edit?id=${addressAttribute.id}" />
<form:form modelAttribute="addressAttribute" method="POST" action="${saveUrl}">
  <table>

    <tr>
      <td><form:label path="region">Region:</form:label></td>
      <td><form:input path="region"/></td>
      <td align="left"><form:errors path="region" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="city">City</form:label></td>
      <td><form:input path="city"/></td>
      <td align="left"><form:errors path="city" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="street">Street</form:label></td>
      <td><form:input path="street"/></td>
      <td align="left"><form:errors path="street" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="homeNumber">Home Number</form:label></td>
      <td><form:input path="homeNumber"/></td>
      <td align="left"><form:errors path="homeNumber" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="flatNumber">Flat Number</form:label></td>
      <td><form:input path="flatNumber"/></td>
      <td align="left"><form:errors path="flatNumber" cssClass="error"/></td>
    </tr>
  </table>

  <input type="submit" value="Save" />
  <input type="button" onclick="location.href='${pageContext.request.contextPath}/main/address/list.html';" value="Cancel" />
</form:form>

</body>
</html>
