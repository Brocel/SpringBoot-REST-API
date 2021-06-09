package com.brocels.springboot.backend.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.brocels.springboot.backend.service.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter{

	
	@Autowired
    private JwtUtil jwtUtil;
	
    @Autowired
    private CustomUserDetailsService service;
    
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		String token = null;
        String userName = null;
        
        
		
	}

}
