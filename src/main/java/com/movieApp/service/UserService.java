package com.movieApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movieApp.convertor.TicketConvertor;
import com.movieApp.convertor.UserConvertor;
import com.movieApp.entities.Ticket;
import com.movieApp.entities.User;
import com.movieApp.exceptions.NotNullException;
import com.movieApp.exceptions.UserDoesNotExists;
import com.movieApp.exceptions.UserExist;
import com.movieApp.repository.TicketRepository;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.UserRequest;
import com.movieApp.response.HistoryResponse;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketRepository ticketRepository;
	
	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */
	
	public String addUser(UserRequest userRequest) {
		
		/*
		 * userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		 */
		
		Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());
		
		if (users.isPresent()) {
			throw new UserExist();
		}
		
				
		User user = UserConvertor.userDtoToUser(userRequest);
		
		if(user.getName().equals(null) || user.getName().isEmpty()) {
			throw new NotNullException();
		}
		
		if(user.getMobileNo().isEmpty() || user.getMobileNo().equals(null))
		{
			throw new NotNullException();
		}
						
		userRepository.save(user);
		return "User Saved Successfully";
	}
	
	public User getUserforLogin(UserRequest request) {
		
		Optional<User> users = userRepository.findByEmailId(request.getEmailId());
		
		if(users.isEmpty()) {
			
			throw new UserDoesNotExists();
		}
		
		
		User user = users.get();
		
		if(user.getPassword().equals(request.getPassword())) {
		
		return user;
		
		}
		
		return null;
	}
	
	public List<HistoryResponse> showHistroy(int id){
		
		List<Ticket> history = ticketRepository.findAll();
		
		ArrayList<HistoryResponse> list = new ArrayList<>();
		
		for(Ticket ticket : history) {
			if(ticket.getUser().getId() == id) {
				list.add(TicketConvertor.returnHistory(ticket));
			}
		}
		
		if(list.isEmpty()) {
			throw new UserDoesNotExists();
		}
		
		return list;
	}
	
	public User getUserByEmailId(String emailId) {
		
		User user = userRepository.findByEmailId(emailId).get();
		
		
		return user;
		
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}
}
