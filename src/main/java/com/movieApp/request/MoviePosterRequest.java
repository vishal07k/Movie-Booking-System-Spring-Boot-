package com.movieApp.request;

import org.springframework.web.multipart.MultipartFile;

public class MoviePosterRequest {

	private String posterName;
	
	private int movieId;
	
	private MultipartFile file;
	
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public MoviePosterRequest(String posterName, int movieId) {
		super();
		this.posterName = posterName;
		this.movieId = movieId;
	}

	public MoviePosterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
