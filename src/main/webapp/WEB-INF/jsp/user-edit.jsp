<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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

<h2>Edit Existing User</h2>

<c:url var="saveUrl" value="/main/user/edit?id=${userAttribute.id}" />
<form:form modelAttribute="userAttribute" method="POST" action="${saveUrl}">
  <table>

    <tr>
      <td><form:label path="firstName">First Name:</form:label></td>
      <td><form:input path="firstName"/></td>
      <td align="left"><form:errors path="firstName" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="lastName">Last Name:</form:label></td>
      <td><form:input path="lastName"/></td>
      <td align="left"><form:errors path="lastName" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="patronymic">Patronymic:</form:label></td>
      <td><form:input path="patronymic"/></td>
    </tr>

    <tr>
      <td><form:label path="phone">Phone:</form:label></td>
      <td><form:input path="phone"/></td>
      <td align="left"><form:errors path="phone" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="email">Email:</form:label></td>
      <td><form:input path="email"/></td>
      <td align="left"><form:errors path="email" cssClass="error"/></td>
    </tr>

    <tr>
      <td><form:label path="birthDate">Date of Birth:</form:label></td>
      <td><form:input path="birthDate"/></td>
      <td align="left"><form:errors path="birthDate" cssClass="error"/></td>
    </tr>

    <tr>
      <td>Address:</td>
      <td>
        <select name="addressId">
          <option value="" selected>None</option>
          <c:forEach var="address" items="${addressList}">
          <c:if test="${requestScope.addressId != null && address.id eq requestScope.addressId}">
            <option selected value="${address.id}">${address.region} ${address.city}  ${address.street} ${address.homeNumber} ${address.flatNumber} </option>
          </c:if>
            <option value="${address.id}">${address.region} ${address.city}  ${address.street} ${address.homeNumber} ${address.flatNumber} </option>
          </c:forEach>
        </select>
      </td>
    </tr>
  </table>

  <input type="submit" value="Save" />
  <input type="button" onclick="location.href='${pageContext.request.contextPath}/main/user/list.html';" value="Cancel" />
</form:form>

</body>
</html>