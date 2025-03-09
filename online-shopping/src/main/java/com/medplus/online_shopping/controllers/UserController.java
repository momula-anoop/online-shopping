package com.medplus.online_shopping.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.online_shopping.dto.UserDto;
import com.medplus.online_shopping.service.UserService;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	
	@PostMapping("/Login")
	public ResponseEntity<String> userLogin(@RequestBody UserDto user){
		userService.userLogin(user.getCustomerId(), user.getPassword());
		return new ResponseEntity<>("Valid", HttpStatus.OK);
}
}
