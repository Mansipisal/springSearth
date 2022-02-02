package com.earth.entities;

import java.util.*;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Table(name="CUSER")
public class Cuser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	@NotBlank(message="Name field should not be blank !! ")
	@Size(min=2,max=20,message="min 2 and max 20 characters are allowed !!")
	private String name;
	@Column(unique=true)
	@Email(regexp= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Enter valid email id !!")
	private String email;
	private String password;
	private String organizationName;
	@Size(min=10,max=10,message="Enter valid contact no !!")
	private String contact;
	private String role;
	private String address;
	private String wasteType;
	private int weight;
	@Column(length=500)
	@Size(min=0,max=500,message="In about section only 0 to 500 characters are allowed !!")
	private String about;
	private String imgUrl;
	private boolean enabled;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY )
	private List <Chistory> chistory = new ArrayList<>();
	

	public Cuser() {
		super();
	
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<Chistory> getChistory() {
		return chistory;
	}
	public void setChistory(List<Chistory> chistory) {
		this.chistory = chistory;
	}

	@Override
	public String toString() {
		return "Cuser [cId=" + cId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", organizationName=" + organizationName + ", contact=" + contact + ", role=" + role + ", address="
				+ address + ", wasteType=" + wasteType + ", weight=" + weight + ", about=" + about + ", imgUrl="
				+ imgUrl + ", enabled=" + enabled + ", chistory=" + chistory + "]";
	}	
	
	
}
