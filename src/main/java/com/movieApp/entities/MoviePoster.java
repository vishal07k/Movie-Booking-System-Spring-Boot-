package com.movieApp.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class MoviePoster {

	@Id
	private String posterName;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Movie movieId;

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public Movie getMovieId() {
		return movieId;
	}

	public void setMovieId(Movie movieId) {
		this.movieId = movieId;
	}

	public MoviePoster(String posterName, Movie movieId) {
		super();
		this.posterName = posterName;
		this.movieId = movieId;
	}

	public MoviePoster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MoviePoster [posterName=" + posterName + "]";
	}
	
}
