package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import view.Panels.CarPanel;
import view.Panels.ContractPanel;
import view.Panels.CustomerPanel;
import view.Panels.EmployeePanel;
import view.Panels.FinancePanel;
import view.Panels.HomePanel;
import view.Panels.PromoPanel;

import java.awt.*;

public class MainDashboard extends JFrame {
    private JPanel pnlSidebar, pnlContent;
    private CardLayout cardLayout;
    private JLabel lblHeaderTitle;
    private String userRole;

    public MainDashboard(String userRole) {
        this.userRole = userRole; // Nhận vai trò từ LoginFrame
        setTitle("Carvip - Quản lý Hệ thống Thuê xe");
        setSize(1350, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initSidebar();
        initHeader();
        initContent();
        
        // Mặc định hiển thị Trang chủ khi vừa mở app
        switchPage("HOME", "TỔNG QUAN HỆ THỐNG"); 
    }

    private void initSidebar() {
        pnlSidebar = new JPanel();
        pnlSidebar.setBackground(new Color(24, 28, 43));
        pnlSidebar.setPreferredSize(new Dimension(280, 850));
        pnlSidebar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel lblLogo = new JLabel("CARVIP SYSTEM", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setBorder(new EmptyBorder(30, 0, 30, 0));
        lblLogo.setPreferredSize(new Dimension(280, 80));
        pnlSidebar.add(lblLogo);

        // --- 1. Trang Chủ ---
        JButton btnHome = createMenuButton("Trang Chủ");
        btnHome.addActionListener(e -> switchPage("HOME", "TỔNG QUAN HỆ THỐNG"));
        pnlSidebar.add(btnHome);

        // --- 2. Module XE & LOAIXE (Bảng XE, LOAIXE) ---
        String carLabel = userRole.equals("KHACH_HANG") ? "Tìm & Thuê Xe" : "Quản Lý Đội Xe";
        JButton btnCars = createMenuButton(carLabel);
        btnCars.addActionListener(e -> switchPage("CARS", carLabel.substring(2)));
        pnlSidebar.add(btnCars);

        // --- 3. Module KHACHHANG ---
        if (!userRole.equals("KHACH_HANG") && !userRole.equals("CHU_XE")) {
            JButton btnCust = createMenuButton("Khách Hàng");
            btnCust.addActionListener(e -> switchPage("CUSTOMERS", "QUẢN LÝ KHÁCH HÀNG"));
            pnlSidebar.add(btnCust);
        }

        // --- 4. Module DOANHNGHIEP & CHUXE (Chỉ ADMIN) ---
        if (userRole.equals("ADMIN")) {
            JButton btnEnt = createMenuButton("Doanh Nghiệp");
            btnEnt.addActionListener(e -> switchPage("ENTERPRISES", "ĐỐI TÁC DOANH NGHIỆP"));
            pnlSidebar.add(btnEnt);

            JButton btnOwner = createMenuButton("Đối Tác Chủ Xe");
            btnOwner.addActionListener(e -> switchPage("OWNERS", "HỒ SƠ CHỦ XE"));
            pnlSidebar.add(btnOwner);
        }

        // --- 5. Module NHANVIEN (Admin & Doanh nghiệp quản lý nhân viên) ---
        if (userRole.equals("ADMIN") || userRole.equals("DOANH_NGHIEP")) {
            JButton btnEmp = createMenuButton("Đội Ngũ Nhân Viên");
            btnEmp.addActionListener(e -> switchPage("EMPLOYEES", "QUẢN LÝ NHÂN VIÊN"));
            pnlSidebar.add(btnEmp);
        }

        // --- 6. Module HOPDONG & BIENBAN ---
        JButton btnContract = createMenuButton("Hợp Đồng & Biên Bản");
        btnContract.addActionListener(e -> switchPage("CONTRACTS", "GIAO DỊCH HỢP ĐỒNG"));
        pnlSidebar.add(btnContract);

        // --- 7. Module HOADON & THANHTOAN ---
        if (!userRole.equals("NHAN_VIEN")) {
            JButton btnFinance = createMenuButton("Hóa Đơn & Thanh Toán");
            btnFinance.addActionListener(e -> switchPage("REPORTS", "QUẢN LÝ TÀI CHÍNH"));
            pnlSidebar.add(btnFinance);
        }

        // --- 8. Module KHUYENMAI ---
        JButton btnPromo = createMenuButton("Chương Trình Ưu Đãi");
        btnPromo.addActionListener(e -> switchPage("PROMO", "CHƯƠNG TRÌNH KHUYẾN MÃI"));
        pnlSidebar.add(btnPromo);

        // --- 9. Đăng xuất (Có hỏi xác nhận) ---
        JButton btnLogout = createMenuButton("Đăng Xuất");
        pnlSidebar.add(btnLogout);
        btnLogout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginFrame().setVisible(true);
            }
        });

        add(pnlSidebar, BorderLayout.WEST);
    }

    private void initHeader() {
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setPreferredSize(new Dimension(1070, 60));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        lblHeaderTitle = new JLabel("BẢNG ĐIỀU KHIỂN - " + userRole);
        lblHeaderTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeaderTitle.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnlHeader.add(lblHeaderTitle, BorderLayout.WEST);

        add(pnlHeader, BorderLayout.NORTH);
    }

    private void initContent() {
        cardLayout = new CardLayout();
        pnlContent = new JPanel(cardLayout);
        pnlContent.setBackground(new Color(245, 245, 245));

        // Kết nối các class Panel thật mà bạn đã tạo
        pnlContent.add(new HomePanel(), "HOME");
        pnlContent.add(new CarPanel(), "CARS");
        pnlContent.add(new CustomerPanel(), "CUSTOMERS");
        pnlContent.add(new EmployeePanel(), "EMPLOYEES");
        pnlContent.add(new ContractPanel(), "CONTRACTS");
        pnlContent.add(new FinancePanel(), "REPORTS");
        pnlContent.add(new PromoPanel(), "PROMO");

        add(pnlContent, BorderLayout.CENTER);
    }

    // HÀM QUAN TRỌNG NHẤT ĐỂ CHUYỂN TAB
    private void switchPage(String cardName, String title) {
        cardLayout.show(pnlContent, cardName);
        lblHeaderTitle.setText(title);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(260, 45));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(24, 28, 43));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(0, 20, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}