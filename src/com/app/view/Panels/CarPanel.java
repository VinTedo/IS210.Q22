package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarPanel extends JPanel {
    private JTable tblCars;
    private DefaultTableModel model;

    public CarPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Thanh công cụ tìm kiếm
        JPanel pnlToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlToolBar.add(new JLabel("Tìm kiếm xe:"));
        pnlToolBar.add(new JTextField(20));
        pnlToolBar.add(new JButton("Tìm Kiếm"));
        pnlToolBar.add(new JButton("Thêm Xe Mới"));

        // Bảng dữ liệu dựa trên Schema XE
        String[] columns = {"Biển Số", "Thương Hiệu", "Tên Xe", "Số Chỗ", "Trạng Thái", "Đơn Giá"};
        model = new DefaultTableModel(columns, 0);
        tblCars = new JTable(model);
        
        add(pnlToolBar, BorderLayout.NORTH);
        add(new JScrollPane(tblCars), BorderLayout.CENTER);
    }
}