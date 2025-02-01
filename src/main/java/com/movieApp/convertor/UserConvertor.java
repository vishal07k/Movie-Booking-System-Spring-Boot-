package com.movieApp.convertor;

import com.movieApp.entities.User;
import com.movieApp.request.UserRequest;
import com.movieApp.response.UserResponse;

public class UserConvertor {

	 public static User userDtoToUser(UserRequest userRequest) {
		 User user = new User();
		 user.setName(userRequest.getName());
		 user.setAge(userRequest.getAge());
		 user.setAddress(userRequest.getAddress());
		 user.setGender(userRequest.getGender());
		 user.setMobileNo(userRequest.getMobileNo());
		 user.setEmailId(userRequest.getEmailId());
		 user.setRoles(userRequest.getRoles());
		 user.setPassword(userRequest.getPassword());
		 user.setRoles("USER");
		 
		 return user;
	 }
	 
	 public static UserRequest userToUserRequest(User user1) {
		 
		 UserRequest user = new UserRequest();
		 user.setName(user1.getName());
		 user.setAddress(user1.getAddress());
		 user.setAge(user1.getAge());
		 user.setEmailId(user1.getEmailId());
		 user.setGender(user1.getGender());
		 user.setMobileNo(user1.getMobileNo());
		 user.setPassword(user1.getPassword());
		 user.setRoles(user1.getRoles());
		 
		 return user;
		 
	 }
	 
	 public static UserResponse userToUserDto(User user) {
		 
		 UserResponse userDto = new UserResponse();
		 userDto.setName(user.getName());
		 userDto.setAge(user.getAge());
		 userDto.setAddress(user.getAddress());
		 userDto.setGender(user.getGender());
		 
		 return userDto;
	 }
}
