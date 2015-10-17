<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Home page</title>
</head>
<style>
  a:link, a:visited {
    display: block;
    font-weight: bold;
    color: #ffffff;
    background-color: #98bf21;
    width: 200px;
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
<body>
<p class="italic">User-Address Data Base</p>
<h3>
  <br/>
  <a href="${pageContext.request.contextPath}/main/user/list.html">View all users</a><br>
  <a href="${pageContext.request.contextPath}/main/address/list.html">View all addresses</a>
</h3>
</body>
</html>