# IS210.Q22
## Kiến trúc hệ thống
Dự án được xây dựng theo mô hình **MVC (Model-View-Controller)** nhằm tách biệt giao diện và logic xử lý dữ liệu:
- **Model**: Đại diện cho 13 thực thể trong database (Xe, Hợp đồng, Khách hàng...).
- **View**: Giao diện Java Swing được chia thành các Panels và Dialogs chuyên biệt.
- **Controller**: Bộ não điều khiển, tính toán doanh thu và kiểm tra trạng thái vận hành.

---

## Sơ đồ cơ sở dữ liệu (Schema)
Hệ thống quản trị dựa trên 13 bảng thực thể trong Oracle Database:
1. `TAIKHOAN`: Quản lý định danh và 5 vai trò (Admin, DN, NV, Chủ xe, Khách).
2. `KHACHHANG`: Hồ sơ cá nhân, CCCD và bằng lái.
3. `DOANHNGHIEPQUANLI`: Thông tin pháp lý đơn vị vận hành.
4. `NHANVIEN`: Đội ngũ trực tiếp ký biên bản bàn giao.
5. `XE` & `LOAIXE`: Thông tin kỹ thuật, biển số và đơn giá thuê.
6. `CHUXE`: Đối tác ký gửi xe vào hệ thống.
7. `HOPDONG` & `CT_HOPDONG_XE`: Chi tiết thời gian và địa điểm thuê.
8. `BIENBAN`: Ghi nhận tình trạng xe lúc nhận/trả.
9. `HOADON` & `THANHTOAN`: Xử lý tài chính, phụ phí và tiền thuê gốc.
10. `KHUYENMAI`: Các chương trình ưu đãi theo thời điểm.

---

## Các tính năng chính
- **Phân quyền đa vai trò**: Tự động thay đổi menu Sidebar dựa trên vai trò người dùng.
- **Quản lý Đội xe**: Theo dõi trạng thái xe (Trống/Đang thuê/Bảo trì) và lịch kỹ thuật.
- **Quy trình Thuê xe khép kín**: Từ tìm kiếm xe -> Lập hợp đồng -> Ký biên bản bàn giao -> Thanh toán.
- **Báo cáo Tài chính**: Thống kê doanh thu theo doanh nghiệp và lợi nhuận thực nhận của chủ xe.

---

## Hướng dẫn cài đặt & Chạy ứng dụng
1. **Yêu cầu hệ thống**:
   - Java JDK 17 trở lên.
   - Oracle Database 19c/21c.
   - Thư viện `ojdbc8.jar` (để kết nối database).

2. **Cài đặt Database**:
   - Chạy script tạo bảng trong file `SQL/schema.sql`.
   - Cấu hình thông tin tài khoản trong file `src/dao/JDBCConnection.java`.

3. **Chạy ứng dụng**:
   - Mở dự án bằng IDE (NetBeans/IntelliJ/VS Code).
   - Chạy file `src/main/MainApp.java` (hoặc `LoginFrame.java`).

---

## 🛠️ Công cụ phát triển
- **Ngôn ngữ**: Java (Swing Framework).
- **Cơ sở dữ liệu**: Oracle SQL.
