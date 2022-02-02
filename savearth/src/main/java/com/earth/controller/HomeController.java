package com.earth.controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.earth.dao.UserRepository;
import com.earth.dao.consumerRepository;
import com.earth.entities.Cuser;
import com.earth.entities.Puser;
import com.earth.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private consumerRepository CuserRepository;
	
	
	//-----------------	  home page   ----------------------------------
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "sEarth");
		return "home";
	}
	
	
	//-----------------	 producer login page   ----------------------------------
	@GetMapping("/plogin")
	public String cLogin(Model model) {
		model.addAttribute("title", "sEarth-Producer Login page");
		return "producer_login";
	}
	
	
	
	//-----------------	  consumer login page   ----------------------------------
	
	@GetMapping("/clogin")
	public String pLogin(Model model) {
		model.addAttribute("title", "sEarth-Consumer Login page");
		return "consumer_login";
	}
	
	
	//-----------------	  consumer registration page   ----------------------------------
	
	@GetMapping("/c-register")
	public String cRegister(Model model) {
		model.addAttribute("title", "sEarth-Consumer Register page");
		model.addAttribute("cuser" , new Cuser());
		return "consumer_register";
	}
	
	
	//-----------------	  producer registration page   ----------------------------------
	@GetMapping("/p-register")
	public String pRegister(Model model) {
		model.addAttribute("title", "sEarth-Producer Register page");
		model.addAttribute("puser" , new Puser());
		return "producer_register";
	}
	
	@GetMapping("/cdash")
	public String cdash(Model model) {
		model.addAttribute("title", "sEarth-Producer Register page");
		
		return "normal/consumer/consumer_dashboard";
	}
	//handler for producer registration 
	@RequestMapping(value="/do_register",method=RequestMethod.POST )
	public String registerPuser(@Valid @ModelAttribute("puser") Puser puser,BindingResult result, @RequestParam(value="aggrement",defaultValue="false") boolean aggrement,Model model,HttpSession session) {
		try {
			
			if(!aggrement) {
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if(result.hasErrors()) {
				
				model.addAttribute("puser", puser);
				return "producer_register";
			}
			
			puser.setRole("ROLE_PRODUCER");
			puser.setEnabled(true);
			puser.setImgUrl("defalut.png");

			
			Puser res = this.userRepository.save(puser);
			
			model.addAttribute("puser",new Puser());
			session.setAttribute("message", new Message("Producer Successfully Registered","alert-success"));
			return "producer_register";
		}
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("puser", puser);
			session.setAttribute("message", new Message("Something went wrong !!! "+e.getMessage(),"alert-danger"));
			return "producer_register";
		}
		
	}

//handler for consumer registration 
	@RequestMapping(value="/do_c_register",method=RequestMethod.POST )
	public String registerCuser(@Valid @ModelAttribute("cuser") Cuser cuser,BindingResult result, @RequestParam(value="aggrement",defaultValue="false") boolean aggrement,Model model,HttpSession session,Principal principal) {
		try {
			
			if(!aggrement) {
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if(result.hasErrors()) {
				
				model.addAttribute("cuser", cuser);
				return "consumer_register";
			}
			
		
			cuser.setImgUrl("defult.png");
		
			cuser.setRole("ROLE_CONSUMER");
			cuser.setEnabled(true);
			

			
			String newPass =  passwordEncoder.encode(cuser.getPassword());
			
			cuser.setPassword(newPass);

			
			Cuser res = this.CuserRepository.save(cuser);
			
			model.addAttribute("cuser",new Cuser());
			session.setAttribute("message", new Message("Consumer Successfully Registered","alert-success"));
			return "consumer_register";
		}
		catch(Exception e){
			System.out.println(cuser);
			e.printStackTrace();
			model.addAttribute("cuser", new Cuser());
			session.setAttribute("message", new Message("Something went wrong !!! "+e.getMessage(),"alert-danger"));
			return "consumer_register";
		}
		
	}
	
	
	@GetMapping("/supply")
	public String supplyWaste(Model model,HttpSession session) {
		model.addAttribute("title", "sEarth - Supplier page");
		

		return "supply/supplyLogin";
	}
	
}

