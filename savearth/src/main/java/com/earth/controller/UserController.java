package com.earth.controller;



import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import com.earth.dao.HistoryRepository;
import com.earth.dao.UserRepository;
import com.earth.entities.History;
import com.earth.entities.Puser;
import com.earth.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private HistoryRepository historyRepository;

	
	@RequestMapping("/index")
	public String dashboard(Model model,@RequestParam String username , @RequestParam String password,HttpSession session){
	
		Puser user = userRepository.getUserByUserName(username);
		System.out.print(user);
		
			
		try {
			
			String uPass= user.getPassword();
			String pass = password;
			
			if(uPass.equals(pass)) {
				System.out.println(user.getPassword());
				System.out.println(password);
				model.addAttribute("user", user); 
				return "normal/user_dashboard";
			}
				
			else
				throw new Exception(" Invalid username and password");

		}
		catch(Exception e){
			
			model.addAttribute("user", user); 
			session.setAttribute("message", new Message("Invalid username and password !!!","alert-danger"));
			return "producer_login";
		}
		
	
	}
	
	@GetMapping("/binlevel/{username}")
	public String orderWaste(@PathVariable("username") String username ,Model model ) {
		model.addAttribute("title", "sEarth-Producer Bin Level");
		
		Puser user = userRepository.getUserByUserName(username);
		System.out.print(user);
		model.addAttribute("user", user); 
		return "normal/binlevel";
		
		
	}
	
	
	@GetMapping("/history/{page}/{username}")
	public String showHistory(@PathVariable("page") Integer page, @PathVariable("username") String username,Model m,Principal principal) {
		m.addAttribute("title", "sEarth-Consumer history");
		
		Puser user = this.userRepository.getUserByUserName(username);
		
		Pageable pageable=	PageRequest.of(page, 5);
		Page<History> history = this.historyRepository.findHistoryByUser(username,pageable);
		m.addAttribute("history", history);
		m.addAttribute("currentPage",page);
		m.addAttribute("totalPage",history.getTotalPages());
		m.addAttribute("user", user);

		return "normal/history";
	}

//	@GetMapping("/order")
//	public String showHistory(Model m) {
//		String username = "pisalmansi1105@gmail.com";
//		Puser user = this.userRepository.getUserByUserName(username);
//		m.addAttribute("user",user);
//		return "normal/orderWaste";
//	}
//	
//	@PostMapping("/order-waste")
//	public String processOrder(@ModelAttribute History history,Model m ,Principal principal) {
//		String username = "pisalmansi1105@gmail.com";
//        Date date = new Date();
//        String strDateFormat = "yyyy-MM-dd";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//        String formattedDate= dateFormat.format(date);
//		System.out.print(formattedDate);
//		
//		
//		Puser user = this.userRepository.getUserByUserName(username);
//   
//		history.setPuser(user);
//		history.setStatus("Order");
//				user.getHistory().add(history);
//		m.addAttribute("user", user);
//		
//		this.userRepository.save(user);
//
//		return "normal/orderWaste";
//	}
//	
	
	@GetMapping("/profile/{username}")
	public String getProfile(Model m,@PathVariable("username") String username) {
		Puser user = this.userRepository.getUserByUserName(username);
		m.addAttribute("title", "sEarth-Consumer profile");
		m.addAttribute("user", user);
		return "normal/profile";
	}
	
	
	
	
	
	@GetMapping("/plogout")
	public String plogout(){
	
		return "producer_login";
	}
}
