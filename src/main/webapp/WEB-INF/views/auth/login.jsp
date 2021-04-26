<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post">
    <h2>Please provide email and password</h2>
    <p>
    <label for="email">Email</label>
    <input type="text" id="email" name="email" placeholder="Email" required autofocus/>
    </p>
    <p>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Password" required/>
    </p>
    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
    <button type="submit">Sign in</button>
</form>
</body>
</html>
