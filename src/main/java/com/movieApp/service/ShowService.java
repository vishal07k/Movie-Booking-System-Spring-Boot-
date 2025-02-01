package com.movieApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieApp.convertor.ShowConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.entities.Show;
import com.movieApp.entities.ShowSeat;
import com.movieApp.entities.Theater;
import com.movieApp.entities.TheaterSeat;
import com.movieApp.enums.SeatType;
import com.movieApp.exceptions.MovieDoesNotExists;
import com.movieApp.exceptions.NotNullException;
import com.movieApp.exceptions.ShowDoesNotExists;
import com.movieApp.exceptions.TheaterDoesNotExists;
import com.movieApp.repository.MovieRepository;
import com.movieApp.repository.ShowRepository;
import com.movieApp.repository.ShowSeatsRepository;
import com.movieApp.repository.TheaterRepository;
import com.movieApp.request.ShowRequest;
import com.movieApp.request.ShowSeatRequest;

@Service
public class ShowService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	TheaterRepository theaterRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	public String addShow(ShowRequest showRequest)
	{
		if(showRequest.getMovieId().equals(null) || showRequest.getShowDate().equals(null) || showRequest.getShowStartTime().equals(null)) {
			throw new NotNullException();
		}
		
		Show show = ShowConvertor.showDtoToShow(showRequest);
		
		Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());
		if(movieOpt.isEmpty()) {
			throw new MovieDoesNotExists();
		}
		
		Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());
		if(theaterOpt.isEmpty()) {
			throw new TheaterDoesNotExists();
		}
		
		Theater theater = theaterOpt.get();
		Movie movie = movieOpt.get();
		
		show.setMovie(movie);
		show.setTheater(theater);
		
		show = showRepository.save(show);
		
		movie.getShows().add(show);
		theater.getShowList().add(show);
		
		movieRepository.save(movie);
		theaterRepository.save(theater);
		
		return "Show has been added successfully !";
	}
	
	public String associateShowSeats(ShowSeatRequest showSeatRequest) throws ShowDoesNotExists {
	
		Optional<Show> showOpt = showRepository.findById(showSeatRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExists();
		}

		Show show = showOpt.get();
		Theater theater = show.getTheater();

		List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

		List<ShowSeat> showSeatList = show.getShowSeatList();

		for (TheaterSeat theaterSeat : theaterSeatList) {
			ShowSeat showSeat = new ShowSeat();
			showSeat.setSeatNo(theaterSeat.getSeatNo());
			showSeat.setSeatType(theaterSeat.getSeatType());

			if(showSeat.getSeatType().equals(SeatType.CLASSIC)) {
				showSeat.setPrice((showSeatRequest.getPriceOfClassicSeat()));
			} else {
				showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
			}

			showSeat.setShow(show);
			showSeat.setIsAvailable(Boolean.TRUE);
			showSeat.setIsFoodContains(Boolean.FALSE);

			showSeatList.add(showSeat);
		}

		showRepository.save(show);

		return "Show seats have been associated successfully";
	}
	
	public List<Show> getShowsByDate(){
		
		List<Show> shows = showRepository.findAll();
		
		return shows;
		
	}
	
	public List<Show> getShowsByMovieId(int MovieId){
		
	return showRepository.getAllShowsOfMovie(MovieId);
	}
	
	@Autowired
	ShowSeatsRepository showSeatsRepository;
	
	public List<ShowSeat> getAllSeatsByShow(int showId){
		List<ShowSeat> seats = showSeatsRepository.findAll();
		List<ShowSeat> seatsByShow = new ArrayList<>();
		
		for(ShowSeat s : seats) {
			if(s.getShow().getShowId() == showId) {
				seatsByShow.add(s);
			}
		}
		
		return seatsByShow;
	}
	
	public  int totalPrice = 0;
	
	public List<String> getOriginalName(List<String> reserveSeats){
		
		List<String> list = new ArrayList<>();
		
		List<ShowSeat> seats = showSeatsRepository.findAll();
		
		List<ShowSeat> Original = new ArrayList<>();
		
		
		
		for(String strId : reserveSeats) {
			int id = Integer.parseInt(strId);
			System.out.println(id);
			Optional<ShowSeat> seat = showSeatsRepository.findById(id);
			if(seat.isPresent()) {
				Original.add(seat.get());
				
				totalPrice += seat.get().getPrice();
			}
			else {
				totalPrice = 0;
			}
			
		}
		
		for(ShowSeat seat1 : Original) {
			
			list.add(seat1.getSeatNo());
			
		}
		
		return list;
		
	}
	
	public Show getShowById(int id) {
		return showRepository.findById(id).get();
	}
}
