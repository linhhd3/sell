package com.linhhd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.linhhd.model.UserDTO;
import com.linhhd.service.UserService;

@RestController
public class RestUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/list-user", method=RequestMethod.GET)
	public List<UserDTO> listUser(HttpServletRequest request){
		List<UserDTO> users = userService.getAllUser();
		
		return users;
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public UserDTO viewUser(@PathVariable(value = "userId") int userId) {

		UserDTO userDTO = userService.getUserById(userId);

		return userDTO;
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void addUser(@RequestBody UserDTO user) {
		userService.addUser(user);

	}
}
