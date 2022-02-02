package com.earth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="PRODUCER_HISTORY")
public class History {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	private String email;
	private String wasteType;
	private String date;
	private String time;
	private int weight;
	private String status;

	
	@ManyToOne
	private Puser puser;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Puser getPuser() {
		return puser;
	}
	public void setPuser(Puser puser) {
		this.puser = puser;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
}
