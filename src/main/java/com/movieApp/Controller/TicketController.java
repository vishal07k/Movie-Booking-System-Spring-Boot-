package com.movieApp.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
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

import com.movieApp.entities.Movie;
import com.movieApp.entities.User;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.TicketRequest;
import com.movieApp.response.TicketResponse;
import com.movieApp.service.MovieService;
import com.movieApp.service.TicketService;
import com.razorpay.*;


@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MovieService movieService;

	@PostMapping("/book")
	public String ticketBooking(@ModelAttribute TicketRequest ticketRequest, Model model) {
		User user= null;
		try {
			
			TicketResponse result = ticketService.ticketBooking(ticketRequest);
			System.out.println(ticketRequest);
			System.out.println(ticketRequest.getMovieId());
			//System.out.println(result);
			 user = userRepo.findById(ticketRequest.getUserId()).get();
			 List<Movie> movies = movieService.getAllMovies();
				
				List<Movie> popularMovies = movieService.getPopularMovies();
				
				model.addAttribute("movieList",movies);
				model.addAttribute("popularMovies", popularMovies);
			
			return "redirect:/userDashbord/" + user.getEmailId() + "?ticketBooked";
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
			return "redirect:/userDashbord/" + user.getEmailId() + "?ticketBookedError";
		}
	}
	
	@PostMapping("/payment")
	public String paymentApi(@RequestBody Map<String, Object> data) throws Exception{
		
		 System.out.println("Received Payment Request: " + data);
	        
	        // ✅ Check if amount exists and is a valid number
	        if (!data.containsKey("amount")) {
	            return "Amount is required";
	        }

	        // ✅ Convert amount safely
	        int amount = 0;
	        try {
	            amount = Integer.parseInt(data.get("amount").toString());
	        } catch (NumberFormatException e) {
	            return "Invalid amount format";
	        }

	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "created");
	        response.put("amount", amount);
	        response.put("id", "order_test_12345");
				
		return response.toString();
	}
}
