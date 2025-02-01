package com.movieApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.request.UserRequest;
import com.movieApp.response.HistoryResponse;
import com.movieApp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/addNew")
	public String addNewUser(@ModelAttribute UserRequest userEntryDto, RedirectAttributes redirectAttributes) {
		try {
			
			System.out.println(userEntryDto);
			String result = userService.addUser(userEntryDto);
			redirectAttributes.addFlashAttribute("message", "User created successfully");
			
			return "redirect:/login?success";
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/signUp";
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
	
	public static final String USER_LOGIN = "User";
	
	@GetMapping("/dashbord")
	public String userdashbord(Model model/* @AuthenticationPrincipal UserDetails details*/) {
		
//		String username = details.getUsername();
		
//		model.addAttribute(USER_LOGIN, username);
		
		return "userdashbord";
		
	}
}
