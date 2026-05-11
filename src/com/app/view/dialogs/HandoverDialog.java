package view.dialogs;

import javax.swing.*;
import java.awt.*;

public class HandoverDialog extends JDialog {
    public HandoverDialog(JFrame parent) {
        super(parent, "Biên Bản Bàn Giao Xe", true);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        JPanel pnlFields = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlFields.add(new JLabel(" Tình trạng nhận:")); pnlFields.add(new JTextArea());
        pnlFields.add(new JLabel(" Phụ tùng kèm theo:")); pnlFields.add(new JTextField());
        
        add(new JLabel("XÁC NHẬN TÌNH TRẠNG XE", SwingConstants.CENTER), BorderLayout.NORTH);
        add(pnlFields, BorderLayout.CENTER);
        add(new JButton("Xác Nhận Ký Biên Bản"), BorderLayout.SOUTH);
    }
}