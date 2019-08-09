package com.mongodb;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="CharliesCollection")
public class Bean {

	public Bean() {
		
	}
	public Bean(String name, String address, String mobile, List<String> pets) {
		super();
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.pets = pets;
	}
    @Id
    public String id;

	private String name;
	private String address;
	private String mobile;
	private List<String> pets;

	public void add(Bean pet){
		this.add(pet);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getPets() {
		return pets;
	}

	public void setPets(List<String> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "Bean [name=" + name + ", address=" + address + ", mobile=" + mobile + ", pets=" + pets + "]";
	}
	public String get_id() {
		return id;
	}
	public void set_id(String _id) {
		this.id = _id;
	}
}
