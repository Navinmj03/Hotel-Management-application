import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Room extends JFrame implements ActionListener {
    JTable table;
    JButton back;

    Room() {
        // Set up frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Load and scale image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("room.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600); // Crop image
        add(image);

        // Create labels with enhanced styling
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color headerColor = new Color(0, 128, 0); // Green

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(10, 10, 100, 20);
        l1.setFont(labelFont);
        l1.setForeground(headerColor);
        add(l1);

        JLabel l2 = new JLabel("Availability");
        l2.setBounds(120, 10, 100, 20);
        l2.setFont(labelFont);
        l2.setForeground(headerColor);
        add(l2);

        JLabel l3 = new JLabel("Status");
        l3.setBounds(230, 10, 100, 20);
        l3.setFont(labelFont);
        l3.setForeground(headerColor);
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(330, 10, 100, 20);
        l4.setFont(labelFont);
        l4.setForeground(headerColor);
        add(l4);

        JLabel l5 = new JLabel("Type");
        l5.setBounds(420, 10, 100, 20);
        l5.setFont(labelFont);
        l5.setForeground(headerColor);
        add(l5);

        // Create table with enhanced styling
        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFillsViewportHeight(true);
        add(table);

        // Retrieve data from database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back button with styling
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200, 500, 120, 30);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Set up frame properties
        setBounds(300, 200, 1050, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Room Information");
        addBorderToTable();
    }

    // Method to add a border to the table
    private void addBorderToTable() {
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Reception();
    }

    public static void main(String[] args) {
        new Room();
    }
}
