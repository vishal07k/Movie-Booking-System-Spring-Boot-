package com.movieApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieApp.entities.User;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.TicketRequest;
import com.movieApp.response.TicketResponse;
import com.movieApp.service.TicketService;


@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	UserRepository userRepo;

	@PostMapping("/book")
	public String ticketBooking(@ModelAttribute TicketRequest ticketRequest) {
		User user= null;
		try {
			
			TicketResponse result = ticketService.ticketBooking(ticketRequest);
			System.out.println(ticketRequest);
			System.out.println(ticketRequest.getMovieId());
			//System.out.println(result);
			 user = userRepo.findById(ticketRequest.getUserId()).get();
			
			return "redirect:/userDashbord/" + user.getEmailId() + "?ticketBooked";
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
			return "redirect:/userDashbord/" + user.getEmailId() + "?ticketBookedError";
		}
	}
}
