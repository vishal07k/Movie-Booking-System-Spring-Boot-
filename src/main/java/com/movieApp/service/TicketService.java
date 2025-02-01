package com.movieApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.movieApp.convertor.TicketConvertor;
import com.movieApp.entities.Show;
import com.movieApp.entities.ShowSeat;
import com.movieApp.entities.Ticket;
import com.movieApp.entities.User;
import com.movieApp.exceptions.EmailNotSendException;
import com.movieApp.exceptions.SeatsNotAvailable;
import com.movieApp.exceptions.ShowDoesNotExists;
import com.movieApp.exceptions.UserDoesNotExists;
import com.movieApp.repository.ShowRepository;
import com.movieApp.repository.TicketRepository;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.TicketRequest;
import com.movieApp.response.TicketResponse;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private UserRepository userRepository;

	public TicketResponse ticketBooking(TicketRequest ticketRequest) {
		
		Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExists();
		}

		Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

		if (userOpt.isEmpty()) {
			throw new UserDoesNotExists();
		}

		User user = userOpt.get();
		Show show = showOpt.get();

		Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestSeats());
		

		if (!isSeatAvailable) {
			
			throw new SeatsNotAvailable();
		}

		// count price
		Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(),	ticketRequest.getRequestSeats());

		String seats = listToString(ticketRequest.getRequestSeats());

		Ticket ticket = new Ticket();
		ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
		ticket.setBookedSeats(seats);
		ticket.setUser(user);
		ticket.setShow(show);

		ticket = ticketRepository.save(ticket);

		user.getTicketList().add(ticket);
		show.getTicketList().add(ticket);
		userRepository.save(user);
		showRepository.save(show);
		
		
		int result = sendMail(user, ticket, show);
		
		if(result!=1)
		{
			throw new EmailNotSendException();
		}
		
		return TicketConvertor.returnTicket(show, ticket);
	}

	private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
		
		for (ShowSeat showSeat : showSeatList) {
			String seatNo = showSeat.getSeatNo();

			if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
				return false;
			}
		}

		return true;
	}

	private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
		Integer totalAmount = 0;

		for (ShowSeat showSeat : showSeatList) {
			for(String seat : requestSeats) {
			if (seat.contains(showSeat.getSeatNo())) {
				System.out.println("Running.................................................................");
				totalAmount += showSeat.getPrice();
				showSeat.setIsAvailable(Boolean.FALSE);
			}
			}
		}

		return totalAmount;
	}

	private String listToString(List<String> requestSeats) {
		StringBuilder sb = new StringBuilder();

		for (String s : requestSeats) {
			sb.append(s).append(",");
		}

		return sb.toString();
	}
	
	@Value("${spring.mail.username}")
	String fromMail;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	private int sendMail(User user, Ticket ticket, Show show) {
		
		String recipient = user.getEmailId();
		
		String subject = "Ticket Booked Successfully : Movie("+show.getMovie().getMovieName()+")";
		
		String body = "Dear user,"
				+ "\n\n"
				+ "You have successfully booked movie "+show.getMovie().getMovieName()+" Details are as follows :\n"
						+ "Theater : "+show.getTheater().getName()+"\n"
								+ "Booked Date : "+ticket.getBookedAt()+"\n"
								+ "Show Time : "+show.getTime()+"\n"
										+ "Reserved Seats : "+ticket.getBookedSeats()+"\n"
												+ "Total payment : "+ticket.getTotalTicketsPrice()+"\n"
														+ "\n\n"
														+ "Thanks & Regards"
														+ "\n"
														+ "Vishal Cinema";
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setTo(recipient);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		javaMailSender.send(simpleMailMessage);
		
		return 1;
		
	}
	
	public List<Ticket> getAllTickets() {
		
		List<Ticket>  tickets = ticketRepository.findAll();
 		
		return tickets;
	}

}
