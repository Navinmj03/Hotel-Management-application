import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDrivers extends JFrame implements ActionListener
{
    JButton jbadd,jbcancel;
    JTextField tfname,tfage,tfcmp,tfmd,tfloc;
    JComboBox<String> avcb,agecb;
    AddDrivers()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel hd=new JLabel("Add Drivers");
        hd.setFont(new Font("Tahoma",Font.BOLD,18));
        hd.setBounds(150,20,200,20);
        add(hd);

        JLabel lblname=new JLabel("Name");
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblname.setBounds(60,70,120,30);
        add(lblname);

        tfname=new JTextField();
        tfname.setBounds(200,70,150,30);
        add(tfname);
        
        JLabel lblage=new JLabel("Age");
        lblage.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblage.setBounds(60,110,120,30);
        add(lblage);

        tfage=new JTextField();
        tfage.setBounds(200,110,150,30);
        add(tfage);

        JLabel lblgender=new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblgender.setBounds(60,150,120,30);
        add(lblgender);

        String genderOptions[]={"MALE","FEMALE"};
        agecb=new JComboBox<>(genderOptions);
        agecb.setBounds(200,150,150,30);
        agecb.setBackground(Color.WHITE);
        add(agecb);

        JLabel lblcmp=new JLabel("Car Company");
        lblcmp.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblcmp.setBounds(60,190,120,30);
        add(lblcmp);

        tfcmp=new JTextField();
        tfcmp.setBounds(200,190,150,30);
        add(tfcmp);

        JLabel lbltype=new JLabel("Model"); 
        lbltype.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbltype.setBounds(60,230,120,30);
        add(lbltype);

        tfmd=new JTextField();
        tfmd.setBounds(200,230,150,30);
        add(tfmd);

        JLabel lblavl=new JLabel("Availability"); 
        lblavl.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblavl.setBounds(60,270,120,30);
        add(lblavl);

        String avlOptions[]={"AVAILABLE","BUSY"};
        avcb=new JComboBox<>(avlOptions);
        avcb.setBounds(200,270,150,30);
        avcb.setBackground(Color.WHITE);
        add(avcb);

        JLabel lblloc=new JLabel("Location"); 
        lblloc.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblloc.setBounds(60,310,120,30);
        add(lblloc);

        tfloc=new JTextField();
        tfloc.setBounds(200,310,150,30);
        add(tfloc);


        jbadd=new JButton("Add Driver");
        jbadd.setForeground(Color.WHITE);
        jbadd.setBackground(Color.BLACK);
        jbadd.setBounds(60,365,130,30);
        jbadd.addActionListener(this);
        add(jbadd);

        jbcancel=new JButton("Cancel");
        jbcancel.setForeground(Color.WHITE);
        jbcancel.setBackground(Color.BLACK);
        jbcancel.setBounds(220,365,130,30);
        jbcancel.addActionListener(this);
        add(jbcancel);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("adddriver.jpeg"));
		JLabel image=new JLabel(i1);
		image.setBounds(250,10,500,470);
		add(image);

        setBounds(440,130,700,470);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jbadd)
        {
        String name=tfname.getText();
        String age=tfage.getText();
        String gender=(String)agecb.getSelectedItem();
        String company=tfcmp.getText();
        String brand=tfmd.getText();
        String status=(String)avcb.getSelectedItem();
        String location=tfloc.getText();

        if(name.equals("") || age.equals("") || company.equals("") || brand.equals("") || location.equals("") )
        {
            JOptionPane.showMessageDialog(null,"Fill Every Details");
            return;
        }

        try
        {
            Conn cn=new Conn();
            String query="INSERT INTO driver values( '"+name+"', '"+age+"', '"+gender+"','"+company+"', '"+brand+"', '"+status+"','"+location+"')";
            cn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"New Driver added succesfully");
            setVisible(false);
    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        else
        {
            setVisible(false);
        }
    }
    public static void main(String[] arg)
    {
    new AddDrivers();
    }
}

