<%-- 
    Document   : index
    Created on : 15-Oct-2019, 19:47:34
    Author     : KHALID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD Example</title>
    </head>
    <body>
        <jsp:forward page="/UserController?action=listuser" />
    </body>
</html>
