package YosukeAkutsuPA04;

// add the class template 

import io.github.pixee.security.ObjectInputFilters;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.applet.*;
import javax.swing.*;

import java.sql.*;
import javax.swing.border.*;

public class StaffServer {
 
  private Connection connect = null;
  private Statement stmt = null;
  private Statement stmt2 = null;
  private Statement stmt3 = null;
  private int port;
  private Message msg;
  private PreparedStatement preparedStatement = null;
  private  final int VIEW_OP = 0;
  private final int INSERT_OP = 1;
  private  final int UPDATE_OP = 2;
  private  final int DELETE_OP = 3;
  private ResultSet result1 = null;
  private ResultSet result2 = null;
  private ResultSet result3 = null;
  private ServerSocket serverSocket;
  ObjectOutputStream serverOutputStream;
  ObjectInputStream serverInputStream;
  
  public StaffServer (int port){
	  
	  this.port = port;
	  initializeDB(port);
	  
  }

  
  private void initializeDB(int ListeningPort) {
    
      // create the server
	  try {
		  this.serverSocket = new ServerSocket(ListeningPort);
		  System.out.println("Server port = " + port);
	  
		  Class.forName(com.mysql.jdbc.Driver);
		  System.out.println("JDBC Driver loaded");
	  
	  // Connect to your database using your credentials
		  Connection connect = DriverManager.getConnection("jdbc:mysql://BusCISMySQL01:3306/joyaolanDB", "joyaolan","c611c!44987");
	      System.out.println("Database connected" + " " + port);
		  
      // Create a statement object
	      stmt = connect.createStatement();
	  
	  // loops for ever waiting for the client connection requests
	      while(true) {
	    	  Socket socket = serverSocket.accept(); 
	    	  serverInputStream = new ObjectInputStream(socket.getInputStream());
			  serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
			  
	          // create a thread for each client connection request using Runnable class HandleAClient 
	          HandleAClient task = new HandleAClient(socket);
	          new Thread(task).start();
	         
	        } 
		  }
		  catch (IOException e) {
				e.printStackTrace();
				System.out.println("Port in use.");
	      }
	  	     // create a thread for each client connection request using Runnable class HandleAClient 
  }

 

