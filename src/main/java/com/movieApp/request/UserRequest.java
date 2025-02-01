package com.movieApp.request;

import java.util.Collection;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import com.movieApp.enums.Gender;

import lombok.Data;

@Data
public class UserRequest /* implements UserDetails */{

    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
    private String password;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", age=" + age + ", address=" + address + ", mobileNo=" + mobileNo
				+ ", emailId=" + emailId + ", gender=" + gender + ", roles=" + roles + ", password=" + password + "]";
	}
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * 
	 * return null; }
	 * 
	 * @Override public String getUsername() {
	 * 
	 * return emailId; }
	 */
    
}
