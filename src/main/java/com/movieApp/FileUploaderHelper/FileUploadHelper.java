package com.movieApp.FileUploaderHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.movieApp.exceptions.MovieDoesNotExists;
import com.movieApp.request.MoviePosterRequest;
import com.movieApp.service.MovieService;

@Component
public class FileUploadHelper {
	
	@Autowired
	MovieService movieService;

	public final String UPLOAD_DIR = "/home/mima26/Desktop/SpringDemo1//MovieBookingSystem/src/main/resources/static/images";

	public int uploadFile(MultipartFile f, int movieId) {
		
		int result = 0;
		
		try {
			
//			InputStream is = f.getInputStream();
//			byte data[] = new byte[is.available()];
//			
//			is.read(data);
//			
//			//write
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + File.separator + f.getOriginalFilename() );
//			fos.write(data);
//			
//			fos.flush();
			
			//Or Option
			
			Files.copy(f.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + f.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			MoviePosterRequest moviePosterRequest = new MoviePosterRequest();
			moviePosterRequest.setMovieId(movieId);
			moviePosterRequest.setPosterName(f.getOriginalFilename());
			
			result = movieService.addPoster(moviePosterRequest);	
			
			
			
			
		}catch(Exception e)
		{
		   new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); 
		}
		
		
		return result;
		
	}
}
