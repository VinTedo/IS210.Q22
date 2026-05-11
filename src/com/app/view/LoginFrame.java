package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister, btnForgotPassword;

    public LoginFrame() {
        setTitle("Carvip - Đăng Nhập Hệ Thống");
        setSize(1000, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 1. TẠO PANEL CHỨA HÌNH NỀN PHỦ KÍN
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Đảm bảo đường dẫn khớp với thư mục Images của bạn
                    ImageIcon icon = new ImageIcon("Images/DangNhap.png");
                    Image img = icon.getImage();
                    // Vẽ ảnh phủ kín toàn bộ diện tích Panel
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.out.println("Không tìm thấy ảnh nền!");
                }
            }
        };
        
        backgroundPanel.setLayout(new GridBagLayout());
        this.setContentPane(backgroundPanel);

        // 2. TẠO KHUNG FORM ĐĂNG NHẬP (Nổi trên nền ảnh)
        // Alpha = 210 giúp khung hơi trong suốt nhìn rất hiện đại
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 210)); 
        formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblTitle.setForeground(new Color(30, 144, 255)); // Màu xanh dương chủ đạo
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(lblTitle, gbc);

        // --- Ô nhập Email ---
        gbc.insets = new Insets(10, 0, 5, 0);
        gbc.gridy = 1;
        formPanel.add(createLeftLabel("Email:"), gbc);
        
        txtEmail = createStyledField();
        gbc.gridy = 2;
        formPanel.add(txtEmail, gbc);

        // --- Ô nhập Mật khẩu ---
        gbc.gridy = 3;
        formPanel.add(createLeftLabel("Mật khẩu:"), gbc);
        
        txtPassword = createStyledPasswordField();
        gbc.gridy = 4;
        formPanel.add(txtPassword, gbc);

        // --- Nút Đăng Nhập ---
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.DARK_GRAY);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        
        gbc.gridy = 5;
        gbc.ipady = 15; 
        gbc.insets = new Insets(30, 0, 15, 0);
        formPanel.add(btnLogin, gbc);

        // --- Cụm nút phụ (Đăng ký & Quên MK) ---
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        subPanel.setOpaque(false); // Để lộ nền của formPanel

        btnRegister = new JButton("Đăng ký");
        styleSecondaryButton(btnRegister, new Color(0, 102, 204));
        
        btnForgotPassword = new JButton("Quên mật khẩu?");
        styleSecondaryButton(btnForgotPassword, Color.RED);

        subPanel.add(btnRegister);
        subPanel.add(btnForgotPassword);
        
        gbc.gridy = 6;
        gbc.ipady = 0;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(subPanel, gbc);

        // Đưa formPanel vào chính giữa background
        backgroundPanel.add(formPanel);

        // --- XỬ LÝ SỰ KIỆN ---
        btnRegister.addActionListener(e -> {
            this.dispose();
            new RegisterFrame().setVisible(true);
        });

        btnForgotPassword.addActionListener(e -> {
            this.dispose();
            new ForgotPasswordFrame().setVisible(true);
        });
        
        // --- XỬ LÝ SỰ KIỆN ĐĂNG NHẬP ---
        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String pass = new String(txtPassword.getPassword());

            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Giả lập các tài khoản theo vai trò (Sau này sẽ kết nối Database Oracle)
            // Cấu trúc: TenDangNhap | MatKhau -> VaiTro
            String role = "";
            if (email.equals("admin") && pass.equals("123")) role = "ADMIN";
            else if (email.equals("doanhnghiep") && pass.equals("123")) role = "DOANH_NGHIEP";
            else if (email.equals("nhanvien") && pass.equals("123")) role = "NHAN_VIEN";
            else if (email.equals("chuxe") && pass.equals("123")) role = "CHU_XE";
            else if (email.equals("khachhang") && pass.equals("123")) role = "KHACH_HANG";

            if (!role.equals("")) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò: " + role);
                this.dispose();
                new MainDashboard(role).setVisible(true); // Mở Dashboard với vai trò tương ứng
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });;
    }

    private JLabel createLeftLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return label;
    }

    private JTextField createStyledField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tf.setPreferredSize(new Dimension(350, 40));
        tf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        return tf;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField pf = new JPasswordField();
        pf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pf.setPreferredSize(new Dimension(350, 40));
        pf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        return pf;
    }

    private void styleSecondaryButton(JButton btn, Color color) {
        btn.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        btn.setForeground(color);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}