  /**View record by ID
 * @throws SQLException */
  private void view(Message msg) throws SQLException {
   // Build a SQL SELECT statement
	  stmt = connect.createStatement();
	  result1 = stmt.executeQuery("select * from staff where id = " + msg.getId()+ ";");
	  
	  if(!result1.next()){
			msg.setId(0);
			msg.setMsg("No record found.");
			return;
		}
	  
	  int mPhoneNum, hPhoneNum;
		String lastName, firstName, address, city, state, mCarrier, hCarrier;
		char mi;
		
		lastName = result1.getString(2);
		firstName = result1.getString(3);
		mi = result1.getString(4).charAt(0);
		address = result1.getString(5);
		city = result1.getString(6);
		state = result1.getString(7);
		mPhoneNum = Integer.parseInt(result1.getString(8));
		hPhoneNum = Integer.parseInt(result1.getString(9));
		result1 = stmt.executeQuery("select * from telephone where phone = " + mPhoneNum + ";");
		result1.next();
		mCarrier = result1.getString(2);
		result1 = stmt.executeQuery("select * from telephone where phone = " + hPhoneNum + ";");
		result1.next();
		hCarrier = result1.getString(2);
		msg.setLastName(lastName);
		msg.setFirstName(firstName);
		msg.setMi(mi);
		msg.setAddress(address);
		msg.setCity(city);
		msg.setState(state);
		msg.setmPhoneNo(mPhoneNum);
		msg.setmPhoneCarrier(mCarrier);
		msg.sethPhoneNo(hPhoneNum);
		msg.sethPhoneCarrier(hCarrier);
    
  }

  
  /**Insert a new record
 * @throws SQLException */
  private void insert(Message msg) throws SQLException {
    // Build a SQL INSERT statement
	    //connect 3 server
	    stmt = connect.createStatement();
	  	stmt2 = connect.createStatement();
	  	stmt3 = connect.createStatement();
	  	
	  	//execute the result data
	  	result1 = stmt.executeQuery("select * from Staff Where id = " + msg.getId() + ";");
		result2 = stmt2.executeQuery("select * from Staff Where mobilephone = " + msg.getmPhoneNo() + ";");
		result3 = stmt3.executeQuery("select * from Staff Where homephone = " + msg.gethPhoneNo() + ";");
		 
		 if (result1.next()){ 
			 msg.setId(0);
			 msg.setMsg("ID already exists in the database. Please try agian");
			 return;
		 }
	     else if (result2.next()||result3.next()){
	    	 msg.setId(0);
			 msg.setMsg("Phone number already exists in the database. Please try agian");
			 return;
			 
	     }
	     else {
	    	 
	    	 preparedStatement = connect.prepareStatement("insert into  telephone values (?, ?)");
			 preparedStatement.setInt(1, msg.gethPhoneNo());
			 preparedStatement.setString(2, msg.gethPhoneCarrier());
			 preparedStatement.execute();
			      
			 preparedStatement = connect.prepareStatement("insert into  telephone values (?, ?)");
			 preparedStatement.setInt(1, msg.getmPhoneNo());
			 preparedStatement.setString(2, msg.getmPhoneCarrier());
			 preparedStatement.execute();
			      
			 preparedStatement = connect.prepareStatement("insert into  staff values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			 preparedStatement.setInt(1, msg.getId());
			 preparedStatement.setString(2, msg.getLastName());
			 preparedStatement.setString(3, msg.getFirstName());
			 preparedStatement.setString(4, String.valueOf(msg.getMi()));
			 preparedStatement.setString(5, msg.getAddress());
			 preparedStatement.setString(6, msg.getCity());
			 preparedStatement.setString(7, msg.getState());
			 preparedStatement.setInt(8, msg.getmPhoneNo());
		     preparedStatement.setInt(9, msg.gethPhoneNo());
				      
			 preparedStatement.execute();
			 System.out.println("Insert Sucssess");
			 
			 
			}
	     }
	  

  /**Update a record
 * @throws SQLException */
  private void update(Message msg) throws SQLException {
  // Build a SQL UPDATE statement
	  stmt = connect.createStatement();
	  
	  result1 = stmt.executeQuery("select * from Staff Where id = " + msg.getId() + ";");
	  
	  if (!result1.next()){ 
			msg.setId(0);
			msg.setMsg("ID does not exists in the database. Please try agian");
			return;
		}
		else{
			preparedStatement = connect.prepareStatement("update staff set address = '"+ msg.getAddress() + "' where id = "+ msg.getId() + ";");
			preparedStatement.execute();
		}	
  }

  /**Clear text fields
 * @throws SQLException */
  private void delete(Message msg) throws SQLException {
	// Build a SQL DELETE statement
	  
	  stmt = connect.createStatement();
			result1 = stmt.executeQuery("select * from Staff Where id = " + msg.getId() + ";");
					 
			if (!result1.next()){
				msg.setId(0);
				System.out.println("ID does not exists in the database. \nPlease try agian");
			}
			
			else{
				int mPhoneNum = Integer.parseInt(result1.getString(8));
				int hPhoneNum = Integer.parseInt(result1.getString(9));
				
				preparedStatement = connect.prepareStatement("delete from staff where id=" + msg.getId());
				preparedStatement.execute();
			    preparedStatement = connect.prepareStatement("delete from telephone where phone=" + mPhoneNum);
			    preparedStatement.execute();
			    preparedStatement = connect.prepareStatement("delete from telephone where phone=" + hPhoneNum);
			    preparedStatement.execute();
			}
  }

    // inner Runnable class handle a client connection
	class HandleAClient implements Runnable {
	    private Socket socket; // A connected socket

	    /** Construct a thread */
	    public HandleAClient(Socket socket) {
	      this.socket = socket;
	    }

	    /** Run a thread */
	    public void run() {
	    	try {
	    	// write the code to call a proper method to process the client request
	    	ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
		    ObjectInputFilters.enableObjectFilterIfUnprotected(inputFromClient);
		    ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

	    	
	    	while (true) {
		    	 Message message = null;	
		    	 message = (Message)inputFromClient.readObject();
		    	 switch (message.getOrder()) { 
		    	 	case VIEW_OP: view(message);
		                          break;			            
		            case INSERT_OP: insert(message);
		    				      break;			    				        
		    		case UPDATE_OP: update(message);
		                          break;
		            case DELETE_OP: delete(message);
		                          break; 
		    		}			    			
		            outputToClient.writeObject(message);; 
		    	  }
		       
		      }
		      catch(IOException e) {
		        System.err.println(e);
		      } 
   	 	  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
   	 	  } 
   	 	  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
   	 	  }
		    }
	    }
	   // end of class Runnable 
  
  public static void main(String[] args) {
	  int port = 8000;
	  System.out.println("Server started at port: " + port);
      new StaffServer(port);
    
  }
}
