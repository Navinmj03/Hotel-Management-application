import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener
{
  
  JTextField tfname,tfage,tfsal,tfph,tfmail,tfaad;
  JRadioButton rbmale,rbfemale;
  JButton submit;
  JComboBox<String> cbjob;

  AddEmployee()
  {
    setLayout(null);

    JLabel hd=new JLabel("Add Employee");
    hd.setFont(new Font("Tahoma",Font.BOLD,18));
    hd.setBounds(150,20,200,20);
    add(hd);
  
    JLabel lbname=new JLabel("NAME"); //name
    lbname.setBounds(60,70,120,30);
    lbname.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbname);

    tfname=new JTextField();
    tfname.setBounds(200,70,150,30);
    add(tfname);

    JLabel lbage=new JLabel("AGE"); //age
    lbage.setBounds(60,120,120,30);
    lbage.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbage);
    
    tfage=new JTextField();
    tfage.setBounds(200,120,150,30);
    add(tfage);
    
    JLabel lbgender=new JLabel("GENDER"); //gender
    lbgender.setBounds(60,170,120,30);
    lbgender.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbgender);
    
    rbmale=new JRadioButton("Male");
    rbmale.setBounds(200,170,70,30);
    rbmale.setFont(new Font("Tahoma",Font.PLAIN,14));
    rbmale.setBackground(Color.WHITE);
    add(rbmale);

    rbfemale=new JRadioButton("Female");
    rbfemale.setBounds(265,170,70,30);
    rbfemale.setFont(new Font("Tahoma",Font.PLAIN,14));
    rbfemale.setBackground(Color.WHITE);
    add(rbfemale);

    ButtonGroup bg=new ButtonGroup();
    bg.add(rbmale);
    bg.add(rbfemale);

    JLabel lbjob=new JLabel("JOB"); //job
    lbjob.setBounds(60,220,120,30);
    lbjob.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbjob);

    String str[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
    cbjob=new JComboBox<>(str);
    cbjob.setBounds(200,220,150,30);
    cbjob.setBackground(Color.WHITE);
    add(cbjob);

    JLabel lbsal=new JLabel("SALARY"); //age
    lbsal.setBounds(60,270,120,30);
    lbsal.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbsal);
    
    tfsal=new JTextField();
    tfsal.setBounds(200,270,150,30);
    add(tfsal);

    JLabel lbph=new JLabel("PHONE"); //phone
    lbph.setBounds(60,320,120,30);
    lbph.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbph);
    
    tfph=new JTextField();
    tfph.setBounds(200,320,150,30);
    add(tfph);

    JLabel lbmail=new JLabel("EMAIL"); //EMAIL
    lbmail.setBounds(60,370,120,30);
    lbmail.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbmail);
    
    tfmail=new JTextField();
    tfmail.setBounds(200,370,150,30);
    add(tfmail);

    JLabel lbaad=new JLabel("AADHAR"); //aadhar
    lbaad.setBounds(60,420,120,30);
    lbaad.setFont(new Font("Tahoma",Font.PLAIN,17));
    add(lbaad);
    
    tfaad=new JTextField();
    tfaad.setBounds(200,420,150,30);
    add(tfaad);

    submit=new JButton("SUBMIT");
    submit.setBackground(Color.BLACK);
    submit.setForeground(Color.WHITE);
    submit.setBounds(200,480,150,30);
    submit.addActionListener(this);
    add(submit);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("addemployee.jpg"));
    Image i2 = i1.getImage().getScaledInstance(450, 450,Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(380,60,450,370); //crop image
    add(image);

    getContentPane().setBackground(Color.WHITE);
    setBounds(430,130,860,600);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae)
  {
    String name=tfname.getText();
    String age=tfage.getText();
    String salary=tfsal.getText();
    String phone=tfph.getText();
    String email=tfmail.getText();
    String aadhar=tfaad.getText();
    String gender=null;

    if(name.equals("") || age.equals("") || salary.equals("") || phone.equals("") || email.equals("") || aadhar.equals(""))
    {
        JOptionPane.showMessageDialog(null,"Fill Every Details");
        return;
    }
    
    if(rbmale.isSelected())
    {
        gender = "male";
    
    }
    else if(rbfemale.isSelected())
    {
        gender = "female";
    }    
    String job = (String)cbjob.getSelectedItem();
    try
    {
        Conn cn=new Conn();
        String query="INSERT INTO employee values( '"+name+"', '"+age+"', '"+gender+"','"+job+"', '"+salary+"', '"+phone+"','"+aadhar+"', '"+email+"')";
        cn.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Employee added succesfully");
        setVisible(false);
    }
    catch (SQLException e) 
    {
        if (e.getErrorCode() == 1062) // Duplicate entry error code
        { 
            JOptionPane.showMessageDialog(this, "This aadhar number already exists");
        }
        else 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding employee");
        }
    }
  }
  public static void main(String[] arg)
  {
    new AddEmployee();
  }
}


