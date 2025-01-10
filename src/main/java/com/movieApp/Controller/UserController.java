package com.movieApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieApp.request.UserRequest;
import com.movieApp.response.HistoryResponse;
import com.movieApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/addNew")
	public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
		try {
			String result = userService.addUser(userEntryDto);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/showHistory/{id}")
	public ResponseEntity<List<HistoryResponse>> showHystory(@PathVariable int id){
		try {
		List<HistoryResponse> list = userService.showHistroy(id);
				
		return new ResponseEntity<>(list, HttpStatus.OK);
		}catch(Exception e) {
			
			List<HistoryResponse> list = new ArrayList<>();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			
		}
	}
}
