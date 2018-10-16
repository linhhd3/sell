package com.linhhd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linhhd.dao.UserDao;
import com.linhhd.entity.User;
import com.linhhd.model.UserDTO;
import com.linhhd.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public void addUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setPhone(userDTO.getPhone());
		userDao.addUser(user);

	}

	public void updateUser(UserDTO userDTO) {
		User user = userDao.getUserById(userDTO.getId());
		if (user != null) {
			user.setName(userDTO.getName());
			user.setPhone(userDTO.getPhone());
			userDao.updateUser(user);
		}

	}

	public void deleteUser(int id) {
		User user = userDao.getUserById(id);
		if (user != null) {
			userDao.deleteUser(id);
		}

	}

	public UserDTO getUserById(int id) {
		User user = userDao.getUserById(id);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setPhone(user.getPhone());

		return userDTO;
	}

	public List<UserDTO> getAllUser() {
		List<User> users = userDao.getAllUser();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setPhone(user.getPhone());

			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

}
