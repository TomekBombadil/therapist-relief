<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit patient</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp" %>
<div class="patient-add-form-div">
    <form:form method="post" action="/patient/edit?${_csrf.parameterName}=${_csrf.token}" modelAttribute="patienttoedit" enctype="multipart/form-data">
        <div class="row">
            <div class="column">
                <table class="patient-add-table">
                    <tr>
                        <td><h2>Edit patient</h2></td>
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
                        <td><form:label path="phone">Phone:</form:label></td>
                        <td><form:input path="phone"/><form:errors path="phone" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="pesel">Pesel:</form:label></td>
                        <td><form:input path="pesel"/><form:errors path="pesel" cssClass="validation-error-text"/></td>
                    </tr>
                    <tr>
                        <td><label for="rodoFile">Rodo file:</label></td>
<%--                        name musi być inne niż pole w encji, żeby się nie próbowało bindować, tylko przez @RequestParam pobieram i setterem ustawiam--%>
                        <td><input type="file" name="rodoFileToUpload" id="rodoFile"/></td>
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
