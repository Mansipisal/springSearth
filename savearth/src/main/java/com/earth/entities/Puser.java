package com.earth.entities;

import java.util.*;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Table(name="PUSER")
public class Puser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message="Name field should not be blank !! ")
	@Size(min=2,max=20,message="min 2 and max 20 characters are allowed !!")
	private String name;
	private String password;
	@Column(unique=true)
	@Email(regexp= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Enter valid email id !!")
	private String email;
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
	private List <History> history = new ArrayList<>();

	public Puser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Puser [id=" + id + ", name=" + name + ", password=" + ", email=" + email + ", contact="
				+ contact + ", role=" + role + ", address=" + address + ", wasteType=" + wasteType + ", weight="
				+ weight + ", about=" + about + ", imgUrl=" + imgUrl + ", enabled=" + enabled + ", history=" + history
				+ "]";
	}

	
	

	
	
	

}
