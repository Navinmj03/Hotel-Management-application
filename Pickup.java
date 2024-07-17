import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Pickup extends JFrame implements ActionListener {
    JTable table;
    JButton back, submit;
    Choice typeofcar;

    Pickup() {
        // Set up frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Pickup Service");
        text.setFont(new Font("Tahoma", Font.BOLD, 24)); // Increased font size
        text.setBounds(400, 30, 300, 30);
        add(text);

        JLabel lbbed = new JLabel("Type of Car");
        lbbed.setBounds(50, 100, 100, 20);
        lbbed.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lbbed);

        typeofcar = new Choice();
        typeofcar.setBounds(150, 100, 200, 25);
        add(typeofcar);

        // Populate car types from the database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM driver");
            while (rs.next()) {
                typeofcar.add(rs.getString("brand"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create labels for table headers
        String[] headers = {"Name", "Age", "Gender", "Company", "Brand", "Availability", "Location"};
        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i]);
            label.setBounds(30 + (i * 120), 160, 100, 20);
            label.setFont(new Font("Arial", Font.BOLD, 16)); // Header font
            label.setForeground(new Color(0, 128, 0)); // Green color
            add(label);
        }

        // Create table with enhanced styling
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFillsViewportHeight(true);
        add(table);

        // Retrieve initial data from the database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM driver");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Submit button
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 520, 120, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.addActionListener(this);
        add(submit);

        // Back button
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(500, 520, 120, 30);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Set up frame properties
        setBounds(300, 200, 1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pickup Service");
        addBorderToTable();
    }

    // Method to add a border to the table
    private void addBorderToTable() {
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        table.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String query1 = "SELECT * FROM driver WHERE brand = '" + typeofcar.getSelectedItem() + "'";
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery(query1);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Pickup();
    }
}
