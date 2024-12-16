<%@page import="java.util.ArrayList"%>
<%@page import="model.Task"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Result Page</title>
        <link rel="stylesheet" href="css/result.css">
    </head>
    <body>      
        <div class="container">
            <header class="header">
            <div class="header-content">
                <span>Xin chào, <strong id="username"><%= session.getAttribute("username") != null ? session.getAttribute("username") : "Tên đăng nhập" %></strong></span>
                <a href="login.jsp" class="logout-link">Đăng Xuất</a>
            </div>
        </header>
            <div class="form-container">
                <h2>Kết Quả Dịch Văn Bản</h2>
                <div class="textarea-container">
                    <textarea id="inputArea" placeholder="Văn bản cần dịch..." readonly>
                        <%= request.getAttribute("originalText") != null ? request.getAttribute("originalText") : ""%>
                    </textarea>
                    <textarea id="resultArea" readonly>
                        <%= request.getAttribute("translatedText") != null ? request.getAttribute("translatedText") : "Dưới đây là kết quả dịch văn bản của bạn..."%>
                    </textarea>
                </div>
                <div class="button-group">
                    <button class="submit-btn" onclick="downloadFile()">Tải Xuống File</button>
                    <button class="submit-btn" onclick="goBack()">Quay Lại</button>
                </div>
            </div>

            <!-- Khu vực danh sách file -->
            <div class="file-list-container">
                <h3>Danh Sách File</h3>
                <div id="fileList">
                    <%
                        ArrayList<Task> tasks = (ArrayList<Task>) session.getAttribute("tasks");
                        if (tasks != null) {
                            for (int i = 0; i < tasks.size(); i++) {
                                Task task = tasks.get(i);
                                String preview = task.getOriginalText().length() > 20
                                        ? task.getOriginalText().substring(0, 20) + "..."
                                        : task.getOriginalText();
                    %>
                    <form method="POST" action="ShowResultServerlet" class="file-item-form">
                        <input type="hidden" name="fileIndex" value="<%= i%>">
                        <button type="submit" class="file-item">
                            <span><%= preview%></span>
                        </button>
                    </form>
                    <% if (i < tasks.size() - 1) { %>
                    <hr class="file-separator">
                    <% }
                        }
                    } else { %>
                    <p>Không có file nào trong danh sách.</p>
                    <% }%>
                </div>
            </div>
        </div>

        <script>
            // Hàm tải xuống file
            function downloadFile() {
                const text = document.getElementById('resultArea').value;
                const blob = new Blob([text], {type: 'text/plain'});
                const link = document.createElement('a');
                link.href = URL.createObjectURL(blob);
                link.download = 'ket_qua_dich.txt';
                link.click();
                URL.revokeObjectURL(link.href);
            }

            function goBack() {
                window.location.href = 'handle.jsp';
            }
        </script>
    </body>
</html>
