package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerPanel extends JPanel {
    public CustomerPanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Mã KH", "Họ Tên", "CCCD", "SĐT", "Địa Chỉ", "Loại KH"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tbl = new JTable(model);
        
        add(new JLabel(" DANH SÁCH KHÁCH HÀNG", SwingConstants.LEFT), BorderLayout.NORTH);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }
}