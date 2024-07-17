import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

	public class NewCustomer extends JFrame implements ActionListener
	{
		JTextField t1,t2,t3,t4,t5,t6;
        JComboBox<String> comboid;
        JRadioButton r1,r2;
		JButton add,back;
		JLabel ctime;
        Choice c1;

		NewCustomer()
		{
		
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel lblName = new JLabel("NEW CUSTOMER FORM");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(100, 20, 300, 30);
		add(lblName);

        JLabel lblId = new JLabel("ID");
		lblId.setBounds(35, 80, 100, 20);
        lblId.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblId);     
        
        String options[] = {"Passport", "Aadhar Card", "Voter Id", "Driving license"};
        comboid= new JComboBox<>(options);
		comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.WHITE);
		add(comboid);

        JLabel lblnumber= new JLabel("Number");
		lblnumber.setBounds(35, 120, 100, 20);
        lblnumber.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblnumber);     
        
        t1 = new JTextField();
		t1.setBounds(200, 120, 150, 25);
		add(t1);

        JLabel lblname= new JLabel("Name");
		lblname.setBounds(35, 160, 100, 20);
        lblname.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblname);     
        
        t2 = new JTextField();
		t2.setBounds(200, 160, 150, 25);
		add(t2);

        JLabel lblgender= new JLabel("Gender");
		lblgender.setBounds(35, 200, 100, 20);
        lblgender.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblgender);  

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        r1.setBounds(200, 200, 60, 25);
        add(r1);
                
        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        r2.setBounds(270, 200, 100, 25);
		add(r2);

		ButtonGroup bg=new ButtonGroup();
    	bg.add(r1);
    	bg.add(r2);

        JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(35, 240, 100, 20);
        lblCountry.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblCountry);
		
        t3 = new JTextField();
		t3.setBounds(200, 240, 150, 25);
		add(t3);

		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(35, 280, 150, 14);
        lblroom.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lblroom);

        c1 = new Choice();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room where available = 'AVAILABLE'");
            while(rs.next())
            {
                c1.add(rs.getString("roomnumber"));    
            }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            c1.setBounds(200, 280, 150, 25);
		    add(c1);

		JLabel lbltime = new JLabel("Checkin Time");
		lbltime.setBounds(35, 320, 150, 25);
        lbltime.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lbltime);

		Date date=new Date();

		ctime = new JLabel("" + date);
		ctime.setBounds(200, 320, 180, 25);
        ctime.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(ctime);

		JLabel lbldeposit = new JLabel("Deposit");
		lbldeposit.setBounds(35, 360, 100, 20);
        lbldeposit.setFont(new Font("Yu Mincho", Font.PLAIN, 17));
		add(lbldeposit);
		
        t4 = new JTextField();
		t4.setBounds(200, 360, 150, 25);
		add(t4);

		add = new JButton("Add");
		add.setBackground(Color.BLACK);
		add.setForeground(Color.WHITE);
		add.setBounds(50,410,120,30);
		add.addActionListener(this);
		add(add);

		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(200,410,120,30);
		back.addActionListener(this);
		add(back);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("newcustomer.png"));
		Image i2 = i1.getImage().getScaledInstance(300, 400,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400,50,300,400); //crop image
		add(image);

        setBounds(350, 200, 850, 550);
        setVisible(true);
		}

	public void actionPerformed(ActionEvent ae)
  	{
		if(ae.getSource()==add)
		{
			String id=(String) comboid.getSelectedItem();
			String number=t1.getText();
			String name=t2.getText();
			String gender=null;
			if (id.isEmpty() || number.isEmpty()) 
			{
				JOptionPane.showMessageDialog(this, "ID and Number fields cannot be empty");
				return;
			}

			if(r2.isSelected())
			{
				gender = "female";
			}
			else
			{
				gender = "male";
			}
			String country=t3.getText();
			String deposit=t4.getText();
			String room=c1.getSelectedItem();
			String time=ctime.getText();

			try
			{
				String query="insert into customer values( '"+id+"', '"+number+"', '"+name+"','"+gender+"', '"+country+"','"+room+"','"+time+"','"+deposit+"')";
				String query2="update room set available = 'OCCUPIED' where roomnumber='"+room+"'";
				
				Conn cn=new Conn();
				cn.s.executeUpdate(query);
				cn.s.executeUpdate(query2);
				JOptionPane.showMessageDialog(null,"New Customer added succesfully");
				
				setVisible(false);
				new Reception();
			
			}
			catch (SQLException e)
			{
				if (e.getErrorCode() == 1062) 
				{ // Duplicate entry error code
					JOptionPane.showMessageDialog(this, "This ID and Number combination already exists");
				}
				else 
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error adding customer");
				}
			}
		}
		else if(ae.getSource()==back)
		{
			setVisible(false);
			new Reception();
		}
	}

	public static void main(String[] args) 
	{

		new NewCustomer();

	}
}
/*Initialization:

Define UI components such as JTextField, JComboBox, JRadioButton, JButton, and Choice.
Set the layout and background color of the content pane.
Add labels and input fields for customer details, including ID, number, name, gender, country, room number, check-in time, and deposit.
Add action listeners for the "Add" and "Back" buttons.
Load available room numbers from the database into the Choice component.
Display the current date and time for check-in.
Action Listener:

Handle actions for the "Add" and "Back" buttons.
When "Add" is clicked, retrieve input data, insert a new customer into the database, and update the room's availability status.
When "Back" is clicked, return to the Reception window.
UI Components:

Labels, text fields, radio buttons, combo box, choice, and buttons are created and added to the frame.
The form includes fields for customer ID, number, name, gender, country, room number, check-in time, and deposit.
Database Interaction:

The constructor retrieves available room numbers from the database and populates the choice component.
The actionPerformed method handles the addition of a new customer and updates the room status in the database.
Action Handling:

When the "Add" button is clicked, the customer details are inserted into the database, and the room status is updated.
When the "Back" button is clicked, the form closes, and the Reception window is reopened. */
