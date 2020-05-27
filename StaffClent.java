package YosukeAkutsuPA04;

//add the class template

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

class StaffClent extends JFrame implements ActionListener{


	private String hostname;
	private int port;
	private Message msg;
	private  final int VIEW_OP = 0;
	private final int INSERT_OP = 1;
	private  final int UPDATE_OP = 2;
	private  final int DELETE_OP = 3;
	private Socket clientSocket;
	ObjectOutputStream clientOutputStream;
	ObjectInputStream clientInputStream;
	

// declare UI component objects
	//label

	private JLabel lblID;
	private JLabel lblLName;
	private JLabel lblFName;
	private JLabel lblMI;
	private JLabel lblAddress;
	private JLabel lblCity;
    private JLabel lblState;
    private JLabel lblMobileNum;
    private JLabel lblMobileCarrier;
    private JLabel lblHomeNum;
    private JLabel lblHomeCarrier;
    private JLabel lblDatabase;
    
    //textField
    private JTextField txtID;
    private JTextField txtLName;
    private JTextField txtFName;
    private JTextField txtMI;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtMobileNum;
    private JTextField txtHomeNum;
    
    //ComboBox
    private JComboBox cbbState;
    private JComboBox cbbMobileCarrier;
    private JComboBox cbbHomeCarrier;
    
    //Button
    private JButton viewButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton closeButton; 
    
	
	
public StaffClent(String hostname, int port) throws IOException {
	
	this.port = port;
	this.hostname = hostname;
	System.out.println("hostname" + " " + port);
	
	// Create a connection with the StaffServer server on port number 8000
	clientSocket = new Socket(hostname, port);
	clientOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
	clientInputStream = new ObjectInputStream(clientSocket.getInputStream());

	
	// call these two methods to create user GUI
	initComponenet();
	doTheLayout();
	
	viewButton.addActionListener(this);
    insertButton.addActionListener(this);
    updateButton.addActionListener(this);
    deleteButton.addActionListener(this);
    clearButton.addActionListener(this);
    closeButton.addActionListener(this);
	
	
}

private void initComponenet(){
	// Initialize the GUI components
	//label
	lblID = new JLabel("ID: ");
	lblLName = new JLabel("Last Name: ");
	lblFName = new JLabel("First Name");
	lblMI = new JLabel("MI");
	lblAddress = new JLabel("Address: ");
	lblCity = new JLabel("City");
	lblState = new JLabel("State: ");
    lblMobileNum = new JLabel("Mobile Phone Number: ");
    lblMobileCarrier = new JLabel("Mobile Phone Carrier: ");
    lblHomeNum = new JLabel("Home Phone Number: ");
    lblHomeCarrier = new JLabel("Home Phone Carrier: ");
    lblDatabase = new JLabel("Database Connected");
	
  //TextField
    txtID = new JTextField(6);
    txtLName = new JTextField(15);
    txtFName = new JTextField(15);
    txtMI = new JTextField(1);
    txtAddress = new JTextField(20);
    txtCity = new JTextField(20);
    txtMobileNum = new JTextField(11);
    txtHomeNum = new JTextField(11);
    
  //ComboBox
    String[] listState = {
			  "AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
			  "HI","ID","IN","IL","IA","KS","KY","LA","ME","MD",
			  "MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
			  "NM","NY","NC","ND","OH","OK","OR","PA","RI","SC",
			  "SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"
			  };
    cbbState = new JComboBox(listState);
    String[] listMobileCarrier = {
    		"Verizon", "T Mobile", "AT&T", "Sprint"
    };
    cbbMobileCarrier = new JComboBox(listMobileCarrier);
    String[] listHomeCarrier = {
    		"Verizon", "T Mobile", "AT&T", "Sprint"
    };
    cbbHomeCarrier = new JComboBox(listHomeCarrier);
    
  //Button
    viewButton = new JButton("View");
    insertButton = new JButton("Insert");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    clearButton = new JButton("Clear");
    closeButton = new JButton("Close");   
    
    
}

private void doTheLayout(){
	// Arrange the UI components into GUI window
	JPanel top = new JPanel(new GridLayout(0,1)); 
	//create beveled border with a lowered edge, using brighter shade 
	Border loweredbevel, Compound;
	TitledBorder title;
    int eb =10;
	//put "staff information" above the border
    loweredbevel= BorderFactory.createLoweredBevelBorder();
	title = BorderFactory.createTitledBorder(loweredbevel, "Staff Information");
	title.setTitlePosition(TitledBorder.ABOVE_TOP);
	//create compound border specifying the border objects to use for the outside and inside
	Compound = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb) 
			,new TitledBorder(title));
	
	top.setBorder(Compound);
	
	//creates a flow layout with the given alignment and default 5 horizontal and vertical gap
	JPanel top1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel top3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel top4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel top5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel top6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	JPanel lower = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    top1.add(lblID);
    top1.add(txtID);
    
    top2.add(lblLName);
    top2.add(txtLName);
    top2.add(lblFName);
    top2.add(txtFName);
    top2.add(lblMI);
    top2.add(txtMI);
    
    top3.add(lblAddress);
    top3.add(txtAddress);
    
    top4.add(lblCity);
    top4.add(txtCity);
    top4.add(lblState);
    top4.add(cbbState);
    
    top5.add(lblMobileNum);
    top5.add(txtMobileNum);
    top5.add(lblMobileCarrier);
    top5.add(cbbMobileCarrier);
    
    top6.add(lblHomeNum);
    top6.add(txtHomeNum);
    top6.add(lblHomeCarrier);
    top6.add(cbbHomeCarrier);
    
    lower.add(viewButton);
    lower.add(insertButton);
    lower.add(updateButton);
    lower.add(deleteButton);
    lower.add(clearButton);
    lower.add(closeButton);
    
    bottom.add(lblDatabase);
    
    setLayout( new BorderLayout());
    top.add(top1);
    top.add(top2);
    top.add(top3);
    top.add(top4);
    top.add(top5);
    top.add(top6);
    this.add(top, "North");
    this.add(lower, "Center");
    this.add(bottom, "South");
	
}
	
