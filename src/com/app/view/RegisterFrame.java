package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegister, btnBackToLogin;

    public RegisterFrame() {
        setTitle("Carvip - Đăng ký tài khoản VIP");
        // Kích thước rộng rãi 1000x750 để chứa đủ các ô nhập liệu mà không bị chật
        setSize(1000, 750); 
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

        // 2. TẠO KHUNG FORM ĐĂNG KÝ (Nổi trên nền ảnh)
        // Màu trắng với độ trong suốt 210 giúp giao diện sang trọng
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 210)); 
        formPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);

        // --- Tiêu đề (Màu xanh lá phong thủy cho Đăng ký) ---
        JLabel lblTitle = new JLabel("TẠO TÀI KHOẢN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(new Color(34, 139, 34)); 
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        formPanel.add(lblTitle, gbc);

        // --- Các trường nhập liệu ---
        gbc.insets = new Insets(5, 0, 5, 0);

        gbc.gridy = 1;
        formPanel.add(createLeftLabel("Họ và tên:"), gbc);
        txtName = createStyledField();
        gbc.gridy = 2;
        formPanel.add(txtName, gbc);

        gbc.gridy = 3;
        formPanel.add(createLeftLabel("Email:"), gbc);
        txtEmail = createStyledField();
        gbc.gridy = 4;
        formPanel.add(txtEmail, gbc);

        gbc.gridy = 5;
        formPanel.add(createLeftLabel("Mật khẩu:"), gbc);
        txtPassword = createStyledPasswordField();
        gbc.gridy = 6;
        formPanel.add(txtPassword, gbc);

        gbc.gridy = 7;
        formPanel.add(createLeftLabel("Xác nhận mật khẩu:"), gbc);
        txtConfirmPassword = createStyledPasswordField();
        gbc.gridy = 8;
        formPanel.add(txtConfirmPassword, gbc);

        // --- Nút Đăng ký (Chữ Đen trên nền xanh lá sáng) ---
        btnRegister = new JButton("ĐĂNG KÝ NGAY");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnRegister.setBackground(new Color(50, 205, 50)); 
        btnRegister.setForeground(Color.BLACK); 
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(new LineBorder(new Color(34, 139, 34), 1));
        
        gbc.gridy = 9;
        gbc.ipady = 15; 
        gbc.insets = new Insets(25, 0, 10, 0);
        formPanel.add(btnRegister, gbc);

        // --- Quay lại đăng nhập ---
        btnBackToLogin = new JButton("Đã có tài khoản? Đăng nhập");
        btnBackToLogin.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        btnBackToLogin.setForeground(new Color(0, 102, 204));
        btnBackToLogin.setContentAreaFilled(false);
        btnBackToLogin.setBorderPainted(false);
        btnBackToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        gbc.gridy = 10;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        formPanel.add(btnBackToLogin, gbc);

        // Đưa formPanel vào chính giữa background
        backgroundPanel.add(formPanel);

        // --- SỰ KIỆN ---
        btnBackToLogin.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });

        btnRegister.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đang xử lý đăng ký...");
        });
    }

    private JLabel createLeftLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private JTextField createStyledField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tf.setPreferredSize(new Dimension(380, 38));
        tf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        return tf;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField pf = new JPasswordField();
        pf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pf.setPreferredSize(new Dimension(380, 38));
        pf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        return pf;
    }
}