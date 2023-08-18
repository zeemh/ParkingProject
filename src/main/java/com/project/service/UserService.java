package com.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.dto.UserDTO;
import com.project.mapper.UserMapper;

@Service
public class UserService {
	private static UserMapper mapper;

	public UserService(UserMapper mapper) {
		this.mapper = mapper;
	}

	public UserDTO login(String userId, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("passwd", passwd);

		return mapper.login(map);
	}

	public int insertUser(UserDTO dto) {
		return mapper.insertUser(dto);
	}

	public int UserCheckId(String id, String type) {
		if (type.equals("user")) {
			return mapper.UserCheckId(id);

		}
		return 0;
	}

	public List<UserDTO> searchUser(String kind, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchUser(map);
	}

	public List<UserDTO> viewAlluser() {
		return mapper.viewAlluser();
	}

	public boolean checkPasswd(String userId, String nowPass) {
		UserDTO dto = mapper.getUser(userId);
		return dto != null && dto.getPasswd().equals(nowPass);
	}

	public void updatePasswd(String userId, String newPass) {
	      mapper.updatePasswd(userId, newPass);
}
}