<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, DB.Person" %>

   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <html>

   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <script src="script/main.js"></script>

      <title>Main page</title>
   </head>

   <body>

      <table id="tbl">
				<tr>
					<th id="num">#</th>
					<th>Surname</th>
					<th>Name</th>
					<th>Middle name</th>
					<th>Count</th>
				</tr>
			<%

         ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("list");

         int size = list.size();
         for (int i = 0; i < size; i++) {
         %>
            <tr>
            <td> <%= (i + 1) %> </td> 
            <td> <%= list.get(i).getSurname() %> </td>
            <td> <%= list.get(i).getName() %> </td>
            <td> <%= list.get(i).getMiddleName() %> </td>
            <td> <%= list.get(i).getCount() %> </td>
            
            </tr>
         <%}%>
         
		</table>

   <script>
      addTableCheckBoxes();
   </script>

   <input type="button" value="delete" id="delBtn">
   <script>
      delBtnAddListener();
   </script>
   
   <form method="get" action="adding">

      <input type="submit" value="adding">

   </form>

   </body>

   </html>