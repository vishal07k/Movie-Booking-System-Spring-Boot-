package com.movieApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movieApp.request.TheaterRequest;
import com.movieApp.request.TheaterSeatRequest;
import com.movieApp.service.TheaterService;


@Controller
@RequestMapping("/theater")
public class TheaterContoller {

	@Autowired
	private TheaterService theaterService;

	@PostMapping("/addNew")
	public String addTheater(@ModelAttribute TheaterRequest request,RedirectAttributes redirectAttributes) {
		try {
			
			System.out.println(request);
			
			String result = theaterService.addTheater(request);
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
			
			redirectAttributes.addFlashAttribute("message", result);
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?theaterSuccess";
			
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?theaterError";
		}
	}
	@PostMapping("/addTheaterSeat")
	public String addTheaterSeat(@ModelAttribute TheaterSeatRequest entryDto, RedirectAttributes redirectAttributes) {
		
		try {
			
			System.out.println(entryDto);
			String result = theaterService.addTheaterSeat(entryDto);
			
			redirectAttributes.addFlashAttribute("message", result);
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?theaterSeatSuccess";
			
			//return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			
			return "redirect:/admin/vishalkhamkar2003@gmail.com?theaterSeatError";
			
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
