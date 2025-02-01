package com.movieApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.request.ShowRequest;
import com.movieApp.request.ShowSeatRequest;
import com.movieApp.service.ShowService;

@Controller
@RequestMapping("/show")
public class ShowController {
	
	@Autowired
	ShowService showService;

	@PostMapping("/addNew")
	public String addShow(@ModelAttribute ShowRequest showRequest,RedirectAttributes redirectAttributes)
	{
		try {
			
			System.out.println(showRequest);
			
			
			String result = showService.addShow(showRequest);
			
			redirectAttributes.addFlashAttribute("message", result);
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?showSuccess";
			
			//return new ResponseEntity<String>(result, HttpStatus.CREATED);
		}catch(Exception e){
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?showError";
			//return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/associateSeats")
	public String associateShowSeats(@ModelAttribute ShowSeatRequest showSeatRequest,RedirectAttributes redirectAttributes) {
		try {
			
			System.out.println(showSeatRequest);
			String result = showService.associateShowSeats(showSeatRequest);
			redirectAttributes.addFlashAttribute("message", result);
			return "redirect:/admin/vishalkhamkar2003@gmail.com?showSeatSuccess";
			
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?showSeatError";
		}
	}
}
