package com.movieApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.entities.User;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.MovieRequest;
import com.movieApp.request.UserRequest;
import com.movieApp.service.MovieService;
import com.movieApp.service.UserService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	

	@PostMapping("/addNew")
	public String addMovie(@ModelAttribute MovieRequest movieRequest, Model model){
		
		try {
			//System.out.println(userRequest);
			System.out.println(movieRequest);
			System.out.println(movieRequest.getAdminEmail());
			
			String result = movieService.addMovie(movieRequest);
			//return new ResponseEntity<String>(result,HttpStatus.CREATED);
			
			//User user = userRepository.findByEmailId(movieRequest.getAdminEmail()).get();
			
			//model.addAttribute("USER", user);
			
			//redirectAttributes.addFlashAttribute("message", "Movie added Successfully !");
			
			//return "adminDashbord";
			
			return "redirect:/admin/"+movieRequest.getAdminEmail()+"?success";
			
		}catch(Exception e) {
			//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			return "redirect:/admin/"+movieRequest.getAdminEmail()+"?error";
		}
		
		
	} 
	
	
}
