<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Handle data</title>
	<link rel="stylesheet" href="css/handle.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<style type="text/css">
		select {
		margin: 6px;
	    border: 3px solid #d2c5c5;
	    padding: 4px;
	    border-radius: 7px
		}
	</style>
</head>
<body>
	<div class="container">
	        <div class="image-container">
	            <img src="https://i.pinimg.com/control2/736x/03/2b/b4/032bb4387d0796e02d7e23ac92adea99.jpg" alt="Hình ảnh minh họa" class="form-image">
	        </div>
	        <div class="form-container">
	            <h2>Gửi Yêu Cầu Xử Lý Dữ Liệu</h2>
	            <form action="http://localhost:8080/BTCKi/upload" method="POST" class="data-form" enctype="multipart/form-data">
	                <label for="data">Nhập Dữ Liệu Cần Xử Lý:</label>
	                 <input type="file" id="file" name="file" required><br><br>
	                <input type="hidden" name="user" value="${sessionScope.user.username}">
	                <c:if test="${param.functionName == 'translate'}">
	                <label for="data">Nhập ngôn ngữ văn bản hiện tại:</label>
	                  <input type="text" name="sourceLanguage"/> 
	                </c:if>
	                
	                <input type="hidden" name="function" value="${param.functionName}">
	                <c:if test="${param.functionName == 'translate'}">
	                    <label for="targetLanguage">Ngôn ngữ đích:</label>
	                    <select id="targetLanguage" name="targetLanguage" required>
	                        <option value="en">Tiếng Anh</option>
	                        <option value="vi">Tiếng Việt</option>
	                        <option value="fr">Tiếng Pháp</option>
	                        <option value="es">Tiếng Tây Ban Nha</option>
	                        <option value="zh">Tiếng Trung</option>
	                    </select>
	                </c:if>
	                
	              	<input type="submit" class="submit-btn" value="Gửi Yêu Cầu">
	            </form>
	        </div>
	 </div>
</body>
</html>