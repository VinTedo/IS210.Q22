package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CarReturnDialog extends JDialog {
    private JTextField txtMaHD, txtBienSo, txtNgayTra, txtKmThemm;
    private JTextArea txtTinhTrang;
    private JTextField txtPhuPhi;
    private JComboBox<String> cbLyDoPhuPhi;
    private JLabel lblTongCong;
    private JButton btnXacNhan, btnCancel;
    private boolean confirmed = false;

    public CarReturnDialog(JFrame parent, String maHD, String bienSo) {
        super(parent, "Xác Nhận Trả Xe - HĐ: " + maHD, true);
        setSize(520, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(15, 20, 10, 20));

        // Thông tin hợp đồng
        JPanel pnlHD = new JPanel(new GridLayout(2, 4, 10, 8));
        pnlHD.setBorder(new TitledBorder("Thông tin hợp đồng"));
        pnlHD.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        txtMaHD   = makeField(); txtMaHD.setText(maHD); txtMaHD.setEditable(false);
        txtBienSo = makeField(); txtBienSo.setText(bienSo); txtBienSo.setEditable(false);
        pnlHD.add(makeLabel("Mã hợp đồng:")); pnlHD.add(txtMaHD);
        pnlHD.add(makeLabel("Biển số xe:")); pnlHD.add(txtBienSo);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtNgayTra = makeField(); txtNgayTra.setText(LocalDate.now().format(fmt));
        pnlHD.add(makeLabel("Ngày trả thực tế:")); pnlHD.add(txtNgayTra);
        pnlHD.add(new JLabel()); pnlHD.add(new JLabel());
        pnlMain.add(pnlHD);
        pnlMain.add(Box.createVerticalStrut(10));

        // Tình trạng xe khi trả
        JPanel pnlTinhTrang = new JPanel(new BorderLayout(0, 5));
        pnlTinhTrang.setBorder(new TitledBorder("Tình trạng xe khi trả"));
        pnlTinhTrang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        txtTinhTrang = new JTextArea(3, 30);
        txtTinhTrang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTinhTrang.setLineWrap(true);
        txtTinhTrang.setText("Xe trả đúng tình trạng ban đầu.");
        pnlTinhTrang.add(new JScrollPane(txtTinhTrang), BorderLayout.CENTER);
        pnlMain.add(pnlTinhTrang);
        pnlMain.add(Box.createVerticalStrut(10));

        // Phụ phí
        JPanel pnlPhu = new JPanel(new GridLayout(3, 4, 10, 8));
        pnlPhu.setBorder(new TitledBorder("Phụ phí phát sinh"));
        pnlPhu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        txtKmThemm = makeField(); txtKmThemm.setText("0");
        txtPhuPhi  = makeField(); txtPhuPhi.setText("0");
        cbLyDoPhuPhi = new JComboBox<>(new String[]{"Không có", "Trả trễ", "Hư hỏng xe", "Vượt km", "Khác"});
        pnlPhu.add(makeLabel("Km vượt định mức:")); pnlPhu.add(txtKmThemm);
        pnlPhu.add(makeLabel("Lý do phụ phí:")); pnlPhu.add(cbLyDoPhuPhi);
        pnlPhu.add(makeLabel("Số tiền phụ phí:")); pnlPhu.add(txtPhuPhi);
        JButton btnTinh = new JButton("Tính tổng");
        btnTinh.addActionListener(e -> tinhTong());
        pnlPhu.add(new JLabel()); pnlPhu.add(btnTinh);
        pnlPhu.add(new JLabel()); pnlPhu.add(new JLabel());
        pnlMain.add(pnlPhu);
        pnlMain.add(Box.createVerticalStrut(10));

        // Tổng cộng
        JPanel pnlTong = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        pnlTong.setBorder(new TitledBorder("Tổng phụ phí phải thu"));
        pnlTong.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        lblTongCong = new JLabel("0 VNĐ");
        lblTongCong.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongCong.setForeground(new Color(231, 76, 60));
        pnlTong.add(lblTongCong);
        pnlMain.add(pnlTong);

        add(new JScrollPane(pnlMain), BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnXacNhan = new JButton("Xác nhận trả xe");
        btnCancel  = new JButton("Hủy");
        btnXacNhan.setBackground(new Color(46, 204, 113));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setPreferredSize(new Dimension(150, 34));
        btnCancel.setPreferredSize(new Dimension(90, 34));
        pnlBtn.add(btnCancel);
        pnlBtn.add(btnXacNhan);
        add(pnlBtn, BorderLayout.SOUTH);

        btnXacNhan.addActionListener(e -> {
            int opt = JOptionPane.showConfirmDialog(this,
                "Xác nhận trả xe biển số " + bienSo + "?\nHành động này không thể hoàn tác.",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                confirmed = true;
                dispose();
            }
        });
        btnCancel.addActionListener(e -> dispose());
    }

    private void tinhTong() {
        try {
            double phuPhi = Double.parseDouble(txtPhuPhi.getText().trim().replaceAll("[^0-9.]", ""));
            lblTongCong.setText(String.format("%,.0f VNĐ", phuPhi));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Số tiền phụ phí không hợp lệ!");
        }
    }

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    private JTextField makeField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return f;
    }

    public boolean isConfirmed()  { return confirmed; }
    public String getNgayTra()    { return txtNgayTra.getText().trim(); }
    public String getTinhTrang()  { return txtTinhTrang.getText().trim(); }
    public String getPhuPhi()     { return txtPhuPhi.getText().trim(); }
    public String getLyDo()       { return (String) cbLyDoPhuPhi.getSelectedItem(); }
}
