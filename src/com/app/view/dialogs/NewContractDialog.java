package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NewContractDialog extends JDialog {
    private JTextField txtMaKH, txtTenKH, txtBienSo, txtTenXe, txtDonGia;
    private JTextField txtNgayNhan, txtNgayTra, txtDiaDiem, txtMaKM;
    private JLabel lblTongTien;
    private JButton btnSave, btnCancel;
    private boolean saved = false;

    public NewContractDialog(JFrame parent) {
        super(parent, "Lập Hợp Đồng Thuê Xe Mới", true);
        setSize(600, 560);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(0, 0));

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(15, 20, 10, 20));

        // ---- Thông tin khách hàng ----
        JPanel pnlKH = new JPanel(new GridLayout(2, 4, 10, 8));
        pnlKH.setBorder(new TitledBorder("Thông tin khách hàng"));
        pnlKH.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        pnlKH.add(makeLabel("Mã khách hàng:")); txtMaKH = makeField(); pnlKH.add(txtMaKH);
        pnlKH.add(makeLabel("Họ tên:")); txtTenKH = makeField(); pnlKH.add(txtTenKH);
        JButton btnTimKH = new JButton("Tìm KH");
        btnTimKH.addActionListener(e -> JOptionPane.showMessageDialog(this, "Kết nối DB để tìm khách hàng"));
        pnlKH.add(new JLabel()); pnlKH.add(btnTimKH);
        pnlKH.add(new JLabel()); pnlKH.add(new JLabel());
        pnlMain.add(pnlKH);
        pnlMain.add(Box.createVerticalStrut(10));

        // ---- Thông tin xe ----
        JPanel pnlXe = new JPanel(new GridLayout(2, 4, 10, 8));
        pnlXe.setBorder(new TitledBorder("Thông tin xe"));
        pnlXe.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        pnlXe.add(makeLabel("Biển số xe:")); txtBienSo = makeField(); pnlXe.add(txtBienSo);
        pnlXe.add(makeLabel("Tên xe:")); txtTenXe = makeField(); pnlXe.add(txtTenXe);
        JButton btnTimXe = new JButton("Tìm xe");
        btnTimXe.addActionListener(e -> JOptionPane.showMessageDialog(this, "Kết nối DB để tìm xe trống"));
        pnlXe.add(makeLabel("Đơn giá/ngày:")); txtDonGia = makeField(); pnlXe.add(txtDonGia);
        pnlXe.add(new JLabel()); pnlXe.add(btnTimXe);
        pnlMain.add(pnlXe);
        pnlMain.add(Box.createVerticalStrut(10));

        // ---- Thời gian & địa điểm ----
        JPanel pnlTime = new JPanel(new GridLayout(3, 4, 10, 8));
        pnlTime.setBorder(new TitledBorder("Thời gian & địa điểm"));
        pnlTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtNgayNhan = makeField(); txtNgayNhan.setText(LocalDate.now().format(fmt));
        txtNgayTra  = makeField(); txtNgayTra.setText(LocalDate.now().plusDays(1).format(fmt));
        pnlTime.add(makeLabel("Ngày nhận (dd/MM/yyyy):")); pnlTime.add(txtNgayNhan);
        pnlTime.add(makeLabel("Ngày trả (dd/MM/yyyy):")); pnlTime.add(txtNgayTra);
        pnlTime.add(makeLabel("Địa điểm nhận:")); txtDiaDiem = makeField(); pnlTime.add(txtDiaDiem);
        pnlTime.add(new JLabel()); pnlTime.add(new JLabel());
        pnlTime.add(makeLabel("Mã khuyến mãi:")); txtMaKM = makeField(); pnlTime.add(txtMaKM);
        JButton btnTinhTien = new JButton("Tính tiền");
        btnTinhTien.addActionListener(e -> tinhTongTien());
        pnlTime.add(new JLabel()); pnlTime.add(btnTinhTien);
        pnlMain.add(pnlTime);
        pnlMain.add(Box.createVerticalStrut(10));

        // ---- Tổng tiền ----
        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        pnlTotal.setBorder(new TitledBorder("Tổng tiền hợp đồng"));
        pnlTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        lblTongTien = new JLabel("0 VNĐ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTongTien.setForeground(new Color(30, 144, 255));
        pnlTotal.add(new JLabel("Tổng tiền: "));
        pnlTotal.add(lblTongTien);
        pnlMain.add(pnlTotal);

        add(new JScrollPane(pnlMain), BorderLayout.CENTER);

        // ---- Nút ----
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnSave   = new JButton("Lập hợp đồng");
        btnCancel = new JButton("Hủy");
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(140, 34));
        btnCancel.setPreferredSize(new Dimension(90, 34));
        pnlBtn.add(btnCancel);
        pnlBtn.add(btnSave);
        add(pnlBtn, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            if (!validate_()) return;
            saved = true;
            JOptionPane.showMessageDialog(this, "Hợp đồng đã được lập!\nKết nối DB để lưu vào bảng HOPDONG.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        btnCancel.addActionListener(e -> dispose());
    }

    private void tinhTongTien() {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ngayNhan = LocalDate.parse(txtNgayNhan.getText().trim(), fmt);
            LocalDate ngayTra  = LocalDate.parse(txtNgayTra.getText().trim(), fmt);
            long soNgay = ChronoUnit.DAYS.between(ngayNhan, ngayTra);
            if (soNgay <= 0) { JOptionPane.showMessageDialog(this, "Ngày trả phải sau ngày nhận!"); return; }
            double donGia = txtDonGia.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtDonGia.getText().trim().replaceAll("[^0-9.]", ""));
            double tong = soNgay * donGia;
            lblTongTien.setText(String.format("%,.0f VNĐ  (%d ngày × %,.0f)", tong, soNgay, donGia));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Kiểm tra lại ngày hoặc đơn giá!");
        }
    }

    private boolean validate_() {
        if (txtMaKH.getText().trim().isEmpty())    { alert("Vui lòng chọn khách hàng!"); return false; }
        if (txtBienSo.getText().trim().isEmpty())  { alert("Vui lòng chọn xe!"); return false; }
        if (txtNgayNhan.getText().trim().isEmpty()) { alert("Vui lòng nhập ngày nhận!"); return false; }
        if (txtNgayTra.getText().trim().isEmpty())  { alert("Vui lòng nhập ngày trả!"); return false; }
        if (txtDiaDiem.getText().trim().isEmpty())  { alert("Vui lòng nhập địa điểm nhận xe!"); return false; }
        return true;
    }

    private void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
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

    public boolean isSaved()    { return saved; }
    public String getMaKH()     { return txtMaKH.getText().trim(); }
    public String getBienSo()   { return txtBienSo.getText().trim(); }
    public String getNgayNhan() { return txtNgayNhan.getText().trim(); }
    public String getNgayTra()  { return txtNgayTra.getText().trim(); }
    public String getDiaDiem()  { return txtDiaDiem.getText().trim(); }
    public String getMaKM()     { return txtMaKM.getText().trim(); }
}