//handle exception
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == viewButton)
		try {
				viewButtonClicked();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	else if(e.getSource() == insertButton)
		try {
				insertButtonClicked();
				
		} catch (ClassNotFoundException | IOException e3) {
			// TODO: handle exception
			e3.printStackTrace();
		}
	else if (e.getSource() == updateButton)
		try {
			updateButtonClicked();
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	else if (e.getSource() == deleteButton)
		try {
			deleteButtonClicked();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	else if (e.getSource() == clearButton)
		try {
			clearButtonClicked();
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	else if (e.getSource() == closeButton)
		try {
			closeButtonClicked();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}

private void viewButtonClicked() throws ClassNotFoundException, IOException{
	  
	  // handle view button clicked event
	int id = 0;
	String idString = txtID.getText().trim();
	try{
		if (idString.isEmpty()||idString.matches("^\\s*$")){
			JOptionPane.showMessageDialog(null, "Please enter Valide ID.");
			throw new Exception();
		}
		else
		id = Integer.parseInt(idString);
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtID,"ID should be a number!");
		txtID.setText("");
		return;
	}	
	msg = new Message(id);
	sendMessage();
		
  }

private void insertButtonClicked() throws ClassNotFoundException, IOException{
	  
	  // handle insert button clicked event 
	int id = 0, mPhoneNo = 0, hPhoneNo = 0;
	char mi;
	String idString = txtID.getText().trim();
	String lastName = txtLName.getText().trim();
	String firstName = txtFName.getText().trim();
	String miStr = txtMI.getText().trim();
	String address = txtAddress.getText().trim();
	String city = txtCity.getText().trim();
	String mNumStr = txtMobileNum.getText().trim();
	//String mPhoneCarrier = field9.getText().trim();
	String hNumStr = txtHomeNum.getText().trim();
	//String hPhoneCarrier = field11.getText().trim();	
	String state = cbbState.getSelectedItem().toString();
	String mCarrier = cbbMobileCarrier.getSelectedItem().toString();
	String hCarrier = cbbHomeCarrier.getSelectedItem().toString();	
	// validation,  can not be empty.
	if (idString.isEmpty()||lastName.isEmpty() || firstName.isEmpty()||miStr.isEmpty() 
		||address.isEmpty() || city.isEmpty()||state.isEmpty()||mNumStr.isEmpty()
		||mCarrier.isEmpty() ||hNumStr.isEmpty()||hCarrier.isEmpty()) {
	    JOptionPane.showMessageDialog(null, "Please enter all required fields.");
	    return;
	}  
	//validate ID , should be number, can not be all space .
	try{
		if(idString.matches("^\\s*$")){
			JOptionPane.showMessageDialog(txtID,"ID cannot be space");
			txtID.setText("");
			throw new Exception();
		}		
		else
		id=Integer.parseInt(idString);
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtID,"ID should be a number!");
		txtID.setText("");
		return;
	}
	    
	// validate last name, can not be all space.
	if(lastName.length() > 15){
		JOptionPane.showMessageDialog(txtLName,"Last name should be no more than 15 letters");
		txtLName.setText("");
		return;
	}
	else if (lastName.matches("^\\s*$")){
		JOptionPane.showMessageDialog(txtLName,"Last name cannot be space");
		txtLName.setText("");
		return;
	}
	// validate first name, can not be all space.
	if(firstName.length() > 15){
		JOptionPane.showMessageDialog(txtFName,"First name should be no more than 15 digits");
		txtFName.setText("");
		return;			
	}
	else if (firstName.matches("^\\s*$")){
		JOptionPane.showMessageDialog(txtFName,"First name cannot be space");
		txtFName.setText("");
		return;
	}
	// validate only one middle initial 
	if(miStr.length() > 1){
		JOptionPane.showMessageDialog(txtMI,"The length of mi should be only 1 character");
		txtMI.setText("");
		return;
	}
	mi = txtMI.getText().charAt(0);
	//	validate only 20 characters long 
	if(address.length() > 20){
		JOptionPane.showMessageDialog(txtAddress,"Address should be no more than 20 characters");
		txtAddress.setText("");
		return;
	}		
	//
	if(city.length() > 20){
		JOptionPane.showMessageDialog(txtCity,"City should be no more than 20 characters");
		txtCity.setText("");
		return;
	}
	
	//Mobile phone	validate only 10 characters 
	try{
		mPhoneNo = Integer.parseInt(mNumStr);
		if(mNumStr.length()!= 10)
			throw new Exception();		
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtMobileNum,"Please input only 10 digit numbers for mobile number.");
		txtMobileNum.setText("");
		return;
	}
	//Home phone  validate only 10 characters
	try{
		hPhoneNo = Integer.parseInt(hNumStr);
		if(hNumStr.length()!= 10)
			throw new Exception();		
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtHomeNum,"Home number must be only 10 digit numbers.");
		txtHomeNum.setText("");
		return;
	}
	// Mobile carrier invalidate space and empty 
	if(mCarrier.matches("^\\s*$")){
	    JOptionPane.showMessageDialog(null,"Mobile Carrier cannot be all spaces");
		return;
	}
	else if (mCarrier.length()>20) {
		JOptionPane.showMessageDialog(null,"The length of city should be no more than 20 characters");
		return;
	   }
		
	// Home phone carrier invalidate space and empty
	if(hCarrier.matches("^\\s*$")){
		JOptionPane.showMessageDialog(null,"Mobile Carrier cannot be all spaces");
		return;
	}
	else if (hCarrier.length()>20) {
		JOptionPane.showMessageDialog(null,"The length of city should be no more than 20 characters");
		return;
	}
	msg = new Message(id,lastName,firstName,mi,address,city, state,
			mPhoneNo, hPhoneNo, mCarrier, hCarrier, INSERT_OP);	
	sendMessage();
	
  }


