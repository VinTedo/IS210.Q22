package view.dialogs;

import javax.swing.*;
import java.awt.*;

public class CarDetailDialog extends JDialog {
    public CarDetailDialog(JFrame parent) {
        super(parent, "Thông Tin Chi Tiết Xe", true);
        setSize(500, 600);
        setLayout(new GridLayout(10, 2, 10, 10));
        setLocationRelativeTo(parent);

        add(new JLabel(" Biển số xe:")); add(new JTextField());
        add(new JLabel(" Năm sản xuất:")); add(new JTextField());
        add(new JLabel(" Số máy:")); add(new JTextField());
        add(new JLabel(" Số khung:")); add(new JTextField());
        add(new JLabel(" Hạn bảo hiểm:")); add(new JTextField());
        
        JButton btnSave = new JButton("Lưu Thông Tin");
        add(btnSave);
        add(new JButton("Hủy"));
    }
}