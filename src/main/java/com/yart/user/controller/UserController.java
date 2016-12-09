package com.yart.user.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;

import com.yart.user.bean.Contact;
import com.yart.user.bean.Project;
import com.yart.user.bean.Role;
import com.yart.user.bean.User;
import com.yart.user.service.UserService;

@RestController
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
	public ResponseEntity<User> getUser(@PathVariable(value="userId") String userId) {
		
		logger.debug("createUser() - UserId :: " + userId);
		
		// Sample Data for front-end testing
		
		User user = new User();
		user.setEmail("yartyart@yart.com");
		user.setFirstName("Yart");
		user.setLocale("en_US");
		user.setPassword("password");
		
		Contact contact1 = new Contact(1, "faceBook", "fb@yart");
		Contact contact2 = new Contact(2, "gmail", "yart@gmail.com");
		List<Contact> contacts = new LinkedList<>();
		contacts.add(contact1);
		contacts.add(contact2);
		
		Role role = new Role(1, "developer", "Develops Yart", "en_US");
		user.setContacts(contacts);
		user.setRole(role);
		user.setUserStatus(new Byte("1"));
		
		Project project = new Project(1, "yart", "yart description", user);
		user.setProject(project);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
		
		logger.debug("createUser() - User Email :: " + user.getEmail());
		
		
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	/**
	 * Set the default Error response in case of malformed JSON request.
	 */
	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {

			@Override
			public Map<String, Object> getErrorAttributes(
					RequestAttributes requestAttributes,
					boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(
						requestAttributes, includeStackTrace);
				if (errorAttributes.get("timestamp") != null) {
					errorAttributes.remove("timestamp");
				}
				if (errorAttributes.get("exception") != null) {
					errorAttributes.remove("exception");
				}
				if (errorAttributes.get("message") != null) {
					errorAttributes.remove("message");
				}
				if (errorAttributes.get("path") != null) {
					errorAttributes.remove("path");
				}
				return errorAttributes;
			}
		};
	}	
}