private void updateButtonClicked() throws ClassNotFoundException, IOException{
	
	// handle update button clicked event
	int id = 0, mPhoneNo = 0, hPhoneNo = 0;
	char mi;
	String idString = txtID.getText().trim();
	String address = txtAddress.getText().trim();
	
	// invalidate empty or space for ID
	try{
		if (idString.isEmpty()||idString.matches("^\\s*$")){
			JOptionPane.showMessageDialog(null, "Please enter Valide ID.");
			throw new Exception();
		}
		else
		id = Integer.parseInt(idString);
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtID,"ID should be a number!");
		txtID.setText("");
		return;
	}	
	    
	if(address.length() > 20){
		JOptionPane.showMessageDialog(null,"The length of address should be no more than 20 characters");
		txtAddress.setText("");
		return;
	}
		
	// just update address.
	msg = new Message(id);
	msg.setOrder(UPDATE_OP);
	msg.setAddress(address);
	sendMessage();	
  }

private void deleteButtonClicked() throws ClassNotFoundException, IOException{
	  
	// handle delete button clicked event
	int id = 0;
	String idString = txtID.getText().trim();
	try{
		if (idString.isEmpty()||idString.matches("^\\s*$")){
			JOptionPane.showMessageDialog(null, "Please enter Valide ID.");
			throw new Exception();
		}
		else
		id = Integer.parseInt(idString);
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(txtID,"ID should be a number!");
		txtID.setText("");
		return;
	}	
	
	msg = new Message(id);
	msg.setOrder(DELETE_OP);
	sendMessage();
	
  }
  
  
  void clearButtonClicked() throws ClassNotFoundException, IOException{
	  
	// handle clear button clicked event
	  txtID.setText("");
		txtLName.setText("");
		txtFName.setText("");
		txtMI.setText("");
		txtAddress.setText("");
		txtCity.setText("");
		txtMobileNum .setText("");
	    txtHomeNum.setText("");
	    cbbState.setSelectedItem(null);
	    cbbMobileCarrier.setSelectedItem(null);;
	    cbbHomeCarrier.setSelectedItem(null);;
	  
  }
  

  void closeButtonClicked() throws IOException{
	  
	// handle close button clicked event
	  clientSocket.close();
		clientOutputStream.close();
		clientInputStream.close();
			
		System.exit(0);
    
  }
  
  public void sendMessage() throws IOException, ClassNotFoundException {	
		
		clientOutputStream.writeObject(msg);
		receivingMessage();
			
	  }
	  
	  private void receivingMessage() throws IOException, ClassNotFoundException {
	  	
		  msg = (Message)clientInputStream.readObject();
			
		  switch(msg.getOrder()){
		  	case 0:
		  		if(msg.getId()!=0) {
		  			txtID.setText(String.valueOf(msg.getId()));
		  			txtLName.setText(msg.getLastName());
		  			txtFName.setText(msg.getFirstName());
		  			txtMI.setText(String.valueOf(msg.getMi()));
		  			txtAddress.setText(msg.getAddress());
		  			txtCity.setText(msg.getCity());
		  			cbbState.setSelectedItem(msg.getState());
		  			txtMobileNum.setText(String.valueOf(msg.getmPhoneNo()));
		  			cbbMobileCarrier.setSelectedItem(msg.getmPhoneCarrier());
		  			txtHomeNum.setText(String.valueOf(msg.gethPhoneNo()));
		  			cbbHomeCarrier.setSelectedItem(msg.gethPhoneCarrier());
		  			
		  		}
		  		else
		  			JOptionPane.showMessageDialog(null,msg.getMsg());
		  		break;
		  	case 1:
		  		if(msg.getId()!=0)
		  			JOptionPane.showMessageDialog(null,"Insert succeed!");
		  		else
		  			JOptionPane.showMessageDialog(null,"Insert fail!\n" + msg.getMsg());
		  		break;
		  	case 2:
		  		if(msg.getId()!=0)
		  			JOptionPane.showMessageDialog(null,"Update succeed!");
		  		else
		  			JOptionPane.showMessageDialog(null,"Update fail!\n" + msg.getMsg());
		  		break;
		  	case 3:
		  		if(msg.getId()!=0)
		  			JOptionPane.showMessageDialog(null,"Delete succeed!");
		  		else
		  			JOptionPane.showMessageDialog(null,"Delete fail!" + msg.getMsg());
		  		break;
		  }		
	  } 
  
  /**Main method*/
  public static void main(String[] args) {
	  
	  // create the user GUI
	  JFrame StaffGui = null;
	  try {
		StaffGui = new StaffClent("localhost",8000);
	  } 
	  catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Cannot connected to Server. Please try agian");
        System.exit(0);
	  } 
	  catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Can't connected to Server. Please try agian");
        System.exit(0);
	  }  
	  StaffGui.setTitle("Staff Table GUI");
	  StaffGui.pack();
	  StaffGui.setLocationRelativeTo(null); // Center the frame
	  StaffGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  StaffGui.setVisible(true);
  }
}