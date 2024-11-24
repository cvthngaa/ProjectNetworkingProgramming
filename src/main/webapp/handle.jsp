<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Handle data</title>
	<link rel="stylesheet" href="css/handle.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="container">
	        <div class="image-container">
	            <img src="https://i.pinimg.com/control2/736x/03/2b/b4/032bb4387d0796e02d7e23ac92adea99.jpg" alt="Hình ảnh minh họa" class="form-image">
	        </div>
	        <div class="form-container">
	            <h2>Gửi Yêu Cầu Xử Lý Dữ Liệu</h2>
	            <form action="handleData" method="POST" class="data-form">
	                <label for="data">Nhập Dữ Liệu Cần Xử Lý:</label>
	                <textarea id="data" name="data" placeholder="Nhập dữ liệu ở đây..." required rows="6"></textarea>
	                <input type="hidden" name="user" value="${sessionScope.user.username}">
	                <input type="hidden" name="function" value="${param.functionName}">
	                
	              	<input type="submit" class="submit-btn" value="Gửi Yêu Cầu">
	            </form>
	        </div>
	 </div>
</body>
</html>