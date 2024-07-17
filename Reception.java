import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Reception extends JFrame implements ActionListener{

	JButton newCustomer,rooms,allEmployee,customers,managerinfo,checkout,updateStatus,roomStatus,pickup,searchRoom,logout;
	Reception() {

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		newCustomer=new JButton("New Customer Form");
		newCustomer.setBounds(10,30,200,30);
		newCustomer.setBackground(Color.BLACK);
		newCustomer.setForeground(Color.WHITE);
		newCustomer.addActionListener(this);
		add(newCustomer);

		customers=new JButton("Customer Info");
		customers.setBounds(10,70,200,30);
		customers.setBackground(Color.BLACK);
		customers.setForeground(Color.WHITE);
		customers.addActionListener(this);
		add(customers);

		allEmployee=new JButton("All Employees");
		allEmployee.setBounds(10,110,200,30);
		allEmployee.setBackground(Color.BLACK);
		allEmployee.setForeground(Color.WHITE);
		allEmployee.addActionListener(this);
		add(allEmployee);

		managerinfo=new JButton("Manager Info");
		managerinfo.setBounds(10,150,200,30);
		managerinfo.setBackground(Color.BLACK);
		managerinfo.setForeground(Color.WHITE);
		managerinfo.addActionListener(this);
		add(managerinfo);

		rooms=new JButton("Rooms");
		rooms.setBounds(10,190,200,30);
		rooms.setBackground(Color.BLACK);
		rooms.setForeground(Color.WHITE);
		rooms.addActionListener(this);
		add(rooms);

		searchRoom=new JButton("Search Room");
		searchRoom.setBounds(10,230,200,30);
		searchRoom.setBackground(Color.BLACK);
		searchRoom.setForeground(Color.WHITE);
		searchRoom.addActionListener(this);
		add(searchRoom);

		roomStatus=new JButton("Update Status");
		roomStatus.setBounds(10,270,200,30);
		roomStatus.setBackground(Color.BLACK);
		roomStatus.setForeground(Color.WHITE);
		roomStatus.addActionListener(this);
		add(roomStatus);

		updateStatus=new JButton("Update Room status");
		updateStatus.setBounds(10,310,200,30);
		updateStatus.setBackground(Color.BLACK);
		updateStatus.setForeground(Color.WHITE);
		updateStatus.addActionListener(this);
		add(updateStatus);

		pickup=new JButton("Pickup Service");
		pickup.setBounds(10,350,200,30);
		pickup.setBackground(Color.BLACK);
		pickup.setForeground(Color.WHITE);
		pickup.addActionListener(this);
		add(pickup);

		checkout=new JButton("Checkout");
		checkout.setBounds(10,390,200,30);
		checkout.setBackground(Color.BLACK);
		checkout.setForeground(Color.WHITE);
		checkout.addActionListener(this);
		add(checkout);

		logout=new JButton("Logout");
		logout.setBounds(10,430,200,30);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.addActionListener(this);
		add(logout);

		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("reception.jpg"));
		JLabel image=new JLabel(i1);
		image.setBounds(250,30,500,470);
		add(image);
		
		setBounds(350,200,800,570);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==newCustomer)
		{
			setVisible(false);
			new NewCustomer();
		}
		else if(ae.getSource()==customers)
		{
			setVisible(false);
			new CustomerInfo();
		}
		else if(ae.getSource()==allEmployee)
		{
			setVisible(false);
			new EmployeeInfo();
		}
		else if(ae.getSource()==managerinfo)
		{
			setVisible(false);
			new ManagerInfo();
		}
		else if(ae.getSource()==rooms)
		{
			setVisible(false);
			new Room();
		}
		else if(ae.getSource()==searchRoom)
		{
			setVisible(false);
			new SearchRoom();
		}
		else if(ae.getSource()==roomStatus)
		{
			setVisible(false);
			new UpdateCheckk();
		}
		else if(ae.getSource()==updateStatus)
		{
			setVisible(false);
			new UpdateRoom();
		}
		else if(ae.getSource()==pickup)
		{
			setVisible(false);
			new Pickup();
		}
		else if(ae.getSource()==checkout)
		{
			setVisible(false);
			new CheckOut();
		}
		else if(ae.getSource()==logout)
		{
			setVisible(false);
            new FrontPage();
			//System.exit(0);
		}
	}
	public static void main(String[] args)
	{
		new Reception();

	}
}
/*
 * Initialization:

Set the background color of the container.
Set the layout to null for absolute positioning of components.
Create Buttons:

Create buttons for "New Customer Form", "Customer Info", "All Employees", "Manager Info", "Rooms", "Search Room", "Update Status", "Update Room Status", "Pickup Service", "Checkout", and "Logout".
Set their bounds (position and size), background color, and foreground color.
Add action listeners to the buttons.
Add Buttons to Frame:

Add each button to the frame.
Add Background Image:

Load an image icon and create a label with this image.
Set the bounds for the image label and add it to the frame.
Configure the Window:

Set the window's bounds (size and position).
Make the window visible.
Handle Button Click Events:

Define actions to be taken when each button is clicked:
For "New Customer Form", "Customer Info", "All Employees", "Manager Info", "Rooms", "Search Room", "Update Status", "Update Room Status", "Pickup Service", and "Checkout":
Hide the current window.
Open the respective window (e.g., NewCustomer, CustomerInfo, etc.).
For "Logout":
Hide the current window.
Open the FrontPage window.
Optionally, exit the application.
 */