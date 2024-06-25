<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Page</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container" id="login-id">
    <h1>Login</h1>
    <!-- login page Html source -->
    <form id="login-form-id" action="<c:url value="/login"/>" method="post" modelAttribute="loginDTO">
        <div class="login-form">
            <div class="input-info">
                <label class="label" for="username">Username:</label>
                <input
                        class="input"
                        type="text"
                        id="username"
                        name="username"
                        placeholder="Username"
                        required
                />
            </div>
            <div class="input-info">
                <label class="label" for="password">Password:</label>
                <input
                        class="input"
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Password"
                        required
                />
            </div>
            <div class="submit">
                <button type="submit" class="sign-in-button">LOG IN</button>
            </div>
            <div id="popup" class="popup">
                <div class="popup-content">
                    <div id="popup-noti"></div>
                    <div id="popup-message"></div>
                    <div id="close-popup">
                        Confirm
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${not empty errorMessage}">
            <div style="color: red">${errorMessage}</div>
        </c:if>

        <div class="register-link">
            Not yet registered?
            <a href="/register">Sign Up</a>
        </div>
    </form>
    <c:if test="${not empty message}">
        <p id="noti" style="display: none">${noti}</p>
        <p id="message" style="display: none">${message}</p>
    </c:if>
</div>

<script src="${pageContext.request.contextPath}/js/handle_popup.js"></script>
<script src="${pageContext.request.contextPath}/js/login-page.js"></script>

</body>
</html>