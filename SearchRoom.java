import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {
    JTable table;
    JButton back, submit;
    JComboBox<String> bedtype;
    JCheckBox available;

    SearchRoom() {
        // Set up frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma", Font.BOLD, 24)); // Increased font size
        text.setBounds(400, 30, 300, 30);
        add(text);

        JLabel lbbed = new JLabel("Bed Type");
        lbbed.setBounds(50, 100, 100, 20);
        lbbed.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lbbed);

        bedtype = new JComboBox<>(new String[]{"Single Bed", "Double Bed"});
        bedtype.setBounds(150, 100, 150, 25);
        bedtype.setBackground(Color.WHITE);
        add(bedtype);

        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);

        // Header Labels
        String[] headers = {"Room Number", "Availability", "Status", "Price", "Type"};
        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i]);
            label.setBounds(50 + (i * 200), 160, 100, 20);
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
            ResultSet rs = c.s.executeQuery("SELECT * FROM room");
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
        setTitle("Room Search");
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
                String selectedBedType = ((String) bedtype.getSelectedItem()).trim();
                String query1 = "SELECT * FROM room WHERE bedtype = '" + selectedBedType + "'";
                String query2 = "SELECT * FROM room WHERE available = 'AVAILABLE' AND bedtype = '" + selectedBedType + "'";

                Conn conn = new Conn();
                ResultSet rs;
                if (available.isSelected()) {
                    rs = conn.s.executeQuery(query2);
                } else {
                    rs = conn.s.executeQuery(query1);
                }
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
        new SearchRoom();
    }
}
