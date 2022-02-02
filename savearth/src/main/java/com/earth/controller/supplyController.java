package com.earth.controller;

import java.security.Principal;
import java.util.Random;

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

import com.earth.dao.ChistoryRepository;
import com.earth.dao.HistoryRepository;
import com.earth.dao.UserRepository;
import com.earth.dao.consumerRepository;
import com.earth.entities.Chistory;
import com.earth.entities.Cuser;
import com.earth.entities.History;
import com.earth.entities.Puser;
import com.earth.helper.Message;
import com.earth.service.EmailService;

@Controller
@RequestMapping("/supply")
public class supplyController {
	
	
	@Autowired
	private	EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private consumerRepository cuserRepository;
	
	@Autowired
	 private ChistoryRepository chistoryRepository;

	@Autowired
	 private HistoryRepository historyRepository;
	

	@RequestMapping("/home")
	public String dashboard(Model model,@RequestParam String username , @RequestParam String password,HttpSession session){
		try {
			
			if(username.equals("supplier") && password.equals("supply123") ) {
				
				return "supply/supplyEmail";
			}
				
			else
				throw new Exception(" Invalid username and password");

		}
		catch(Exception e){
	
			session.setAttribute("message", new Message("Invalid username and password !!!","alert-danger"));
			return "supply/supplyLogin";
		}
	}
	
	@PostMapping("/send-OTP")
	public String verifyUser(Model model ,@RequestParam String userType , @RequestParam String username,HttpSession session) {
		model.addAttribute("title", "sEarth- Verify User account");
		
		System.out.println(userType);
		System.out.println(username);
		
		if(userType=="p") {
		 Puser user=	userRepository.getUserByUserName(username);
		
		 if(user==null) {
			 System.out.println(user);
			 session.setAttribute("message", new Message("Invalid username ","alert-danger"));
				return "supply/supplyLogin";
		 }
		 else {
			 Random random = new Random();
			 int otp = 100000 + random.nextInt(900000);
			
			String subject="OTP from SEARTH";
			String msg = "" + "<div style='border:1px solid #e2e2e2; padding:20px;'>" + "<h1>" + "OTP is " +"<b>" + otp + "</n>" + "</h1>"+"</div>";
			String to=username;
			
			boolean flag = this.emailService.SendEmail(subject, msg, to);
			System.out.println(otp);
			
			if(flag) {
			
				session.setAttribute("message", new Message("OTP has been successfully sent to your registered email id !!!","alert-success"));
				session.setAttribute("email", username); 
				session.setAttribute("myotp" ,otp);
			return "supply/verifyEmail";
			}
			else {
				session.setAttribute("message", new Message("Failed to send OTP !!!","alert-danger"));
				return "supply/supplyEmail";
			}}
		 
		}
		else {
			 Cuser cuser=	cuserRepository.getCuserByUserName(username);
			 if(cuser==null) {
				 System.out.println(cuser);
				 session.setAttribute("message", new Message("Invalid username ","alert-danger"));
					return "supply/supplyEmail";
			 }	
			 else {
				 Random random = new Random();
				 int otp = 100000 + random.nextInt(900000);
				
				String subject="OTP from SEARTH";
				String msg = "" + "<div style='border:1px solid #e2e2e2; padding:20px;'>" + "<h1>" + "OTP is " +"<b>" + otp + "</n>" + "</h1>"+"</div>";
				String to=username;
				
				boolean flag = this.emailService.SendEmail(subject, msg, to);
				System.out.println(otp);
				
				if(flag) {
					session.setAttribute("email", username); 
					session.setAttribute("myotp" ,otp);
				return "supply/verifyEmail";}
				else {
					session.setAttribute("message", new Message("Failed to send OTP !!!","alert-danger"));
					return "supply/supplyEmail";
				}
			 }
		
		}
		
	}
	

