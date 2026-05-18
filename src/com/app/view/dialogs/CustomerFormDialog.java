package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerFormDialog extends JDialog {
    private JTextField txtHoTen, txtCCCD, txtBangLai, txtSDT, txtDiaChi, txtEmail;
    private JComboBox<String> cbLoaiKH;
    private JButton btnSave, btnCancel;
    private boolean saved = false;

    public CustomerFormDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBorder(new EmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 10);

        String[] labels = {"Họ và tên: *", "CCCD: *", "Bằng lái xe: *", "Số điện thoại: *", "Email:", "Địa chỉ:", "Loại khách hàng:"};
        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtHoTen = makeField(); pnlForm.add(txtHoTen, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCCCD = makeField(); pnlForm.add(txtCCCD, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtBangLai = makeField(); pnlForm.add(txtBangLai, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtSDT = makeField(); pnlForm.add(txtSDT, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtEmail = makeField(); pnlForm.add(txtEmail, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtDiaChi = makeField(); pnlForm.add(txtDiaChi, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel(labels[row]), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        cbLoaiKH = new JComboBox<>(new String[]{"Cá nhân", "Doanh nghiệp", "VIP"});
        pnlForm.add(cbLoaiKH, gbc);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlBtn.setBorder(new EmptyBorder(0, 20, 10, 20));
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
        btnSave.setBackground(new Color(30, 144, 255));
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(90, 34));
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
        if (txtHoTen.getText().trim().isEmpty()) { alert("Vui lòng nhập họ tên!"); return false; }
        if (txtCCCD.getText().trim().length() != 12) { alert("CCCD phải đủ 12 số!"); return false; }
        if (txtBangLai.getText().trim().isEmpty()) { alert("Vui lòng nhập số bằng lái!"); return false; }
        if (txtSDT.getText().trim().isEmpty()) { alert("Vui lòng nhập số điện thoại!"); return false; }
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
        f.setPreferredSize(new Dimension(260, 30));
        return f;
    }

    public boolean isSaved() { return saved; }
    public String getHoTen()   { return txtHoTen.getText().trim(); }
    public String getCCCD()    { return txtCCCD.getText().trim(); }
    public String getBangLai() { return txtBangLai.getText().trim(); }
    public String getSDT()     { return txtSDT.getText().trim(); }
    public String getEmail()   { return txtEmail.getText().trim(); }
    public String getDiaChi()  { return txtDiaChi.getText().trim(); }
    public String getLoaiKH()  { return (String) cbLoaiKH.getSelectedItem(); }

    public void setData(String hoTen, String cccd, String bangLai, String sdt,
                        String email, String diaChi, String loaiKH) {
        txtHoTen.setText(hoTen);
        txtCCCD.setText(cccd);
        txtBangLai.setText(bangLai);
        txtSDT.setText(sdt);
        txtEmail.setText(email);
        txtDiaChi.setText(diaChi);
        cbLoaiKH.setSelectedItem(loaiKH);
    }
}
