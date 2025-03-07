package com.movieApp.convertor;

import com.movieApp.entities.Movie;
import com.movieApp.entities.MoviePoster;
import com.movieApp.request.MoviePosterRequest;
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
		movie.setActive(true);
		
		return movie;
	}

	public static MoviePoster moviePosterRequestToPoster(MoviePosterRequest moviePosterRequest, Movie movie) {
		
		MoviePoster moviePoster = new MoviePoster();
		moviePoster.setPosterName(moviePosterRequest.getPosterName());
		moviePoster.setMovieId(movie);
		
		return moviePoster;
		
	}
	
	public static MovieRequest movieToMovieRequest(Movie movieRequest) {
		MovieRequest movie = new MovieRequest();
		movie.setMovieName(movieRequest.getMovieName());
		movie.setDuration(movieRequest.getDuration());
		movie.setGenre(movieRequest.getGenre());
		movie.setLanguage(movieRequest.getLanguage());
		movie.setReleaseDate(movieRequest.getReleaseDate());
		movie.setRating(movieRequest.getRating());
		movie.setId(movieRequest.getId());
		
		return movie;
	}
}
