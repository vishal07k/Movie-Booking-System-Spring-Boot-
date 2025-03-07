package com.movieApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.FileUploaderHelper.FileUploadHelper;
import com.movieApp.convertor.MovieConvertor;
import com.movieApp.entities.Movie;
import com.movieApp.entities.User;
import com.movieApp.exceptions.MovieDoesNotExists;
import com.movieApp.repository.MovieRepository;
import com.movieApp.repository.UserRepository;
import com.movieApp.request.MovieRequest;
import com.movieApp.request.UserRequest;
import com.movieApp.service.MovieService;
import com.movieApp.service.UserService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	

	@PostMapping("/addNew")
	public String addMovie(@ModelAttribute MovieRequest movieRequest,@RequestParam("file") MultipartFile file, Model model){
		
		try {
			//System.out.println(userRequest);
			System.out.println(movieRequest);
			System.out.println(movieRequest.getAdminEmail());
			System.out.println(file.getOriginalFilename());
			
			
			
				// validation

				// System.out.println(movieId);
				//System.out.println(file.getOriginalFilename());

				
				  if(file.isEmpty()) { //return
					  
				  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
				  body(" Request must contain file "); 
				  
				  return "redirect:/admin/"+movieRequest.getAdminEmail() + "?fileerror"; 
				  
				  }
				 
				// check type
				
				  if(!file.getContentType().equals("image/jpeg")) { //return
					  
				  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
				  body(" only Jpg allowed"+file.getContentType()); 
				  
				 return "redirect:/admin/"+movieRequest.getAdminEmail() + "?fileerror";
				  
				  }
				

				// file upload code

				 Movie result = movieService.addMovie(movieRequest);
				 int status = 1;   
				 
				 fileUploadHelper.uploadFile(file, result.getId());
				 
				 if(status != 1) {
				 
				 throw new MovieDoesNotExists(); 
				 
				 }
				 
				 System.out.println("File uploaded successfully");
				 //return ResponseEntity.ok("File Uploaded Successfully");

			
			return "redirect:/admin/"+movieRequest.getAdminEmail()+"?success";
			
		}catch(Exception e) {
			//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			return "redirect:/admin/"+movieRequest.getAdminEmail()+"?error";
		}
		
		
	} 
	
	 @PutMapping("/{id}/deactivate")
	    public ResponseEntity<String> deactivateMovie(@PathVariable int id) {
	        boolean isDeactivated = movieService.deactivateMovie(id);
	        if (isDeactivated) {
	            return ResponseEntity.ok("Movie deactivated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
	        }
	    }
	 
	 
	 
	 @GetMapping("/addPopularMovie/{email}")
	 public String addMovie(@PathVariable String email, @RequestParam String movieName, RedirectAttributes redirectAttributes) {
		 try {
	    String message = movieService.setPopularMovies(movieName);
	     
	     redirectAttributes.addFlashAttribute("message", message);
	     return "redirect:/admin/" + email + "?popularSuccess";
		 }catch(Exception e) {
			 redirectAttributes.addFlashAttribute("message", e.getMessage());
			 return "redirect:/admin/" + email + "?popularError";
		 }
	 }

	
}
