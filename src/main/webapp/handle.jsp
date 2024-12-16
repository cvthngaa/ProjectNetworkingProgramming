<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gửi yêu cầu xử lý</title>
        <link rel="stylesheet" href="css/handle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container">
            <%
                String username = (String) session.getAttribute("username");
            %>
            <header class="header">
                <div class="header-content">
                    <span>Xin chào, <strong id="username"><%= username != null ? username : "Tên đăng nhập"%></strong></span>
                </div>
            </header>
            <div class="container-form">
                <div class="image-container">
                    <img src="https://i.pinimg.com/control2/736x/03/2b/b4/032bb4387d0796e02d7e23ac92adea99.jpg" alt="HÃ¬nh áº£nh minh há»a" class="form-image">
                </div>
                <div class="form-container">
                    <h2>Gửi yêu cầu xử lý</h2>

                    <form id="dataForm" action="http://localhost:8088/BTCKi/upload" method="POST" class="data-form" enctype="multipart/form-data">
                        <div class="submit-btn-container">
                            <button type="button" class="submit-btn add-file-btn" onclick="addFileUpload()">Thêm file</button>
                            <input type="submit" class="submit-btn" value="Gửi yêu cầu">
                        </div>
                        <input type="hidden" name="user" value="${sessionScope.user.username}">
                        <input type="hidden" name="function" value="${param.functionName}">
                        <input type="hidden" id="fileCount" name="fileCount" value="1">

                        <div id="fileUploadSection" class="function-box">
                            <label for="file-0">Chọn File:</label>
                            <input type="file" name="file-0" required><br><br>

                            <label for="sourceLanguage-0">Ngôn ngữ nguồn:</label>
                            <select name="sourceLanguage-0" required>
                                <option value="en">Tiếng Anh</option>
                                <option value="vi">Tiếng Việt</option>
                                <option value="fr">Tiếng Pháp</option>
                                <option value="es">Tiếng Tây Ban Nha</option>
                                <option value="zh">Tiếng Trung</option>
                            </select><br>

                            <label for="targetLanguage-0">Ngôn ngữ đích:</label>
                            <select name="targetLanguage-0" required>
                                <option value="en">Tiếng Anh</option>
                                <option value="vi">Tiếng Việt</option>
                                <option value="fr">Tiếng Pháp</option>
                                <option value="es">Tiếng Tây Ban Nha</option>
                                <option value="zh">Tiếng Trung</option>
                            </select><br>
                        </div>



                    </form>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function addFileUpload() {
                const fileUploadSection = document.getElementById('dataForm');
                const fileCount = document.getElementById('fileCount');
                const currentCount = parseInt(fileCount.value);

                const newFunctionBox = document.createElement('div');
                newFunctionBox.classList.add('function-box');
                newFunctionBox.id = 'function-box-' + currentCount;

                newFunctionBox.innerHTML = `
            <label for="file-${currentCount}">Chọn file</label>
            <input type="file" name="file-${currentCount}" required><br><br>

            <label for="sourceLanguage-${currentCount}">Ngôn ngữ nguồn:</label>
            <select name="sourceLanguage-${currentCount}" required>
                <option value="en">Tiếng Anh</option>
                <option value="vi">Tiếng Việt</option>
                <option value="fr">Tiếng Pháp</option>
                <option value="es">Tiếng Tây Ban Nha</option>
                <option value="zh">Tiếng Trung</option>
            </select><br>

            <label for="targetLanguage-${currentCount}">Ngôn ngữ đích:</label>
            <select name="targetLanguage-${currentCount}" required>
                <option value="en">Tiếng Anh</option>
                <option value="vi">Tiếng Việt</option>
                <option value="fr">Tiếng Pháp</option>
                <option value="es">Tiếng Tây Ban Nha</option>
                <option value="zh">Tiếng Trung</option>
            </select><br>
        `;

                fileUploadSection.appendChild(newFunctionBox);
                fileCount.value = currentCount + 1;
            }
        </script>


    </body>
</html>
