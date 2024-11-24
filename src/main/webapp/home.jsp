<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Người Dùng và Tác Vụ</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>

    <div class="container">
        <!-- Header -->
        <header>
            <div class="logo">
                <h1>Chức Năng Hệ Thống</h1>
            </div>
            <div class="user-info">
                <c:choose>
                  <c:when test="${not empty sessionScope.user}">
                   <span id="username">Xin chào, <strong>${sessionScope.user.username}</strong></span>
				    </c:when>
	               <c:otherwise>
				        <a href="login" id="login" style="color: black;">Đăng nhập</a>
				    </c:otherwise>
                </c:choose>
            </div>
        </header>

        <!-- Main content with Image -->
        <section class="main-content">
            <div class="image-section">
                <img src="https://i.pinimg.com/originals/3e/7e/0c/3e7e0cad7d4680f993d3527a0557a015.jpg" alt="Image" />
            </div>

            <div class="function-section">
                <h2>Chức Năng Chính</h2>

                <!-- Các chức năng hệ thống -->
                <div id="translategdata" class="function-box">
                    <h3>Dịch văn bản</h3>
                    <p>Chức năng này giúp xử lý các bộ dữ liệu lớn bằng các thuật toán tối ưu và phần cứng phân tán.</p>
                     <a href="handle.jsp?functionName=translate" class="button-link">
                        <button>Thực hiện</button>
                    </a>
                </div>

                <div id="convertpdf" class="function-box">
                    <h3>Chuyển đổi PDF -> DOC</h3>
                    <p>Chức năng này giúp chuyển đổi các tài liệu PDF sang định dạng DOC dễ chỉnh sửa hơn.</p>
                   <a href="handle.jsp?functionName=convertPdf" class="button-link">
                        <button>Thực hiện</button>
                    </a>
                </div>

                <div id="plagiarism" class="function-box">
                    <h3>Kiểm Tra Đạo Văn Tự Động</h3>
                    <p>Chức năng này giúp kiểm tra và phát hiện đạo văn trong tài liệu tự động.</p>
                    <a href="handle.jsp?functionName=checkPlagiarism" class="button-link">
                        <button>Thực hiện</button>
                    </a>
                </div>
            </div>
        </section>

    </div>

</body>
</html>