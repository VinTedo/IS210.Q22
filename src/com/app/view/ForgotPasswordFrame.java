package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ForgotPasswordFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtNewPass, txtConfirmPass;
    private JButton btnUpdate, btnBack;

    public ForgotPasswordFrame() {
        setTitle("Carvip - Khôi phục mật khẩu");
        setSize(1000, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 1. TẠO PANEL CHỨA HÌNH NỀN PHỦ KÍN
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Sử dụng file ảnh bạn đã resize (DangNhap.png)
                    // Đảm bảo đường dẫn "Images/..." khớp với thư mục của bạn
                    ImageIcon icon = new ImageIcon("Images/DangNhap.png");
                    Image img = icon.getImage();
                    // Vẽ ảnh phủ kín toàn bộ diện tích cửa sổ
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.out.println("Không tìm thấy ảnh nền!");
                }
            }
        };
        
        // Thiết lập layout để form nằm chính giữa hình nền
        backgroundPanel.setLayout(new GridBagLayout());
        this.setContentPane(backgroundPanel);

        // 2. TẠO KHUNG FORM "NỔI" TRÊN NỀN ẢNH
        // Sử dụng màu trắng có độ trong suốt (Alpha = 200) để thấy ảnh mờ phía sau
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 200)); 
        formPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("QUÊN MẬT KHẨU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(new Color(255, 69, 0));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(lblTitle, gbc);

        // --- Các ô nhập liệu ---
        gbc.insets = new Insets(5, 0, 5, 0);
        
        gbc.gridy = 1;
        formPanel.add(createLeftLabel("Nhập Email tài khoản:"), gbc);
        txtEmail = createStyledField();
        gbc.gridy = 2;
        formPanel.add(txtEmail, gbc);

        gbc.gridy = 3;
        formPanel.add(createLeftLabel("Mật khẩu mới:"), gbc);
        txtNewPass = createStyledPasswordField();
        gbc.gridy = 4;
        formPanel.add(txtNewPass, gbc);

        gbc.gridy = 5;
        formPanel.add(createLeftLabel("Xác nhận mật khẩu mới:"), gbc);
        txtConfirmPass = createStyledPasswordField();
        gbc.gridy = 6;
        formPanel.add(txtConfirmPass, gbc);

        // --- Nút bấm ---
        btnUpdate = new JButton("CẬP NHẬT MẬT KHẨU");
        btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setBorder(new LineBorder(new Color(255, 191, 0), 2));
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 7;
        gbc.ipady = 15;
        gbc.insets = new Insets(30, 0, 10, 0);
        formPanel.add(btnUpdate, gbc);

        btnBack = new JButton("Quay lại Đăng nhập");
        btnBack.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setForeground(Color.DARK_GRAY);
        gbc.gridy = 8;
        gbc.ipady = 0;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(btnBack, gbc);

        // Thêm form vào trung tâm của nền ảnh
        backgroundPanel.add(formPanel);

        // Sự kiện quay lại
        btnBack.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
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
        tf.setPreferredSize(new Dimension(350, 40));
        return tf;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField pf = new JPasswordField();
        pf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pf.setPreferredSize(new Dimension(350, 40));
        return pf;
    }
}