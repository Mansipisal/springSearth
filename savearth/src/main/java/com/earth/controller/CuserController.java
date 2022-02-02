package com.earth.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.earth.dao.ChistoryRepository;
import com.earth.dao.consumerRepository;
import com.earth.entities.Chistory;
import com.earth.entities.Cuser;



@Controller
@RequestMapping("/cuser")
public class CuserController {
	
	@Autowired
	private consumerRepository userRepository;
	
	@Autowired
	 private ChistoryRepository chistoryRepository;
	
	@ModelAttribute
	private void addCommanData(Model model , Principal principal) {
		String name = principal.getName();
		Cuser user = userRepository.getCuserByUserName(name);
		model.addAttribute("user", user); 
	
	}

	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		
	
		return "normal/consumer/consumer_dashboard";
	}
	
	@GetMapping("/order")
	public String orderWaste(Model model) {
		model.addAttribute("title", "sEarth-Consumer order waste page");
		model.addAttribute("chistory",new Chistory());
		
		return "normal/consumer/orderWaste";
	}
	
	@PostMapping("/order-waste")
	public String processOrder(@ModelAttribute Chistory chistory,Principal principal) {
		
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
		System.out.print(formattedDate);
		
		String name = principal.getName();
		Cuser cuser =	this.userRepository.getCuserByUserName(name);
		chistory.setCuser(cuser);
		chistory.setRole("Booked");
		chistory.setOrderDate(formattedDate);
		cuser.getChistory().add(chistory);
		
		this.userRepository.save(cuser);

		return "normal/consumer/orderWaste";
	}

	@GetMapping("/chistory/{page}")
	public String showHistory(@PathVariable("page") Integer page, Model m,Principal principal) {
		m.addAttribute("title", "sEarth-Consumer history");
		String name = principal.getName();
		
		Cuser cuser = this.userRepository.getCuserByUserName(name);
		
		Pageable pageable=	PageRequest.of(page, 5);
		Page<Chistory> history = this.chistoryRepository.findChistoryByCuser(name,pageable);
		m.addAttribute("chistory", history);
		m.addAttribute("currentPage",page);
		m.addAttribute("totalPage",history.getTotalPages());
		
		return "normal/consumer/chistory";
	}
	
	@GetMapping("/profile")
	public String getProfile(Model m) {
		
		m.addAttribute("title", "sEarth-Consumer profile");
		return "normal/consumer/profile";
	}
	
}
