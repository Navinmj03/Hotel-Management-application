import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class CheckOut extends JFrame implements ActionListener
{

    Choice ccustomer;
    JLabel lblroomnumber, lblcheckouttime, lblcheckintime;
    JButton checkout,back;
    CheckOut()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Checkout");
        text.setBounds(100,20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        add(text);

        JLabel lblid =new JLabel("Customer Id");
        lblid.setBounds(30,80, 100, 30);
        add(lblid);
        
        ccustomer = new Choice ();
        ccustomer.setBounds(150,80 ,150,25);
        add(ccustomer);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 100, 30);
        add(lblroomnumber);

        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);
        
        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 100,30);
        add(lblcheckintime);

        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);

        Date date=new Date();
        
        lblcheckouttime =new  JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 150, 30);
        add(lblcheckouttime);

        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);

        ccustomer.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent ie) 
            {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '" + ccustomer.getSelectedItem() + "'");
                    if (rs.next()) 
                    {
                        lblroomnumber.setText(rs.getString("room"));
                        lblcheckintime.setText(rs.getString("checkintime"));
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) 
            {
                ccustomer.add(rs.getString("number"));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("checkout.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(350, 50, 400, 250);
        add(image);

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkout) 
        {
            String query1 = "delete from customer where number = '"+ccustomer.getSelectedItem()+"'";
            String query2 = "update room set available = 'AVAILABLE' where roomnumber = '"+lblroomnumber.getText()+"'";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Checkout done");

                setVisible(false);
                new Reception();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        } 
        else 
        {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) 
    {
        new CheckOut();
    }
}