1.Thư mục repository thì sẽ làm theo cấu trúc sau:
xử lý cơ sở dữ liệu
2.Thư mục service thì sẽ làm theo cấu trúc sau:
interface sẽ có những phương thức cần làm
file impl sẽ làm việc kế thừa những phuong thức của interface service logic sẽ được viết ở đây
3.Thư mục controller thì sẽ làm theo cấu trúc sau:
file controller sẽ làm việc với các phương thức của service
interface sẽ được autowired vào controller
4.Thư mục dto thì sẽ làm theo cấu trúc sau:
file dto sẽ chứa các thuộc tính của entity cần phải truyền vào
dto sẽ có những thuộc tính cần thiết  để khong phải làm việc với entity
5.thư mục mapper thì sẽ làm theo cấu trúc sau:
file mapper sẽ chứa các phương thức để chuyển đổi từ entity sang dto và ngược lại không cần phải dùng builder

