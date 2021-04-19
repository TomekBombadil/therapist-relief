<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add patient</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<div class="patient-add-form-div">
    <form:form method="post" modelAttribute="newpatient">
        <div class="row">
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><h2>New patient</h2></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName">First name:</form:label></td>
                        <td><form:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Last name:</form:label></td>
                        <td><form:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email:</form:label></td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phone">Phone:</form:label></td>
                        <td><form:input path="phone"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="pesel">Pesel:</form:label></td>
                        <td><form:input path="pesel"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="rodoFile">Rodo file:</form:label></td>
                        <td><form:input path="rodoFile"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Save"/></td>
                    </tr>
                </table>
            </div>
                <%-- END column --%>
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><form:label path="description">Description:</form:label></td>
                        <td><form:textarea path="description" cssClass="patient-desc"/></td>
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
