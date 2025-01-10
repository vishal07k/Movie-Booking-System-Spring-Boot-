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
