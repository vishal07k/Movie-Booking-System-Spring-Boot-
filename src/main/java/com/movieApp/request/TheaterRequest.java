package com.movieApp.request;

import lombok.Data;

@Data
public class TheaterRequest {

    private String name;
    private String address;
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
	@Override
	public String toString() {
		return "TheaterRequest [name=" + name + ", address=" + address + "]";
	}
    
    
}
