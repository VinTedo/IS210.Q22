package view.Panels;

import view.dialogs.EnterpriseFormDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class EnterprisePanel extends JPanel {
    private JTable tblEnterprise;
    private DefaultTableModel model;
    private JTextField txtSearch;

    public EnterprisePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Toolbar
        JPanel pnlToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        pnlToolBar.setBackground(Color.WHITE);
        pnlToolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        pnlToolBar.add(new JLabel("Tìm kiếm:"));
        txtSearch = new JTextField(20);
        pnlToolBar.add(txtSearch);

        JButton btnSearch = new JButton("Tìm");
        JButton btnAdd    = new JButton("Thêm Doanh Nghiệp");
        JButton btnEdit   = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm mới");

        btnAdd.setBackground(new Color(30, 144, 255));
        btnAdd.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(231, 76, 60));
        btnDelete.setForeground(Color.WHITE);

        pnlToolBar.add(btnSearch);
        pnlToolBar.add(Box.createHorizontalStrut(10));
        pnlToolBar.add(btnAdd);
        pnlToolBar.add(btnEdit);
        pnlToolBar.add(btnDelete);
        pnlToolBar.add(btnRefresh);

        // Bảng dữ liệu
        String[] columns = {"Mã DN", "Tên Doanh Nghiệp", "Mã Số Thuế", "Người Đại Diện", "SĐT", "Email", "Địa Chỉ"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblEnterprise = new JTable(model);
        tblEnterprise.setRowHeight(30);
        tblEnterprise.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblEnterprise.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblEnterprise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEnterprise.setGridColor(new Color(230, 230, 230));
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblEnterprise.setRowSorter(sorter);

        // Dữ liệu mẫu
        model.addRow(new Object[]{"DN001", "Công ty TNHH Carvip", "0312345678", "Nguyễn Văn A", "0901234567", "carvip@mail.com", "123 Lê Lợi, Q1, TP.HCM"});
        model.addRow(new Object[]{"DN002", "Công ty CP Thuê Xe Nhanh", "0312345679", "Trần Thị B", "0912345678", "xe24h@mail.com", "456 Nguyễn Huệ, Q1, TP.HCM"});

        add(pnlToolBar, BorderLayout.NORTH);
        add(new JScrollPane(tblEnterprise), BorderLayout.CENTER);

        // Số lượng
        JLabel lblCount = new JLabel("  Tổng số: " + model.getRowCount() + " doanh nghiệp");
        lblCount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblCount.setForeground(Color.GRAY);
        add(lblCount, BorderLayout.SOUTH);

        // Events
        btnSearch.addActionListener(e -> {
            String kw = txtSearch.getText().trim();
            sorter.setRowFilter(kw.isEmpty() ? null : RowFilter.regexFilter("(?i)" + kw));
        });
        txtSearch.addActionListener(e -> btnSearch.doClick());

        btnAdd.addActionListener(e -> {
            EnterpriseFormDialog dlg = new EnterpriseFormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Thêm Doanh Nghiệp Mới");
            dlg.setVisible(true);
            if (dlg.isSaved()) {
                model.addRow(new Object[]{
                    "DN" + String.format("%03d", model.getRowCount() + 1),
                    dlg.getTenDN(), dlg.getMaSoThue(), dlg.getNguoiDaiDien(),
                    dlg.getSDT(), dlg.getEmail(), dlg.getDiaChi()
                });
                lblCount.setText("  Tổng số: " + model.getRowCount() + " doanh nghiệp");
                JOptionPane.showMessageDialog(this, "Thêm doanh nghiệp thành công!\n(Kết nối DB để lưu vào bảng DOANHNGHIEPQUANLI)", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnEdit.addActionListener(e -> {
            int row = tblEnterprise.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Vui lòng chọn doanh nghiệp cần sửa!"); return; }
            int modelRow = tblEnterprise.convertRowIndexToModel(row);
            EnterpriseFormDialog dlg = new EnterpriseFormDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Sửa Thông Tin Doanh Nghiệp");
            dlg.setData(
                (String) model.getValueAt(modelRow, 1), (String) model.getValueAt(modelRow, 2),
                (String) model.getValueAt(modelRow, 3), (String) model.getValueAt(modelRow, 4),
                (String) model.getValueAt(modelRow, 5), (String) model.getValueAt(modelRow, 6)
            );
            dlg.setVisible(true);
            if (dlg.isSaved()) {
                model.setValueAt(dlg.getTenDN(), modelRow, 1);
                model.setValueAt(dlg.getMaSoThue(), modelRow, 2);
                model.setValueAt(dlg.getNguoiDaiDien(), modelRow, 3);
                model.setValueAt(dlg.getSDT(), modelRow, 4);
                model.setValueAt(dlg.getEmail(), modelRow, 5);
                model.setValueAt(dlg.getDiaChi(), modelRow, 6);
            }
        });

        btnDelete.addActionListener(e -> {
            int row = tblEnterprise.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Vui lòng chọn doanh nghiệp cần xóa!"); return; }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa doanh nghiệp này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(tblEnterprise.convertRowIndexToModel(row));
                lblCount.setText("  Tổng số: " + model.getRowCount() + " doanh nghiệp");
            }
        });

        btnRefresh.addActionListener(e -> {
            txtSearch.setText("");
            sorter.setRowFilter(null);
            JOptionPane.showMessageDialog(this, "Kết nối DB để tải lại dữ liệu từ bảng DOANHNGHIEPQUANLI");
        });
    }
}
