package com.linhhd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.linhhd.model.UserDTO;
import com.linhhd.service.UserService;
import com.linhhd.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;
	@Autowired
	UserValidator validator;
	@Autowired
	MailSender mailSender;

	@RequestMapping(value = "/danh-sach-khach-hang", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request) {
		logger.info("Thong tin khach hang");
		List<UserDTO> users = userService.getAllUser();
		request.setAttribute("users", users);

		return "listUsers";
	}

	@RequestMapping(value = "/chi-tiet-khach-hang/{userId}", method = RequestMethod.GET)
	public String viewUser(HttpServletRequest request, @PathVariable(value = "userId") int userId) {

		UserDTO user = userService.getUserById(userId);
		request.setAttribute("user", user);

		return "viewUser";
	}

	@RequestMapping(value = "/them-khach-hang", method = RequestMethod.GET)
	public String addUser(HttpServletRequest request) {

		request.setAttribute("user", new UserDTO());

		return "addUser";
	}

	@RequestMapping(value = "/them-khach-hang", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @ModelAttribute(value = "User") UserDTO user,
			BindingResult bindingResult) {
		validator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addUser";
		}
		userService.addUser(user);

		return "redirect:/admin/danh-sach-khach-hang";
	}

	@RequestMapping(value = "/xoa-khach-hang/{userId}", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, @PathVariable(value = "userId") int userId) {
		userService.deleteUser(userId);

		return "redirect:/admin/danh-sach-khach-hang";
	}

	@RequestMapping(value = "/sua-khach-hang/{userId}", method = RequestMethod.GET)
	public String editUser(HttpServletRequest request, @PathVariable(value = "userId") int userId) {
		UserDTO user = userService.getUserById(userId);
		request.setAttribute("user", user);

		return "editUser";
	}

	@RequestMapping(value = "/sua-khach-hang", method = RequestMethod.POST)
	public String editUser(HttpServletRequest request, @ModelAttribute(value = "User") UserDTO user,
			BindingResult bindingResult) {
		validator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editUser";
		}
		userService.updateUser(user);
		sendEmail("dinhlinh.ho@gmail.com", "dinhlinh.ho@gmail.com", "New job", "Ban ve may bay");

		return "redirect:/admin/danh-sach-khach-hang";
	}

	public void sendEmail(String from, String to, String subject, String content) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		
		mailSender.send(mailMessage);
	}
}
