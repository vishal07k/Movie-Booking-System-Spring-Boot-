package com.movieApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieApp.convertor.MovieConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.exceptions.MovieAlreadyExist;
import com.movieApp.repository.MovieRepository;
import com.movieApp.request.MovieRequest;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
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
}
