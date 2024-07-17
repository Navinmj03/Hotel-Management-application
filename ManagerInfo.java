import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;

public class ManagerInfo extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    ManagerInfo() {
        // Set up frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Create labels with enhanced styling
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Color headerColor = new Color(0, 128, 0); // Green

        JLabel l1 = new JLabel("Name");
        l1.setBounds(40, 10, 100, 20);
        l1.setFont(labelFont);
        l1.setForeground(headerColor);
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(170, 10, 100, 20);
        l2.setFont(labelFont);
        l2.setForeground(headerColor);
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290, 10, 100, 20);
        l3.setFont(labelFont);
        l3.setForeground(headerColor);
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400, 10, 100, 20);
        l4.setFont(labelFont);
        l4.setForeground(headerColor);
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540, 10, 100, 20);
        l5.setFont(labelFont);
        l5.setForeground(headerColor);
        add(l5);

        JLabel l6 = new JLabel("Phone");
        l6.setBounds(670, 10, 100, 20);
        l6.setFont(labelFont);
        l6.setForeground(headerColor);
        add(l6);

        JLabel l7 = new JLabel("Email");
        l7.setBounds(790, 10, 100, 20);
        l7.setFont(labelFont);
        l7.setForeground(headerColor);
        add(l7);

        JLabel l8 = new JLabel("Aadhar");
        l8.setBounds(910, 10, 100, 20);
        l8.setFont(labelFont);
        l8.setForeground(headerColor);
        add(l8);

        // Create table with enhanced styling
        table = new JTable();
        table.setBounds(0, 40, 1000, 400);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFillsViewportHeight(true);
        add(table);

        // Retrieve data from database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM employee WHERE job = 'Manager'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back button with styling
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(420, 500, 120, 30);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Set up frame properties
        setBounds(300, 200, 1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Manager Information");
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
        new ManagerInfo();
    }
}
