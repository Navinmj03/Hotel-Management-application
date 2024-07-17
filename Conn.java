import java.sql.*;
public class Conn 
{
    Connection c;//instance variable for connection
    Statement s;//instance variable for statement
    //to manage the database connection and execute SQL statements, respectively.
    Conn()
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // This registers the driver with the DriverManager so that it can be used to make a database connection.
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem","root","navinmj003");//t establishes a connection to the MySQL
            s=c.createStatement();//creating the statement
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
/*
 * Initialization:

Declare instance variables for Connection and Statement.
Load JDBC Driver:

Use Class.forName to load the MySQL JDBC driver.
Establish Database Connection:

Use DriverManager.getConnection to connect to the MySQL database.
Pass the database URL, username, and password to getConnection.
Create Statement Object:

Use the Connection object to create a Statement object for executing SQL queries.
Handle Exceptions:

Use a try-catch block to handle potential exceptions during the database connection process.
Print the stack trace if an exception occurs.

 */