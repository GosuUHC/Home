<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
   <meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
   <title>Auth</title>
</head>

<body>
    <h2>Adding</h2>
    <form method="post" action="adding">
        <%
        Object userLogin = session.getAttribute("logg");
        out.println("for ");
        out.println(userLogin);
        %>
        <br>
        <br>
        Surname: <input type="text" id="addSurname" size="40" name="addingSurname">
        <br>
        <br>
        Name: <input type="text" id="addName" size="40" name="addingName">
        <br>
        <br>
        Middle name: <input type="text" id="addMiddleName" size="40" name="addingMiddleName">
        <br>
        <br>
        Count: <input type="text" id="addCount" size="40" name="addingCount">
        <input type="submit" value="Add">
   </form>
</body>

</html>