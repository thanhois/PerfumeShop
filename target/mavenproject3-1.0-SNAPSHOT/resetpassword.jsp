<%-- 
    Document   : resetpassword
    Created on : Jul 8, 2025, 3:48:27 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2 style="text-align:center">Reset Your Password</h2>
    <form action="newpassword" method="post" style="max-width:400px;margin:auto">
        <label for="username">Username</label><br>
        <input type="text" name="username" id="username" required><br><br>

        <label for="code">Verification Code</label><br>
        <input type="text" name="code" id="code" required><br><br>

        <label for="password">New Password</label><br>
        <input type="password" name="password" id="password" required><br><br>

        <label for="repassword">Confirm Password</label><br>
        <input type="password" name="repassword" id="repassword" required><br><br>

        <input type="submit" value="Reset Password">
    </form>
    <p style="color:red;text-align:center;">
        ${error != null ? error : ""}
    </p>
    <p style="color:green;text-align:center;">
        ${mess != null ? mess : ""}
    </p>
</body>
</html>