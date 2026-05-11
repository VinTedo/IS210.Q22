package view.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaymentDialog extends JDialog {
    private JTextField txtAmount, txtContent;
    private JComboBox<String> cbMethod;
    private JLabel lblInvoiceId;

    public PaymentDialog(JFrame parent, String invoiceId, double totalAmount) {
        super(parent, "Xác nhận Thanh toán - " + invoiceId, true);
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        // --- Giao diện nhập liệu ---
        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 10, 20));
        pnlForm.setBorder(new EmptyBorder(20, 30, 20, 30));

        pnlForm.add(new JLabel("Mã Hóa Đơn:"));
        lblInvoiceId = new JLabel(invoiceId);
        lblInvoiceId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pnlForm.add(lblInvoiceId);

        pnlForm.add(new JLabel("Số tiền thanh toán:"));
        txtAmount = new JTextField(String.valueOf(totalAmount));
        pnlForm.add(txtAmount);

        pnlForm.add(new JLabel("Phương thức:"));
        // Khớp với trường PhuongThuc trong bảng THANHTOAN
        cbMethod = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản", "Thẻ tín dụng"});
        pnlForm.add(cbMethod);

        pnlForm.add(new JLabel("Nội dung:"));
        txtContent = new JTextField("Thanh toán tiền thuê xe");
        pnlForm.add(txtContent);

        add(pnlForm, BorderLayout.CENTER);

        // --- Nút điều khiển ---
        JPanel pnlButtons = new JPanel();
        JButton btnConfirm = new JButton("Xác nhận");
        JButton btnCancel = new JButton("Hủy");
        
        btnConfirm.setBackground(new Color(46, 204, 113));
        btnConfirm.setForeground(Color.WHITE);
        
        pnlButtons.add(btnConfirm);
        pnlButtons.add(btnCancel);
        add(pnlButtons, BorderLayout.SOUTH);

        // Xử lý sự kiện
        btnConfirm.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đã lưu thông tin thanh toán vào Database!");
            this.dispose();
        });

        btnCancel.addActionListener(e -> this.dispose());
    }
}