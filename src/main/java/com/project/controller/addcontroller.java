package com.project.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dto.AddParkingDTO;
import com.project.service.AddParkingService;

@Controller
public class addcontroller {

	private AddParkingService addparkingservice;

	public addcontroller(AddParkingService addparkingservice) {
		super();
		this.addparkingservice = addparkingservice;
	}

	
	@RequestMapping("/map")
	public String map() {
		return "add_parking/map1";
	}

	@RequestMapping("/map/action")
	public ResponseEntity<String> addparking(AddParkingDTO dto) {
		System.out.println(dto);
		dto.setUserNum("admin");
		//객체가 잘 받아져오는지 확인하기 위함
		int result = addparkingservice.insertAddparking(dto);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);
		System.out.println(result);
		//map
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@RequestMapping("/succes")
	public String succes() {
		return "add_parking/succes";
	}
	
	@RequestMapping("/cancle")
	public String cancle() {
		return "add_parking/cancle";
	}
	@RequestMapping("/mapMain")
	public String map_main() {
		return "add_parking/map_main";
	}
}
