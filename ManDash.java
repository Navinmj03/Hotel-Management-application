import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ManDash extends JFrame implements ActionListener 
{
    ManDash()
    {
        //seting up jframe
        setBounds(0,0,1550,1000);
        setLayout(null);

        //setting up background image
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("mandash.jpg"));
        Image i2=i1.getImage().getScaledInstance(1550,1000,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1550,1000);
        add(image);

        //adding text lalbel
        JLabel text=new JLabel("INNSIGHT");
        text.setBounds(600,60,1000,50);
        text.setFont(new Font("serif",Font.PLAIN,46));
        text.setForeground(Color.WHITE);
        image.add(text);

        //adding menubar
        JMenuBar mb=new JMenuBar();
        mb.setBounds(0,0,1550,50);
        image.add(mb);
        
        JMenuItem managerMenu=new JMenu("MANAGER LOGIN");
        managerMenu.setForeground(Color.BLACK);
        mb.add(managerMenu);

        JMenuItem addemployee=new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        managerMenu.add(addemployee);

        JMenuItem addrooms=new JMenuItem("ADD ROOMS");
        addrooms.addActionListener(this);
        managerMenu.add(addrooms);

        JMenuItem adddrivers=new JMenuItem("ADD DRIVERS");
        adddrivers.addActionListener(this);
        managerMenu.add(adddrivers);

        JMenuItem logout=new JMenuItem("Logout");
        logout.addActionListener(this);
        managerMenu.add(logout);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        switch (command) 
        {
            case "ADD EMPLOYEE":
                new AddEmployee();
                break;
            case "ADD ROOMS":
                new AddRoom();
                break;
            case "ADD DRIVERS":
                new AddDrivers();
                break;
            case "Logout":
                setVisible(false);
                new FrontPage();
        }
    }

    public static void main(String[] args)
    {
        new ManDash();
    }
}
