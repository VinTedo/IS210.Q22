package view.Panels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel pnlCards = new JPanel(new GridLayout(1, 4, 20, 0));
        pnlCards.setBackground(Color.WHITE);
        pnlCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCards.add(createStatCard("TỔNG SỐ XE", "45", new Color(30, 144, 255)));
        pnlCards.add(createStatCard("XE ĐANG THUÊ", "12", new Color(46, 204, 113)));
        pnlCards.add(createStatCard("HỢP ĐỒNG MỚI", "08", new Color(241, 196, 15)));
        pnlCards.add(createStatCard("DOANH THU (TR)", "150", new Color(231, 76, 60)));

        add(pnlCards, BorderLayout.NORTH);
        add(new JLabel("BIỂU ĐỒ TĂNG TRƯỞNG (PLACEHOLDER)", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setPreferredSize(new Dimension(200, 120));
        
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setForeground(Color.WHITE);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 36));

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        return card;
    }
}