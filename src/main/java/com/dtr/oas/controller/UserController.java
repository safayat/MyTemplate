package com.dtr.oas.controller;

import com.dtr.oas.model.User;
import com.dtr.oas.service.UserService;
import com.dtr.oas.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;


@RestController
//@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value="/public/api/signup", method = RequestMethod.POST)
	public ApiResponse signup(@RequestBody User user) {
		ApiResponse response = user.validate(false);
		if(response.isSuccess() == false){
			return response;
		}
		try {
			user.setCreationDate(new Date());
			user.setUpdateDate(new Date());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.saveOrUpdate(user);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.success(false)
					.type(ApiResponse.EXCEPTION)
						.message(e.getMessage());
		}
		return response;
	}
	@RequestMapping(value="/admin/api/test", method = RequestMethod.GET)
	public ApiResponse signup() {
		ApiResponse response = new ApiResponse();
		return response;
	}


	@RequestMapping(value="/private/api/search/name", method = RequestMethod.GET)
	public ResponseEntity getUserByName(
			@RequestParam("username") String userName
			,HttpServletResponse response) {
		try {
			User user = userService.getUserByUserName(userName);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@RequestMapping(value="/admin/api/user/list", method = RequestMethod.GET)
	public ResponseEntity getUsers(
			HttpServletResponse response) {
		try {

			return new ResponseEntity<List<User>>(userService.getAll(User.class), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@RequestMapping(value="/private/api/search/unique", method = RequestMethod.GET)
	public ResponseEntity getUser(
			@RequestParam(value = "username", required = false) String userName
			,@RequestParam(value = "userId", required = false) Integer userId
			,@RequestParam(value = "email", required = false) String email
			,HttpServletResponse response) {

		try {

			if(!StringUtils.isEmpty(userId)){
				return new ResponseEntity<User>(userService.get(User.class,userId), HttpStatus.OK);
			}

			if(StringUtils.hasText(userName)){
				return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.OK);
			}

			if(StringUtils.hasText(email)){
				return new ResponseEntity<User>(userService.uniqueSearch(User.class, "email=", email), HttpStatus.OK);
			}
			return new ResponseEntity<String>("Param missing",HttpStatus.EXPECTATION_FAILED);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@RequestMapping(value="/private/api/user", method = RequestMethod.GET)
	public ResponseEntity getLoggedInUser(Principal principal,HttpServletResponse response) {
		try {
			User user = userService.getUserByUserName(principal.getName());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@RequestMapping(value="/public/api/login/error", method = RequestMethod.GET)
	public ResponseEntity loginErrorhandler(Principal principal,HttpServletResponse response) {

		return new ResponseEntity<String>("Login failed",HttpStatus.INTERNAL_SERVER_ERROR);


	}
}
