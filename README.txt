Cấu trúc thư mục dự án

1. Repository (Xử lý cơ sở dữ liệu)

Thư mục này chứa các lớp repository để làm việc với cơ sở dữ liệu.

Chịu trách nhiệm thao tác với dữ liệu trong database.

Sử dụng JPA hoặc JDBC để truy vấn dữ liệu.

2. Service (Xử lý logic nghiệp vụ)

Thư mục này chứa các lớp service để xử lý logic nghiệp vụ.

Interface: Định nghĩa các phương thức cần thực hiện.

Impl (Implementation): Kế thừa interface và triển khai các phương thức, xử lý logic nghiệp vụ tại đây.

3. Controller (Xử lý request từ client)

Thư mục này chứa các lớp controller để xử lý request từ client.

Gọi các phương thức từ service để xử lý yêu cầu.

Interface service được Autowired vào controller.

4. DTO (Data Transfer Object)

Thư mục này chứa các lớp DTO để truyền dữ liệu giữa các lớp trong hệ thống.

Chỉ chứa các thuộc tính cần thiết của entity, giúp tránh làm việc trực tiếp với entity.

Giúp tối ưu hóa việc truyền dữ liệu giữa các tầng.

5. Mapper (Chuyển đổi giữa Entity và DTO)

Thư mục này chứa các lớp mapper để chuyển đổi giữa entity và DTO.

Chuyển đổi từ entity sang DTO và ngược lại.

Không cần sử dụng builder, chỉ viết phương thức chuyển đổi đơn giản.