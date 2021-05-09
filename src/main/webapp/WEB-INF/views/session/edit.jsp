<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit session</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<div class="patient-add-form-div">
    <form:form method="post" modelAttribute="sessiontoedit">
        <div class="row">
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><h2>Edit session</h2></td>
                    </tr>
                    <tr>
                        <td><form:label path="date">Date:</form:label></td>
                        <td><form:input type="date" path="date" value="${sessiontoedit.date}"/><form:errors path="date" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="time">Time:</form:label></td>
                        <td><form:select items="${availablehours}" path="time" /><form:errors path="time" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="patients">Patients:</form:label></td>
                        <td><form:select path="patients" items="${availablepatients}" multiple="true" itemValue="id" itemLabel="fullName"/><form:errors path="patients"
                                                                                                                                                        cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="user.id">User:</form:label></td>
                        <td><form:select path="user.id" items="${availableusers}" itemLabel="username" itemValue="id"/><form:errors path="user.id"
                                                                                                                                    cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="paymentDue">Expected payment:</form:label></td>
                        <td><form:input type="number" min="0" step="0.01" path="paymentDue"/><form:errors path="paymentDue"
                                                                                                          cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="paymentActual">Actual payment:</form:label></td>
                        <td><form:input type="number" min="0" step="0.01" path="paymentActual"/><form:errors path="paymentActual"
                                                                                                             cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Save"/></td>
                        <form:hidden path="id"/>
                        <form:hidden path="created"/>
                    </tr>
                </table>
            </div>
                <%-- END column --%>
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><form:label path="notes">Notes:</form:label></td>
                        <td><form:textarea path="notes" cssClass="patient-desc"/></td>
                    </tr>
                </table>
            </div>
                <%-- END column --%>
        </div><%-- END row --%>
    </form:form>
</div>
<%-- END patient-add-form-div --%>
</body>
</html>
