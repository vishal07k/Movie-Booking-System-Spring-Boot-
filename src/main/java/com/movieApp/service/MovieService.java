package com.movieApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieApp.convertor.MovieConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.entities.MoviePoster;
import com.movieApp.exceptions.MovieAlreadyExist;
import com.movieApp.exceptions.MovieDoesNotExists;
import com.movieApp.exceptions.MovieIsInactive;
import com.movieApp.repository.MovieRepository;
import com.movieApp.repository.PosterRepository;
import com.movieApp.request.MoviePosterRequest;
import com.movieApp.request.MovieRequest;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private PosterRepository posterRepository;
	
	public List<Movie> getAllMovies(){
		
		List<Movie> movies = movieRepository.findAll();
		
		return movies;
		
	}
	
	
	public Movie addMovie(MovieRequest movieRequest)
	{
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if(movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		
		return movieRepository.save(movie);
	}
	
	
	static List<Movie> popularMovies = new ArrayList<>();
	
	public  String setPopularMovies(String moviName){
		
		
		Movie movie = movieRepository.findByMovieName(moviName);
		
		for(Movie movie1 : popularMovies) {
			if(movie1.getMovieName().equals(movie.getMovieName())) throw new MovieAlreadyExist();
		}
		
		if(movie.isActive()) {
		
			popularMovies.add(movie);
			return "Movie Added to Popular";
		
		}else {
			
			throw new MovieIsInactive();
		}
		
		 		
	}
	
	public List<Movie> getPopularMovies(){
		
		return popularMovies;
 		
	}
	
	@Transactional
	public List<Movie> getLatestMovies(){
		
		List<Movie> latest = new ArrayList<>();
		List<Movie> allMovies = movieRepository.findAll();
		for(Movie movie : allMovies) {
			
			if(movie.isActive()) {
				
				latest.add(movie);
			}
		}
		return latest;
	}
	
	public int addPoster(MoviePosterRequest moviePosterRequest) {
		
		Optional<Movie> optional = movieRepository.findById(moviePosterRequest.getMovieId());
		if(optional.isEmpty()) {
			
			return 0;
		}
		
		Movie movie = optional.get();
		
		MoviePoster moviePoster = MovieConvertor.moviePosterRequestToPoster(moviePosterRequest, movie);
		
		posterRepository.save(moviePoster);
		return 1;
	}
	
	public Optional<Movie> getMoviesById(int movieId){
		
		return movieRepository.findById(movieId);
	}
	
	 public boolean deactivateMovie(int id) {
	        Optional<Movie> optionalMovie = movieRepository.findById(id);
	        if (optionalMovie.isPresent()) {
	            Movie movie = optionalMovie.get();
	            movie.setActive(false);  // Assuming there is an 'active' field in the Movie entity
	            movieRepository.save(movie);
	            return true;
	        }
	        return false;
	    }
}
