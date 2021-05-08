<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Your upcoming sessions</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<a href="<c:url value="/session/add"/>">Add</a>
<a href="<c:url value="/auth/logout"/>">Logout</a>
<h2>Your upcoming sessions:</h2>
<div class="patient-add-form-div">
    <div class="row">
        <table class="patient-add-table">
            <tr>
                <td>Date</td>
                <td>Time</td>
                <td>Patients</td>
                <td>Therapist</td>
                <td>Payment</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${upcoming}" var="session">
                <tr>
                    <td>${session.date}</td>
                    <td>${session.time}</td>
                    <td>${session.patientsNames}</td>
                    <td>${session.user.username}</td>
                    <td>${session.paymentDiff}</td>
                    <td><a href="<c:url value="/session/edit?id=${session.id}"/>">Edit</a></td>
                    <td><a href="<c:url value="/session/delete?id=${session.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%-- END row --%>
</div>
<%-- END patient-add-form-div --%>
</body>
</html>
