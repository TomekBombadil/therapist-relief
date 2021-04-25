<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add user</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<div class="patient-add-form-div">
    <form:form method="post" modelAttribute="newuser">
        <div class="row">
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><h2>New user</h2></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName">First name:</form:label></td>
                        <td><form:input path="firstName"/><form:errors path="firstName" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Last name:</form:label></td>
                        <td><form:input path="lastName"/><form:errors path="lastName" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email:</form:label></td>
                        <td><form:input path="email"/><form:errors path="email" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="password">Password:</form:label></td>
                        <td><form:password path="password"/><form:errors path="password" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="role">Role:</form:label></td>
                        <td><form:input path="role"/><form:errors path="role" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Save"/></td>
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
