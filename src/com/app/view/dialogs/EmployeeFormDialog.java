package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmployeeFormDialog extends JDialog {
    private JTextField txtHoTen, txtCCCD, txtSDT, txtEmail, txtDoanhNghiep;
    private JComboBox<String> cbChucVu;
    private JButton btnSave, btnCancel;
    private boolean saved = false;

    public EmployeeFormDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(500, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBorder(new EmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 10);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Họ và tên: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtHoTen = makeField(); pnlForm.add(txtHoTen, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("CCCD: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCCCD = makeField(); pnlForm.add(txtCCCD, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Số điện thoại: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtSDT = makeField(); pnlForm.add(txtSDT, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtEmail = makeField(); pnlForm.add(txtEmail, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Doanh nghiệp: *"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtDoanhNghiep = makeField(); pnlForm.add(txtDoanhNghiep, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        pnlForm.add(makeLabel("Chức vụ:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        cbChucVu = new JComboBox<>(new String[]{"Nhân viên", "Trưởng phòng", "Quản lý"});
        pnlForm.add(cbChucVu, gbc);

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
        if (txtSDT.getText().trim().isEmpty()) { alert("Vui lòng nhập số điện thoại!"); return false; }
        if (txtDoanhNghiep.getText().trim().isEmpty()) { alert("Vui lòng nhập tên doanh nghiệp!"); return false; }
        return true;
    }

    private void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        l.setPreferredSize(new Dimension(150, 28));
        return l;
    }

    private JTextField makeField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setPreferredSize(new Dimension(260, 30));
        return f;
    }

    public boolean isSaved()         { return saved; }
    public String getHoTen()         { return txtHoTen.getText().trim(); }
    public String getCCCD()          { return txtCCCD.getText().trim(); }
    public String getSDT()           { return txtSDT.getText().trim(); }
    public String getEmail()         { return txtEmail.getText().trim(); }
    public String getDoanhNghiep()   { return txtDoanhNghiep.getText().trim(); }
    public String getChucVu()        { return (String) cbChucVu.getSelectedItem(); }

    public void setData(String hoTen, String cccd, String sdt,
                        String email, String doanhNghiep, String chucVu) {
        txtHoTen.setText(hoTen);
        txtCCCD.setText(cccd);
        txtSDT.setText(sdt);
        txtEmail.setText(email);
        txtDoanhNghiep.setText(doanhNghiep);
        cbChucVu.setSelectedItem(chucVu);
    }
}
