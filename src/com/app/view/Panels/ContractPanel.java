package view.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContractPanel extends JPanel {
    public ContractPanel() {
        setLayout(new BorderLayout());
        String[] columns = {"Mã HĐ", "Khách Hàng", "Ngày Nhận", "Địa Điểm", "Trạng Thái", "Tổng Tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tbl = new JTable(model);

        JPanel pnlActions = new JPanel();
        pnlActions.add(new JButton("Lập Hợp Đồng Mới"));
        pnlActions.add(new JButton("Ký Biên Bản Bàn Giao"));

        add(pnlActions, BorderLayout.NORTH);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }
}