<%-- 
    Document   : users
    Created on : 20-Oct-2022, 8:49:35 PM
    Author     : levan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/style.css" type="text/css">
        <script src="https://kit.fontawesome.com/e33bf080b0.js" crossorigin="anonymous"></script>
        <title>User Information</title>
    </head>
    <body>
     <div class="container">
        <div class="row">
            <div class="column1">
                <h3>Add User</h3>
                <div class="user">
                    <form action="users" method="POST">
                        <input type="text" name="email" id="email" value="" placeholder="Email">
                        <input type="text" name="fname" id="fname" value="" placeholder="First Name" >
                        <input type="text" name="lname" id="lname" value="" placeholder="Last Name">
                        <input type="text" name="password" id="password" value="" placeholder="Password">
                        <select name="role">
                        <c:forEach var="role" items="${roles}">                           
                            <option value="${role.roleId}">${role.roleName}  </option>
                        </c:forEach>
                        </select><br>
                        <input type="checkbox" name="active" id="active" value="">
                        <label for="active">User Active</label>
                        <input type="submit" value="Save">
                        <input type="hidden" name="action" value="add">
                    </form>
                </div>
            </div>
            <div class="column2">
                <h3>Manage Users</h3>
                <div class="manage_users">
                    <table>
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Password</th>
                            <th>Role Name</th>
                            <th>Active</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.password}</td>
                                <td>${user.role.roleName}</td>
                                <td>
                                    <input type="checkbox" name="active" id="active" <c:if test="${user.active}"> checked </c:if>>
                                </td>
                                <td><a href="users?action=view&&email=${user.email}"><i class="fa-regular fa-pen-to-square"></i></a>
                                </td>
                                <td><a href="users?action=delete&&email=${user.email}"><i class="fa-sharp fa-solid fa-user-slash"></i></a>
                                </td>
                            </tr> 
                        </c:forEach>
                          
                    
                    </table>
                    <p>${message}</p>
                </div> 
            </div>
            <div class="column3">
                <h3>Edit User</h3>
                <form action="users" method="POST">

                    <input type="text" name="email" id="email" value="${selectedUser.email}" placeholder="Email" readonly >
                    <input type="text" name="fname" id="fname" value="${selectedUser.firstName}" placeholder="First Name" >
                    <input type="text" name="lname" id="lname" value="${selectedUser.lastName}" placeholder="Last Name">
                    <input type="text" name="password" id="password" value="${selectedUser.password}" placeholder="Password">
                    
                     <select name="role">
                         
                       
                        <c:forEach var="role" items="${roles}">
                            <c:if test="${selectedUser.role.roleName eq role.roleName}">
                                <option value="${role.roleId}">${selectedUser.role.roleName}</option>
                            </c:if>                          

                        </c:forEach>
                                <c:forEach var="role" items="${roles}">
                                    <c:if test="${role.roleName ne selectedUser.role.roleName}">
                                        <option value="${role.roleId}">${role.roleName}  </option>
                                        
                                    </c:if>
                                </c:forEach>       
                          
                    </select>
                    
                    <input type="checkbox" name="active" id="active" <c:if test="${selectedUser.active}"> checked </c:if>> 
                    <label for="active">User Active</label>
                    <input type="submit" value="Save"> 
                    <input type="hidden" name="action" value="update">

                </form>
                <form action="users" method="POST">
                    <input type="submit" value="Cancel">
                    <input type="hidden" name="action" value="cancel">
                </form>                

        </div>
    </div>
    </body>
</html>
