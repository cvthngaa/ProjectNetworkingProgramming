<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <!-- Register Form -->
    <div class="register_form" id="register_form">
        <div class="left"></div>

        <div class="right">
            <h1>Đăng ký!</h1>

            <form action="register" method="post">
                <p>Tên người dùng</p>
                <div class="user">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" name="username" placeholder="Tên đăng nhập" class="username">
                </div>

                <p class="password_tag">Mật khẩu</p>
                <div class="password">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="password" placeholder="Mật khẩu">
                </div>

                <p class="password_tag">Xác nhận mật khẩu</p>
                <div class="password">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="confirm_password" placeholder="Xác nhận mật khẩu">
                </div>

                <button type="submit">Đăng ký</button>
            </form>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <div class="message">
                    <%= message %>
                </div>
            <%
                }
            %>
            <p style="margin-top: 10px">Bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
        </div>
    </div>
</body>
</html>
