import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrontPage extends JFrame implements ActionListener 
{

    JButton managerlogin, receptlogin;
    Image backgroundImage;

    FrontPage() 
	{
        // Load the background image
        backgroundImage = new ImageIcon(ClassLoader.getSystemResource("front.jpg")).getImage();

        // Create a custom panel with the background image
        JPanel backgroundPanel = new JPanel() 
		{
            @Override
            protected void paintComponent(Graphics g) 
			{
                super.paintComponent(g);
                // Draw the background image resized to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        managerlogin = new JButton("Manager Login");
        managerlogin.setBounds(10, 20, 150, 30);
        managerlogin.setBackground(Color.BLACK);
        managerlogin.setForeground(Color.WHITE);
        managerlogin.addActionListener(this);
        backgroundPanel.add(managerlogin);

        receptlogin = new JButton("Reception Login");
        receptlogin.setBounds(10, 70, 150, 30);
        receptlogin.setBackground(Color.BLACK);
        receptlogin.setForeground(Color.WHITE);
        receptlogin.addActionListener(this);
        backgroundPanel.add(receptlogin);

        add(backgroundPanel);

		setSize(700, 500);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
	{
        if (ae.getSource() == managerlogin) 
		{
            setVisible(false);
            new ManagerLogin();
        } 
		else if (ae.getSource() == receptlogin) 
		{
            setVisible(false);
            new ReceptLogin();
        }
    }

    public static void main(String[] args) 
	{
        new FrontPage();
    }
}
/*

 Initialization:

Load the background image.
Create and set up the main application window (JFrame).
Create a custom panel:

Extend JPanel to override the paintComponent method.
Draw the background image resized to fit the panel.
Add buttons:

Create buttons for "Manager Login" and "Reception Login".
Set their positions, background, and foreground colors.
Add action listeners to the buttons to handle click events.
Configure the window:

Add the custom panel to the main window.
Set the window size and center it on the screen.
Set the default close operation.
Make the window visible.
Handle button click events:

When "Manager Login" is clicked:
Hide the front page window.
Open the ManagerLogin window.
When "Reception Login" is clicked:
Hide the front page window.
Open the ReceptLogin window.
 */