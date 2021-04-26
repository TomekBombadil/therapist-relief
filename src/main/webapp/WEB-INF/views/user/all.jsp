<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<a href="<c:url value="/user/add"/>">Add</a>
<h2>Users:</h2>
<div class="patient-add-form-div">
    <div class="row">
        <table class="patient-add-table">
            <tr>
                <td>Username</td>
                <td>Email</td>
                <td>Enabled</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${allusers}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td><a href="<c:url value="/user/edit?id=${user.id}"/>">Edit</a></td>
                    <td><a href="<c:url value="/user/delete?id=${user.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%-- END row --%>
</div>
<%-- END patient-add-form-div --%>
</body>
</html>
