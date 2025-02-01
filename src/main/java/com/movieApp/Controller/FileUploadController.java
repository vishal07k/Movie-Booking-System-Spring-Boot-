package com.movieApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movieApp.FileUploaderHelper.FileUploadHelper;
import com.movieApp.convertor.MovieConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.exceptions.MovieDoesNotExists;
import com.movieApp.request.MoviePosterRequest;
import com.movieApp.request.MovieRequest;
import com.movieApp.service.MovieService;

@Controller
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@Autowired
	private MovieService movieService;

	@PostMapping("/file/upload/")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// validation

			// System.out.println(movieId);
			//System.out.println(file.getOriginalFilename());

			
			  if(file.isEmpty()) { //return
				  
			  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
			  body(" Request must contain file "); 
			  
			  return "index"; 
			  
			  }
			 
			// check type
			
			  if(!file.getContentType().equals("image/jpeg")) { //return
				  
			  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
			  body(" only Jpg allowed"+file.getContentType()); 
			  
			  return "index"; 
			  
			  }
			

			// file upload code

			List<Movie> movies = movieService.getAllMovies();

			MovieRequest movie = new MovieRequest();

			int count = 0;

			for (Movie movie1 : movies) {

				if (count == movies.size() - 1) {

					movie = MovieConvertor.movieToMovieRequest(movie1);
				}
				count++;
			}
			System.out.println(movie);
			
			 int status = 1;   
			 
			 fileUploadHelper.uploadFile(file, movie.getId());
			 
			 if(status != 1) {
			 
			 throw new MovieDoesNotExists(); 
			 
			 }
			 
			 System.out.println("File uploaded successfully");
			 //return ResponseEntity.ok("File Uploaded Successfully");

			 return "redirect:/admin/vishalkhamkar2003@gmail.com?filesuccess";

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?fileerror";
		}

	
		// return
		// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went
		// wrong");
	}

}
