package com.linhhd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.linhhd.model.UserDTO;
import com.linhhd.validator.UserValidator;

@Controller
public class HelloController {

	@Autowired
	private UserValidator validator;

	@RequestMapping("/say-hello")
	public ModelAndView sayHello(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "user", required = true) String userName,
			@RequestHeader(value = "Accept", required = false) String contentType) {
		request.setAttribute("msg", contentType);
		return new ModelAndView("hello");

	}

	@RequestMapping("/hello/{name}/{id}")
	public String hello(HttpServletRequest request, @PathVariable(value = "name") String name,
			@PathVariable(value = "id") int id) {

		request.setAttribute("msg", name);
		request.setAttribute("msg", id);
		return "hello";
	}

	@RequestMapping(value = "/them-user", method = RequestMethod.GET)
	public String addUser(HttpServletRequest request) {
		UserDTO user = new UserDTO();
		user.setName("Ho Linh");
		request.setAttribute("user", user);

		List<String> favourites = new ArrayList<String>();
		favourites.add("Xem phim");
		favourites.add("Nghe nhac");
		favourites.add("Bong da");
		request.setAttribute("list", favourites);

		return "addUser";
	}

	@RequestMapping(value = "/them-user", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
		validator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> favourites = new ArrayList<String>();
			favourites.add("Xem phim");
			favourites.add("Nghe nhac");
			favourites.add("Bong da");
			request.setAttribute("list", favourites);

			return "addUser";

		}
		MultipartFile multipartFile = user.getAvatar();
		try {
			File file = new File("C:/Uploads/" + multipartFile.getOriginalFilename());
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		return "viewUser";
	}

	@RequestMapping(value = "/upload-file", method = RequestMethod.GET)
	public String uploadFile(HttpServletRequest request) {

		return "upload";

	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam(value = "file") List <MultipartFile> multipartFiles) {
		// luu file xuong o cung

		try {
			for(MultipartFile multipartFile : multipartFiles) {
			File file = new File("C:/Uploads/" + multipartFile.getOriginalFilename());
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("files", multipartFiles);
		return "viewFile";

	}
	
	@RequestMapping(value = "/download-file", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String dataDirectory = "C:/Uploads/";
		Path file = Paths.get(dataDirectory, "1.jpg");
		if(Files.exists(file)){
			response.setContentType("image/jpeg");
			response.addHeader("Content-Disposition", "attachment; filename=anh.jpeg");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
