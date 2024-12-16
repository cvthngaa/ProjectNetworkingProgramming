<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	 <div class="login_form" id="login_form">
        <div class="left">
            <img src="https://i.pinimg.com/736x/20/44/8f/20448fb4c6c278be29c79695fbd02ff7.jpg">
        </div>
    
        <div class="right">
            <h1>Đăng nhập!</h1>
             <p style="color: red">${sessionScope.errorMessage}</p>

            <form action="login" method="post">
                <p>Tên người dùng</p>
                <div class="user">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" name="username" placeholder="Tên đăng nhập" class="username">
                </div>

                <p class="passworg_tag">Mật khẩu</p>
                <div class="password">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="password" placeholder="Mật khẩu">
                </div>    
                <br> <button type="submit">Đăng nhập</button>
        
            </form>
    
            <p style="margin-top:10px">Bạn chưa có tài khoản? <a href="register.jsp" style="color: red; margin-left:40px ; font-size: 18px" class="register-link">Đăng ký</a></p>
        </div>
    </div>
</body>
</html>