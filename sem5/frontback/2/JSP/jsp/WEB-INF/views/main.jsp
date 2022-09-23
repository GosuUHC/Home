<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.sql.ResultSet, DB.UsersUtil" %>


   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <html>

   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <title>Main page</title>
   </head>

   <body>

      <%  
      UsersUtil UsersUtil = new UsersUtil();
      Object userLogin = session.getAttribute("logg");
      ResultSet rs = null;


      if (userLogin != null) {
         rs = UsersUtil.getData((String) userLogin);

      } else {
         out.println("<br>USERLOGIN INVALID<br>");
      }
      
      while(rs.next()) {
         
         out.println(rs.getString("userlogin"));
         out.println(rs.getString("surname"));
         out.println(rs.getString("name"));
         out.println(rs.getString("middlename"));
         out.println(rs.getString("count"));
         out.println("<br>");
      }
      %>

   <form method="get" action="adding">

      <input type="submit" value="adding">

   </form>

   <form method="post" action="delete">
      <input type="text" id="inpDel" size="10" name="delNum">
      <input type="submit" value="delete">

   </form>
         


   </body>

   </html>