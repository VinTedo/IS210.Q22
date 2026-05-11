package main; // Đổi từ com.app.main thành main

import view.LoginFrame; // Đổi từ com.app.view.LoginFrame thành view.LoginFrame
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainApp {
    public static void main(String[] args) {
        // Chỉnh giao diện cho đẹp theo hệ điều hành (Windows/Mac)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Khởi động ứng dụng bằng cách mở Form Đăng Nhập
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo cửa sổ Login và cho nó hiển thị
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }
        });
    }
}