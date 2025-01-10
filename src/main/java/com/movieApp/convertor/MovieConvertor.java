package com.movieApp.convertor;

import com.movieApp.entities.Movie;
import com.movieApp.request.MovieRequest;

public class MovieConvertor {
	
	public static Movie movieDtoToMovie(MovieRequest movieRequest) {
		
		Movie movie = new Movie();
		movie.setMovieName(movieRequest.getMovieName());
		movie.setDuration(movieRequest.getDuration());
		movie.setGenre(movieRequest.getGenre());
		movie.setLanguage(movieRequest.getLanguage());
		movie.setReleaseDate(movieRequest.getReleaseDate());
		movie.setRating(movieRequest.getRating());
		
		return movie;
	}

}
