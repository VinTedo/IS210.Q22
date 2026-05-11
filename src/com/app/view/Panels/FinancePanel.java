package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FinancePanel extends JPanel {
    public FinancePanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Mã HD", "Ngày Lập", "Tiền Gốc", "Phí Phát Sinh", "Tổng Tiền", "Trạng Thái"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tbl = new JTable(model);

        add(new JLabel(" QUẢN LÝ DOANH THU & HÓA ĐƠN", SwingConstants.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }
}