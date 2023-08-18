package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.dto.UserDTO;

@Mapper
public interface UserMapper {

	UserDTO login(Map<String, Object> map);

	int insertUser(UserDTO dto);

	int UserCheckId(String id);

	List<UserDTO> viewAlluser();

	List<UserDTO> searchUser(Map<String, Object> map);

	UserDTO getUser(String userId);

	void updatePasswd(String userId, String newPass);
}
