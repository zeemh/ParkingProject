package com.project.service;

import org.springframework.stereotype.Service;

import com.project.dto.AddParkingDTO;
import com.project.mapper.AddParkingMapper;

@Service
public class AddParkingService {
	private AddParkingMapper mapper;

	public AddParkingService(AddParkingMapper mapper) {
		this.mapper = mapper;
	}

	public int insertAddparking(AddParkingDTO dto) {
		return mapper.insertAddparking(dto);
	}

	
}
