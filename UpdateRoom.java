import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateRoom extends JFrame implements ActionListener
{
    Choice ccustomer;
    JTextField tfroom,tfavail,tfstatus;
    JButton check,update,back;
    
    UpdateRoom() 
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text=new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma",Font.PLAIN,25));
        text.setBounds(30,20,250,30);
        text.setForeground(Color.BLACK);
        add(text);

        JLabel lblid=new JLabel("Customer Id");
        lblid.setBounds(30,80,100,20);
        add(lblid);

        ccustomer=new Choice();
        ccustomer.setBounds(200,80,150,25);
        add(ccustomer);

        try
        {
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            while(rs.next()) 
            {
                ccustomer.add(rs.getString("number"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom=new JLabel("Room Number");
        lblroom.setBounds(30,130,100,20);
        add(lblroom);

        tfroom=new JTextField();
        tfroom.setBounds(200,130,150,25);
        add(tfroom);

        JLabel lblname=new JLabel("Availability");
        lblname.setBounds(30,180,100,20);
        add(lblname);

        tfavail=new JTextField();
        tfavail.setBounds(200,180,150,25);
        add(tfavail);

        JLabel lblcheckin=new JLabel("Cleaning status");
        lblcheckin.setBounds(30,230,100,20);
        add(lblcheckin);

        tfstatus=new JTextField();
        tfstatus.setBounds(200,230,150,25);
        add(tfstatus);

        check=new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30,300,100,30);
        check.addActionListener(this);
        add(check);

        update=new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150,300,100,30);
        update.addActionListener(this);
        add(update);

        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270,300,100,30);
        back.addActionListener(this);
        add(back);

        setBounds(500,200,480,450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==check)
        {
            String id=ccustomer.getSelectedItem();
            String query="select * from customer where number='"+id+"'";
            try 
            {
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery(query);
                while(rs.next()) 
                {
                    tfroom.setText(rs.getString("room"));
                }

                ResultSet rs2=c.s.executeQuery("select * from room where roomnumber='"+tfroom.getText()+"'");
                while(rs2.next())
                {
                    tfavail.setText(rs2.getString("available"));
                    tfstatus.setText(rs2.getString("status"));
                }
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        else if (ae.getSource()==update)
        {
            //String number=ccustomer.getSelectedItem();
            String room=tfroom.getText();
            String available=tfavail.getText();
            String status=tfstatus.getText();

            try {
                Conn c=new Conn();
                c.s.executeUpdate("update room set available='"+available+"',status='"+status+"' where roomnumber = '"+room+"'" );

                JOptionPane.showMessageDialog(null,"Data Updated Successfully");

                setVisible(false);
                new Reception();

            } catch (Exception e) {
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

		new UpdateRoom();

	}
}