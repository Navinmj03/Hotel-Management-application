import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ReceptLogin extends JFrame implements ActionListener{
    
    JLabel l1,l2,bl,titleLabel;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;

    ReceptLogin()
    {
        setTitle("Login");
        setBounds(500,200,400, 350);//label,button positioned on the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);//custom position

        // Title label
        titleLabel = new JLabel("Reception Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(140, 10, 250, 30);
        add(titleLabel);

        l1 = new JLabel("Username");
        l1.setBounds(50,60,80,20);
        add(l1);

        t1=new JTextField();
        t1.setBounds(140,60,150,20);
        add(t1);
        
        l2 = new JLabel("Password");
        l2.setBounds(50,100,80,20);
        add(l2);

        t2=new JPasswordField();
        t2.setBounds(140,100,150,20);
        add(t2);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("recp.jpg"));
        Image i2 = i1.getImage().getScaledInstance(275,190,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(60,200,275,190);
        add(image);

        b1 = new JButton("Login");
        b1.setBounds(150,150,85,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        setSize(400,420);
        setLocation(500,200);
    }

   public void actionPerformed(ActionEvent ae)
   {
        if(ae.getSource()==b1)
        {
        try
        {
            Conn c1 = new Conn();
            String u = t1.getText();
            char[] passwordChars = t2.getPassword();
            String v = new String(passwordChars);

            
            String q = "select * from receptionLogin where username='"+u+"' and password='"+v+"'"; //string closed and concatenated
            
            ResultSet rs = c1.s.executeQuery(q); 
            if(rs.next())
            { 
                new Reception().setVisible(true);
                setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid login");
                setVisible(false);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }else if(ae.getSource()==b2)
        {
            System.exit(0);
        }
    }
    public static void main(String[] arg){
        new ReceptLogin();
    }
}

    
