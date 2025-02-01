package com.movieApp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.entities.Movie;
import com.movieApp.entities.MoviePoster;
import com.movieApp.entities.Show;
import com.movieApp.entities.ShowSeat;
import com.movieApp.entities.Theater;
import com.movieApp.entities.Ticket;
import com.movieApp.entities.User;
import com.movieApp.exceptions.UserDoesNotExists;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.TicketRequest;
import com.movieApp.request.UserRequest;
import com.movieApp.response.TicketResponse;
import com.movieApp.service.MovieService;
import com.movieApp.service.ShowService;
import com.movieApp.service.TheaterService;
import com.movieApp.service.TicketService;
import com.movieApp.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@Controller
public class MainController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ShowService showService;
	
	@Autowired
	TheaterService theaterService;
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Movie> movies = movieService.getAllMovies();
		
		model.addAttribute("movieList",movies);
		return "home";
	}
	
	@GetMapping("/signUp")
	public String register(Model model) {
		model.addAttribute("register", "sign up");
		
		return "signup";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("register", "sign up");
		return "loginForm";
	}
	
	@PostMapping("/loginCheck")
	public String loginCheck(@ModelAttribute UserRequest request, Model model,RedirectAttributes redirectAttributes)
	{
		System.out.println(request);
		try {
		
		User user = userService.getUserforLogin(request);
		
		System.out.println(user);
		
		
		if(user != null) {
			
		if(user.getRoles().equals("ADMIN")) {
			
			List<Movie> movies = movieService.getAllMovies();
			
			List<Theater> theaters = theaterService.getAllTheaters();
			
			List<Show> shows = showService.getShowsByDate();
			
			List<Ticket> tickets = ticketService.getAllTickets();
			
			model.addAttribute("Movies", movies);
			
			model.addAttribute("USER", user);
			
			model.addAttribute("Theaters", theaters);
			
			model.addAttribute("Shows", shows);
			
			model.addAttribute("Tickets", tickets);
			
			return "adminDashbord";
		}
		
		if(user.getRoles().equals("USER")) {
			
		model.addAttribute("USER", user);
		
		List<Movie> movies = movieService.getAllMovies();
		
		List<Movie> popularMovies = movieService.getPopularMovies();
		
		model.addAttribute("movieList",movies);
		model.addAttribute("popularMovies", popularMovies);
		
		return "userdashbord";
		
		}
		
		}
		
		redirectAttributes.addFlashAttribute("message", "Please Check Email and Password !");
		return "redirect:/login";
		
		}catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("message", "Please Check Email and Password !");
			return "redirect:/login";
		}
		
	}
	
	@GetMapping("/admin/{emailId}")
	public String adminController(@PathVariable String emailId, Model model) {
		
		User user = userService.getUserByEmailId(emailId);
		
		List<Movie> movies = movieService.getAllMovies();
		 int size = movies.size();
		 
//		 Movie movie = movies.get(size-1);
 		
		model.addAttribute("USER", user);
		
		model.addAttribute("Movies", movies);
		
		return "adminDashbord";
	}
	
	
	
	
	@PostMapping("/booking")
	public String booking(@ModelAttribute TicketRequest ticketRequest, Model model) {
		
		//System.out.println(ticketRequest.getUserId());
		//System.out.println(ticketRequest.getMovieId());
		
		List<Show> shows = showService.getShowsByMovieId(ticketRequest.getMovieId());
		
		Optional<Movie> movieOpt = movieService.getMoviesById(ticketRequest.getMovieId());
		
		model.addAttribute("Movie", movieOpt.get());
		
		model.addAttribute("Ticket", ticketRequest);
		
		model.addAttribute("Shows", shows);
		
		return "Booking";
		
	}
	
	@PostMapping("/selectSeat")
	public String seatBooking(@ModelAttribute TicketRequest ticketRequest, Model model)
	{
		
		//System.out.println(ticketRequest);
		
		//System.out.println(ticketRequest.getShowId());
		
		List<ShowSeat> seats = showService.getAllSeatsByShow(ticketRequest.getShowId());
		
		model.addAttribute("Ticket", ticketRequest);
		
		model.addAttribute("Seats", seats);
		
		return "seats";
	}
	
	@PostMapping("/bookseats")
	public String bookTicket(@ModelAttribute TicketRequest ticketRequest, Model model) {
		
		List<String> requestedSeats = ticketRequest.getRequestSeats();
		
		List<String> originalSeats = showService.getOriginalName(requestedSeats);

		//System.out.println(originalSeats);
		
		ticketRequest.setRequestSeats(originalSeats);
		System.out.println(ticketRequest);
		
		int totalPrice = showService.totalPrice;
		//System.out.println(totalPrice);
		
		User user = userService.getUserById(ticketRequest.getUserId());
		
		Movie movie = movieService.getMoviesById(ticketRequest.getMovieId()).get();
		
		Show show = showService.getShowById(ticketRequest.getShowId());
		
		model.addAttribute("Show", show);
		
		model.addAttribute("Movie", movie);
		
		model.addAttribute("USER", user);
		model.addAttribute("USER", user);
		model.addAttribute("Ticket", ticketRequest);
		
		model.addAttribute("totalPrice", totalPrice);
		
		return "BookTicket";
	}
	
	@GetMapping("/userDashbord/{emailId}")
	public String bookingSuccessfull(@PathVariable String emailId, Model model) {
		
		User user = userService.getUserByEmailId(emailId);
		model.addAttribute("USER", user);
		List<Movie> movies = movieService.getAllMovies();
		
		List<Movie> popularMovies = movieService.getPopularMovies();
		
		model.addAttribute("movieList",movies);
		model.addAttribute("popularMovies", popularMovies);
		
		return "userdashbord";
	}

}
