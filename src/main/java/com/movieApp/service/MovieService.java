package com.movieApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieApp.convertor.MovieConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.entities.MoviePoster;
import com.movieApp.exceptions.MovieAlreadyExist;
import com.movieApp.exceptions.MovieDoesNotExists;
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
	
	
	public String addMovie(MovieRequest movieRequest)
	{
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if(movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		
		return "This movie has been added successfully !";
	}
	
	public List<Movie> getPopularMovies(){
		
		List<Movie> popularMovies = new ArrayList<>();
		
		List<Movie> allMovies = movieRepository.findAll();
		
		for(Movie movie : allMovies) {
			if(movie.getRating() > 8) {
				
				popularMovies.add(movie);
			}
		}
		
		return popularMovies;
 		
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
}
