package com.spring.user_service.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.user_service.entity.User;
import com.spring.user_service.response.AuthRequest;
import com.spring.user_service.util.JwtUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private JwtUtil jwtUtil;
	    
	    @PostMapping("/register")
	    public User registration(@RequestBody User user) {
	    	System.err.println(user.getUsername());
	        // return UserService.register(user);
			return user;
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
	        System.out.println("Login called");
	        
	        try {
	            System.out.println("Authentication successful for user: " + authRequest.getUsername());
	            Map<String,String> response=userService.findByUsername(authRequest.getUsername());
	            System.err.println(response.get("JWT Token"));
	            return ResponseEntity.ok(response);
	        } catch (BadCredentialsException e) {
	            System.out.println("Invalid credentials: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Exception occurred: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }

	    @GetMapping("/profile")
	    public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String token) {
	    	try {
	            String jwtToken = token.substring(7); // Remove "Bearer " prefix
	            System.out.println("Received token: " + jwtToken);
	            
	            // Validate the token
	            if (jwtUtil.validateToken(jwtToken, jwtUtil.extractUsername(jwtToken))) {
	                String username = jwtUtil.extractUsername(jwtToken);
	                System.out.println("Username from token: " + username);
	                User user =  (User) userService.findByUsername(username);
	                if (user != null) {
	                    return ResponseEntity.ok(user);
	                } else {
	                    System.out.println("User not found");
	                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	                }
	            } else {
	                System.out.println("Invalid token");
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }
}
