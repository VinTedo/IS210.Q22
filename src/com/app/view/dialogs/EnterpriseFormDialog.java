package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EnterpriseFormDialog extends JDialog {
    private JTextField txtTenDN, txtMaSoThue, txtDiaChi, txtSDT, txtEmail, txtNguoiDaiDien;
    private JButton btnSave, btnCancel;
    private boolean saved = false;

    public EnterpriseFormDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBorder(new EmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 0, 7, 10);
        int row = 0;

        String[][] fields = {
            {"Tên doanh nghiệp: *", ""}, {"Mã số thuế: *", ""}, {"Người đại diện:", ""},
            {"Số điện thoại: *", ""}, {"Email:", ""}, {"Địa chỉ:", ""}
        };

        JTextField[] inputs = {
            txtTenDN = makeField(), txtMaSoThue = makeField(), txtNguoiDaiDien = makeField(),
            txtSDT = makeField(), txtEmail = makeField(), txtDiaChi = makeField()
        };

        for (int i = 0; i < fields.length; i++) {
            gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
            pnlForm.add(makeLabel(fields[i][0]), gbc);
            gbc.gridx = 1; gbc.weightx = 1;
            pnlForm.add(inputs[i], gbc);
            row++;
        }

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlBtn.setBorder(new EmptyBorder(0, 20, 10, 20));
        btnSave   = new JButton("Lưu");
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
        if (txtTenDN.getText().trim().isEmpty())    { alert("Vui lòng nhập tên doanh nghiệp!"); return false; }
        if (txtMaSoThue.getText().trim().isEmpty()) { alert("Vui lòng nhập mã số thuế!"); return false; }
        if (txtSDT.getText().trim().isEmpty())      { alert("Vui lòng nhập số điện thoại!"); return false; }
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

    public boolean isSaved()          { return saved; }
    public String getTenDN()          { return txtTenDN.getText().trim(); }
    public String getMaSoThue()       { return txtMaSoThue.getText().trim(); }
    public String getNguoiDaiDien()   { return txtNguoiDaiDien.getText().trim(); }
    public String getSDT()            { return txtSDT.getText().trim(); }
    public String getEmail()          { return txtEmail.getText().trim(); }
    public String getDiaChi()         { return txtDiaChi.getText().trim(); }

    public void setData(String ten, String mst, String ndd, String sdt, String email, String diaChi) {
        txtTenDN.setText(ten); txtMaSoThue.setText(mst); txtNguoiDaiDien.setText(ndd);
        txtSDT.setText(sdt); txtEmail.setText(email); txtDiaChi.setText(diaChi);
    }
}
