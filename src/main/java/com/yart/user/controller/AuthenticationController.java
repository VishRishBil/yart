package com.yart.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yart.user.bean.User;
import com.yart.user.service.UserService;

@RestController
public class AuthenticationController {

	private Logger logger = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> authenticateUser(@RequestBody User user) {
		
		logger.debug("createUser() - User Email :: " + user.getEmail());
		if(user.getEmail() == null) {
			return new ResponseEntity<String>("Email should not be Empty", HttpStatus.BAD_REQUEST);
		} else if (user.getPassword() == null) {
			return new ResponseEntity<String>("Password should not be Empty", HttpStatus.BAD_REQUEST);
		}
 		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
