package com.earth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONSUMER_HISTORY")
public class Chistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String email;
	private String wasteType;
	private int weight;
	private String date;
	private String contact;
	private int amount;
	private String address;
	private String orderDate;
	private String Role;

	
	@ManyToOne
	private Cuser Cuser;

	public int getcId() {
		return cId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getWasteType() {
		return wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Cuser getCuser() {
		return Cuser;
	}

	public void setCuser(Cuser cuser) {
		Cuser = cuser;
	}

	@Override
	public String toString() {
		return "Chistory [cId=" + cId + ", email=" + email + ", wasteType=" + wasteType + ", weight=" + weight
				+ ", date=" + date + ", contact=" + contact + ", amount=" + amount + ", address=" + address
				+ ", orderDate=" + orderDate + ", Role=" + Role + ", Cuser=" + Cuser + "]";
	}

	
	
	
}
