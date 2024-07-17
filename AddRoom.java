import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddRoom extends JFrame implements ActionListener
{
    JButton jbadd,jbcancel;
    JTextField tfroom,tfprice;
    JComboBox<String> avcb,clcb,typecb;
    AddRoom()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel hd=new JLabel("Add Rooms");
        hd.setFont(new Font("Tahoma",Font.BOLD,18));
        hd.setBounds(150,20,200,20);
        add(hd);

        JLabel lblroom=new JLabel("Room Number");
        lblroom.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblroom.setBounds(60,80,120,30);
        add(lblroom);

        tfroom=new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lblavl=new JLabel("Available"); //available
        lblavl.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblavl.setBounds(60,130,120,30);
        add(lblavl);

        String availableOptions[]={"AVAILABLE","OCCUPIED"};
        avcb=new JComboBox<>(availableOptions);
        avcb.setBounds(200,130,150,30);
        avcb.setBackground(Color.WHITE);
        add(avcb);

        JLabel lblclean=new JLabel("Cleaning status"); //cleaning status
        lblclean.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblclean.setBounds(60,180,120,30);
        add(lblclean);

        String cleanOptions[]={"CLEAN","DIRTY"};
        clcb=new JComboBox<>(cleanOptions);
        clcb.setBounds(200,180,150,30);
        clcb.setBackground(Color.WHITE);
        add(clcb);

        JLabel lblprice=new JLabel("Price");
        lblprice.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblprice.setBounds(60,230,120,30);
        add(lblprice);

        tfprice=new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);

        JLabel lbltype=new JLabel("Bed Type"); //Bed Type
        lbltype.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbltype.setBounds(60,280,120,30);
        add(lbltype);

        String bedOptions[]={"Single Bed","Double Bed"};
        typecb=new JComboBox<>(bedOptions);
        typecb.setBounds(200,280,150,30);
        typecb.setBackground(Color.WHITE);
        add(typecb);

        jbadd=new JButton("Add Room");
        jbadd.setForeground(Color.WHITE);
        jbadd.setBackground(Color.BLACK);
        jbadd.setBounds(60,350,130,30);
        jbadd.addActionListener(this);
        add(jbadd);

        jbcancel=new JButton("Cancel");
        jbcancel.setForeground(Color.WHITE);
        jbcancel.setBackground(Color.BLACK);
        jbcancel.setBounds(220,350,130,30);
        jbcancel.addActionListener(this);
        add(jbcancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("addroom.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,30,500,300); //crop image
        add(image);

        setBounds(430,150,940,470);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jbadd)
        {
        String roomnumber=tfroom.getText();
        String available=(String)avcb.getSelectedItem();
        String status=(String)clcb.getSelectedItem();
        String price=tfprice.getText();
        String bedtype=(String)typecb.getSelectedItem();
        if(roomnumber.equals("") || price.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Fill Every Details");
            return;
        }
        try
        {
            Conn cn=new Conn();
            String query="INSERT INTO room values( '"+roomnumber+"', '"+available+"', '"+status+"','"+price+"', '"+bedtype+"')";
            cn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"New Room added succesfully");
            setVisible(false);
    
        }
        catch (SQLException e) 
        {
        if (e.getErrorCode() == 1062) // Duplicate entry error code
        { 
            JOptionPane.showMessageDialog(this, "This Room number already exists");
        }
        else 
        {
            e.printStackTrace();
        }
    }
        }
        else
        {
            setVisible(false);
        }
    }
    public static void main(String[] arg)
    {
    new AddRoom();
    }
}

