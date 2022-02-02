package com.earth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import com.earth.dao.consumerRepository;
import com.earth.entities.Cuser;


public class PuserDetailsServiceImpl implements UserDetailsService{


    @Autowired
    private consumerRepository userRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Cuser cuser = userRepository.getCuserByUserName(username);
		
		if(cuser==null) {
			throw new UsernameNotFoundException("Could not found user !!");
		}
		CustomUserDetails customCuserDetails = new CustomUserDetails(cuser);
		return customCuserDetails;
	}

}
