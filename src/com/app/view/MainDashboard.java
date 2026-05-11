package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainDashboard extends JFrame {

    private JPanel pnlSidebar, pnlContent;
    private CardLayout cardLayout;
    private JLabel lblHeaderTitle;
    private String userRole;

    public MainDashboard(String userRole) {
        this.userRole = userRole; // Gán vai trò người dùng để phân quyền
        
        // Thiết lập khung cửa sổ chính
        setTitle("Carvip - Hệ thống Quản trị và Vận hành Thuê xe");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. KHỞI TẠO CÁC THÀNH PHẦN GIAO DIỆN
        initSidebar();
        initHeader();
        initContent();

        setVisible(true);
    }

    private void initSidebar() {
        pnlSidebar = new JPanel();
        pnlSidebar.setBackground(new Color(24, 28, 43));
        pnlSidebar.setPreferredSize(new Dimension(280, 850));
        pnlSidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        // Logo hệ thống
        JLabel lblLogo = new JLabel("CARVIP SYSTEM", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBorder(new EmptyBorder(30, 0, 40, 0));
        lblLogo.setPreferredSize(new Dimension(280, 100));
        pnlSidebar.add(lblLogo);

        // --- KHỞI TẠO CÁC NÚT MENU ---
        JButton btnHome = createMenuButton("🏠  Trang Chủ");
        
        // Tự động đổi tên nút Xe theo vai trò (Tìm kiếm vs Quản lý)
        String carText = userRole.equals("KHACH_HANG") ? "🔍  Tìm Xe & Đặt Xe" : "🚗  Quản Lý Đội Xe";
        JButton btnCars = createMenuButton(carText);
        
        JButton btnCustomers = createMenuButton("👤  Khách Hàng");
        JButton btnEnterprises = createMenuButton("🏢  Doanh Nghiệp");
        JButton btnEmployees = createMenuButton("👨‍💼  Nhân Viên");
        
        // Đổi tên nút Hợp đồng/Lịch sử theo vai trò
        String contractText = userRole.equals("KHACH_HANG") ? "📜  Lịch Sử Thuê" : "📝  Hợp Đồng & Giao Dịch";
        JButton btnContracts = createMenuButton(contractText);
        
        JButton btnReports = createMenuButton("📊  Báo Cáo Doanh Thu");
        JButton btnLogout = createMenuButton("🚪  Đăng Xuất");

        // --- LOGIC PHÂN QUYỀN SIDEBAR (Dựa trên Bảng 1.3) ---
        pnlSidebar.add(btnHome);
        pnlSidebar.add(btnCars);

        // Chỉ Admin, Doanh nghiệp hoặc Nhân viên mới quản lý hồ sơ Khách hàng
        if (userRole.equals("ADMIN") || userRole.equals("DOANH_NGHIEP") || userRole.equals("NHAN_VIEN")) {
            pnlSidebar.add(btnCustomers);
        }

        // Chỉ Admin quản lý danh mục Doanh nghiệp đối tác
        if (userRole.equals("ADMIN")) {
            pnlSidebar.add(btnEnterprises);
        }

        // Doanh nghiệp quản lý nhân viên của mình hoặc Admin quản lý tất cả
        if (userRole.equals("ADMIN") || userRole.equals("DOANH_NGHIEP")) {
            pnlSidebar.add(btnEmployees);
        }

        pnlSidebar.add(btnContracts);

        // Báo cáo dành cho Admin, Doanh nghiệp quản lý hoặc Chủ xe theo dõi lợi nhuận
        if (userRole.equals("ADMIN") || userRole.equals("DOANH_NGHIEP") || userRole.equals("CHU_XE")) {
            pnlSidebar.add(btnReports);
        }

        pnlSidebar.add(btnLogout);
        add(pnlSidebar, BorderLayout.WEST);

        // Xử lý sự kiện chuyển đổi màn hình (CardLayout)
        btnHome.addActionListener(e -> switchPage("HOME", "TỔNG QUAN HỆ THỐNG"));
        btnCars.addActionListener(e -> switchPage("CARS", carText.toUpperCase().substring(3)));
        btnCustomers.addActionListener(e -> switchPage("CUSTOMERS", "HỒ SƠ KHÁCH HÀNG"));
        btnEnterprises.addActionListener(e -> switchPage("ENTERPRISES", "ĐỐI TÁC DOANH NGHIỆP"));
        btnEmployees.addActionListener(e -> switchPage("EMPLOYEES", "DANH SÁCH NHÂN VIÊN"));
        btnContracts.addActionListener(e -> switchPage("CONTRACTS", contractText.toUpperCase().substring(3)));
        btnReports.addActionListener(e -> switchPage("REPORTS", "THỐNG KÊ & BÁO CÁO"));
        
        // Sự kiện đăng xuất có hộp thoại xác nhận
        btnLogout.addActionListener(e -> handleLogout());
    }

    private void initHeader() {
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(1020, 70));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        lblHeaderTitle = new JLabel("BẢNG ĐIỀU KHIỂN CHÍNH");
        lblHeaderTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeaderTitle.setBorder(new EmptyBorder(0, 30, 0, 0));
        pnlHeader.add(lblHeaderTitle, BorderLayout.WEST);

        JLabel lblInfo = new JLabel("Vai trò: " + userRole + " | Đang trực tuyến  ");
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        pnlHeader.add(lblInfo, BorderLayout.EAST);

        add(pnlHeader, BorderLayout.NORTH);
    }

    private void initContent() {
        cardLayout = new CardLayout();
        pnlContent = new JPanel(cardLayout);
        pnlContent.setBackground(new Color(245, 245, 245));

        // Placeholder cho các module dữ liệu (Dựa trên Schema và Bảng yêu cầu)
        pnlContent.add(createPlaceholder("TRANG CHỦ: TỔNG QUAN VẬN HÀNH"), "HOME");
        pnlContent.add(createPlaceholder("MODUL XE: THÔNG TIN KỸ THUẬT & BẢO DƯỠNG"), "CARS");
        pnlContent.add(createPlaceholder("MODUL KHÁCH HÀNG: CÁ NHÂN & DOANH NGHIỆP"), "CUSTOMERS");
        pnlContent.add(createPlaceholder("MODUL DOANH NGHIỆP: QUẢN LÝ PHÁP LÝ"), "ENTERPRISES");
        pnlContent.add(createPlaceholder("MODUL NHÂN VIÊN: HỒ SƠ & HIỆU SUẤT"), "EMPLOYEES");
        pnlContent.add(createPlaceholder("MODUL GIAO DỊCH: HỢP ĐỒNG & BIÊN BẢN"), "CONTRACTS");
        pnlContent.add(createPlaceholder("MODUL BÁO CÁO: CHI PHÍ & LỢI NHUẬN"), "REPORTS");

        add(pnlContent, BorderLayout.CENTER);
    }

    private void handleLogout() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn đăng xuất khỏi phiên làm việc này?",
            "Xác nhận đăng xuất",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginFrame().setVisible(true);
        }
    }

    private void switchPage(String cardName, String title) {
        cardLayout.show(pnlContent, cardName);
        lblHeaderTitle.setText(title);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(260, 48));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(24, 28, 43));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(0, 30, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hiệu ứng Hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(40, 45, 65));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(24, 28, 43));
            }
        });
        return btn;
    }

    private JPanel createPlaceholder(String text) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        l.setForeground(Color.LIGHT_GRAY);
        p.add(l);
        return p;
    }
}