<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All patients</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
<div class="patient-add-form-div">
    <div class="row">
        <table class="patient-add-table">
            <th>
            <td><h2>Delete user?</h2></td>
            </th>
                <tr>
                    <td>
                        <form:form method="POST" modelAttribute="usertodelete">
                            <form:hidden path="id"/>
                            <input type="submit" value="Yes"/>
                        </form:form>
                    </td>
                    <td><form:form method="GET" action="/user/all">
                        <input type="submit" value="No"/>
                    </form:form></td>
                </tr>
        </table>
    </div>
    <%-- END row --%>
</div>
<%-- END patient-add-form-div --%>
</body>
</html>
