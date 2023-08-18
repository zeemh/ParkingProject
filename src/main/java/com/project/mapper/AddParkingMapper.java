package com.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.dto.AddParkingDTO;

@Mapper
public interface AddParkingMapper {

	int insertAddparking(AddParkingDTO dto);

}
