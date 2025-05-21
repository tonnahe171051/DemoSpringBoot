# Báo Cáo Tiến Độ: Kiến Trúc Phân Lớp (Layered Architecture)
https://github.com/tonnahe171051/DemoSpringBoot

## 1. Mục Tiêu Đã Hoàn Thành

### Components đã phát triển
- Thêm validate cho model DTO thông qua annotation (vd: @Nonnull), sửa lại kiểu trả về của action bên RestController để trả về status theo chuẩn rest
- Triển khai thêm component Exception để chứa class xử lý Exception tập trung
- Tạo class GlobalExceptionHandler với 2 phương thức chính để xử lý lỗi MethodArgumentNotValidException và RuntimeException

### Docker & CI/CD
- Containerization với Docker và Docker Compose
- Cấu hình môi trường dev độc lập với MySQL container
- Chuẩn bị cấu hình cho GitHub Actions CI/CD

## 2. Kết Quả Đạt Được

- API có validate hoàn chỉnh, hoạt động ổn định với MySQL
- API được tài liệu hóa với OpenAPI/Swagger
- Tổ chức code theo chuẩn kiến trúc phân lớp, dễ bảo trì và mở rộng
- Xây dựng môi trường Docker cho phát triển và kiểm thử

## 3. Kế Hoạch Tiếp Theo

- Spring Security (đang tìm hiểu, đã làm việc với JWT trước đó)

