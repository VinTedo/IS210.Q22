package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeePanel extends JPanel {
    public EmployeePanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Mã NV", "Họ Tên NV", "CCCD", "SĐT", "Email", "Doanh Nghiệp"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tbl = new JTable(model);

        add(new JButton("Thêm Nhân Viên Mới"), BorderLayout.SOUTH);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }
}