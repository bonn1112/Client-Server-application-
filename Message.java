package YosukeAkutsuPA04;

//add the class template

import java.io.Serializable;
import java.sql.Date;


public class Message implements Serializable {
	
	private int id;
	private String lastName, firstFName;
	private char mi;
	private String address, city, state; 	
	private int mPhoneNo, hPhoneNo;
	private String mPhoneCarrier, hPhoneCarrier;
	private String msg;
	private int Order;
	
	
	public Message(int id, String lastName, String firstFName, char mi, String address, String city, String state,
			int mPhoneNo, int hPhoneNo, String mPhoneCarrier, String hPhoneCarrier, int order) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstFName = firstFName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.mPhoneNo = mPhoneNo;
		this.hPhoneNo = hPhoneNo;
		this.mPhoneCarrier = mPhoneCarrier;
		this.hPhoneCarrier = hPhoneCarrier;
		this.Order = order;
	}

	public Message(int id){
		this.id = id;
	}	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstFName;
	}


	public void setFirstName(String firstFName) {
		this.firstFName = firstFName;
	}


	public char getMi() {
		return mi;
	}


	public void setMi(char mi) {
		this.mi = mi;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getmPhoneNo() {
		return mPhoneNo;
	}


	public void setmPhoneNo(int mPhoneNo) {
		this.mPhoneNo = mPhoneNo;
	}


	public int gethPhoneNo() {
		return hPhoneNo;
	}


	public void sethPhoneNo(int hPhoneNo) {
		this.hPhoneNo = hPhoneNo;
	}


	public String getmPhoneCarrier() {
		return mPhoneCarrier;
	}


	public void setmPhoneCarrier(String mPhoneCarrier) {
		this.mPhoneCarrier = mPhoneCarrier;
	}


	public String gethPhoneCarrier() {
		return hPhoneCarrier;
	}


	public void sethPhoneCarrier(String hPhoneCarrier) {
		this.hPhoneCarrier = hPhoneCarrier;
	}
	
	// provide the getter and setter methods
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}
		
}
