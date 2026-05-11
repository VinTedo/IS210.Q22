package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PromoPanel extends JPanel {
    public PromoPanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Mã KM", "Tên Chương Trình", "% Giảm", "Ngày Bắt Đầu", "Ngày Kết Thúc"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tbl = new JTable(model);

        add(new JScrollPane(tbl), BorderLayout.CENTER);
        add(new JButton("Tạo Mã Khuyến Mãi Mới"), BorderLayout.SOUTH);
    }
}