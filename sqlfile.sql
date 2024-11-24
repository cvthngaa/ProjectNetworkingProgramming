use ltm;
create table users (
  id int AUTO_increment primary key , 
  username varchar(50) not null unique,
  password varchar(255) not null , 
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
  );
  
  CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    input_data TEXT NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING',
    result TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
INSERT INTO users (username, password)
VALUES ('thunga', '123123'), ('minhtoan', '0011');
INSERT INTO tasks (user_id, input_data, status, result)
VALUES 
    (1, 'Đây là văn bản đầu vào của tác vụ 1. Đoạn văn bản này sẽ được xử lý trong hệ thống để kiểm tra các chức năng cơ bản như lưu trữ và quản lý trạng thái.', 'PENDING', NULL), 
    (2, 'Nội dung văn bản này sẽ được xử lý qua các bước kiểm tra và phân tích dựa trên các quy tắc đã định trước trong hệ thống.', 'PENDING', NULL),
    (1, 'Văn bản này đã được xử lý xong và kết quả của tác vụ này sẽ được lưu vào cơ sở dữ liệu, trả về cho người dùng sau khi hoàn tất.', 'COMPLETED', 'Kết quả xử lý tác vụ 3: Đoạn văn bản đã được phân tích và xử lý thành công, trả về kết quả như mong đợi.');  -- Tác vụ đã hoàn thành cho người dùng 1

select * from users;
select * from tasks;