package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PromoFormDialog extends JDialog {
    private JTextField txtTenCT, txtPhanTramGiam, txtNgayBD, txtNgayKT, txtMoTa;
    private JComboBox<String> cbTrangThai;
    private JButton btnSave, btnCancel;
    private boolean saved = false;

    public PromoFormDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(480, 380);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBorder(new EmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 0, 7, 10);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Tên chương trình: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtTenCT = makeField(); pnlForm.add(txtTenCT, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("% Giảm giá: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtPhanTramGiam = makeField(); txtPhanTramGiam.setText("10");
        pnlForm.add(txtPhanTramGiam, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Ngày bắt đầu: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNgayBD = makeField(); txtNgayBD.setText(LocalDate.now().format(fmt));
        pnlForm.add(txtNgayBD, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Ngày kết thúc: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNgayKT = makeField(); txtNgayKT.setText(LocalDate.now().plusMonths(1).format(fmt));
        pnlForm.add(txtNgayKT, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Mô tả:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtMoTa = makeField(); pnlForm.add(txtMoTa, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Trạng thái:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        cbTrangThai = new JComboBox<>(new String[]{"Đang diễn ra", "Sắp diễn ra", "Đã kết thúc"});
        pnlForm.add(cbTrangThai, gbc);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlBtn.setBorder(new EmptyBorder(0, 20, 10, 20));
        btnSave   = new JButton("Tạo khuyến mãi");
        btnCancel = new JButton("Hủy");
        btnSave.setBackground(new Color(241, 196, 15));
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(140, 34));
        btnCancel.setPreferredSize(new Dimension(90, 34));
        pnlBtn.add(btnCancel);
        pnlBtn.add(btnSave);
        add(pnlBtn, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            if (!validate_()) return;
            saved = true;
            dispose();
        });
        btnCancel.addActionListener(e -> dispose());
    }

    private boolean validate_() {
        if (txtTenCT.getText().trim().isEmpty()) { alert("Vui lòng nhập tên chương trình!"); return false; }
        try {
            double pct = Double.parseDouble(txtPhanTramGiam.getText().trim());
            if (pct <= 0 || pct > 100) { alert("% giảm phải từ 1 đến 100!"); return false; }
        } catch (NumberFormatException ex) { alert("% giảm phải là số!"); return false; }
        if (txtNgayBD.getText().trim().isEmpty()) { alert("Vui lòng nhập ngày bắt đầu!"); return false; }
        if (txtNgayKT.getText().trim().isEmpty()) { alert("Vui lòng nhập ngày kết thúc!"); return false; }
        return true;
    }

    private void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        l.setPreferredSize(new Dimension(160, 28));
        return l;
    }

    private JTextField makeField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setPreferredSize(new Dimension(240, 30));
        return f;
    }

    public boolean isSaved()         { return saved; }
    public String getTenCT()         { return txtTenCT.getText().trim(); }
    public String getPhanTramGiam()  { return txtPhanTramGiam.getText().trim(); }
    public String getNgayBD()        { return txtNgayBD.getText().trim(); }
    public String getNgayKT()        { return txtNgayKT.getText().trim(); }
    public String getMoTa()          { return txtMoTa.getText().trim(); }
    public String getTrangThai()     { return (String) cbTrangThai.getSelectedItem(); }
}
