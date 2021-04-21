<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All patients</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<a href="<c:url value="/patient/add"/>">Add</a>
<h2>Patients:</h2>
<div class="patient-add-form-div">
    <div class="row">
        <table class="patient-add-table">
            <tr>
                <td>First name</td>
                <td>Last name</td>
                <td>Email</td>
                <td>Phone</td>
                <td>Pesel</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${allpatients}" var="patient">
                <tr>
                    <td>${patient.firstName}</td>
                    <td>${patient.lastName}</td>
                    <td>${patient.email}</td>
                    <td>${patient.phone}</td>
                    <td>${patient.pesel}</td>
                    <td><a href="<c:url value="/patient/edit?id=${patient.id}"/>">Edit</a></td>
                    <td><a href="<c:url value="/patient/delete?id=${patient.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%-- END row --%>
</div>
<%-- END patient-add-form-div --%>
</body>
</html>