	@PostMapping("/verify-otp")
	public String verifyOTP(Model model ,@RequestParam String userType ,@RequestParam int otp,HttpSession session) {
		model.addAttribute("title", "sEarth- Verify User account");
		
		Integer myotp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myotp.equals(otp)) {
			
			if(userType=="p") {
				 Puser user=	userRepository.getUserByUserName(email);
				 model.addAttribute("user", user);
				 session.setAttribute("email", email); 		
				 return  "supply/supplyMain";
			}
			else {
				 Cuser cuser=	cuserRepository.getCuserByUserName(email);
				 model.addAttribute("user", cuser);
				 return  "supply/supplyMain";
			}				
		}
		else {
			int OTPN = (int) session.getAttribute("myotp");
		session.setAttribute("email", email); 
		session.setAttribute("myotp" ,OTPN);
		session.setAttribute("message", new Message("You have entered wrong OTP !!!","alert-danger"));
		return "supply/verifyEmail";
		}
	}
	
	@GetMapping("/history/{page}/{username}")
	public String showHistory(@PathVariable("page") Integer page,@PathVariable("username") String username, Model m,Principal principal) {
		
		m.addAttribute("title", "sEarth-Consumer history");
		
		
		Cuser cuser = this.cuserRepository.getCuserByUserName(username);
	
		Pageable pageable=	PageRequest.of(page, 5);
		Page<Chistory> history = this.chistoryRepository.findChistoryByCuser(username,pageable);
		m.addAttribute("chistory", history);
		m.addAttribute("currentPage",page);
		m.addAttribute("totalPage",history.getTotalPages());
		m.addAttribute("user", cuser);
		return "supply/chistory";
	}
	
	@PostMapping("/update/{id}")
	public String getProfile(Model m,@PathVariable("id") int id) {
		m.addAttribute("title", "sEarth- Supply Update ");
		
		
	    Chistory history = chistoryRepository.findById(id).get();
	    Cuser cuser =	this.cuserRepository.getCuserByUserName(history.getEmail());

	    
//	    try {
//	    	history.setRole("Placed");
//		    cuser.getChistory().add(history);
//			
//			this.cuserRepository.save(cuser);
//			m.addAttribute("user", cuser);
//			 return  "supply/supplyMain";
//	    }
//	    catch(Exception e) {
//	    	 System.out.println(history);
//	    	 System.out.println(cuser);
	 	    
	 	    
	    	
	        m.addAttribute("history",history);
	 		m.addAttribute("user", cuser);
	 		 return  "supply/update";
	    }
	   

	
	@PostMapping("/update-waste")
	public String updateCuser(@ModelAttribute Chistory chistory,Model model) {
		model.addAttribute("title", "sEarth - update cuser");
	  try {
		String email = chistory.getEmail();
		System.out.println(email);
		Cuser cuser =	this.cuserRepository.getCuserByUserName(chistory.getEmail());
		
		 
		 
		 chistory.setCuser(cuser);
		this.chistoryRepository.save(chistory);
		
		model.addAttribute("user", cuser);
		model.addAttribute("email",email);
		return "supply/supplyMain";
	  }
	  catch(Exception e) {
		  Cuser cuser =	this.cuserRepository.getCuserByUserName(chistory.getEmail());
	
		  model.addAttribute("user", cuser);
		  return "supply/supplyMain"; 
	  }
	}	
	
	
// updataing producer status by supplier -------------------------------
	
	@PostMapping("/update-waste-puser")
	public String updatePuser(@ModelAttribute History history,Model model) {
		model.addAttribute("title", "sEarth - update puser");
	  try {
		String email = history.getEmail();
		System.out.println(email);
		Puser user =	this.userRepository.getUserByUserName(history.getEmail());
		
		 
		 
		 history.setPuser(user);
		this.historyRepository.save(history);
		
		model.addAttribute("user", user);
		model.addAttribute("email",email);
		return "supply/supplyMain";
	  }
	  catch(Exception e) {
		  Puser user =	this.userRepository.getUserByUserName(history.getEmail());
	
		  model.addAttribute("user", user);
		  return "supply/supplyMain"; 
	  }
	}	
	
	
	@GetMapping("/dash/{username}")
	public String supplyDash(Model model,@PathVariable("username") String username) {
		model.addAttribute("title", "sEarth - Dashboard page");
		 Cuser cuser =	this.cuserRepository.getCuserByUserName(username);
		 model.addAttribute("user", cuser);
		return "supply/supplyMain";
	}

	
	@GetMapping("/logout")
	public String showHistory(Model model) {
		model.addAttribute("title", "sEarth - Supplier page");
	
		return "supply/supplyLogin";
	}
}
