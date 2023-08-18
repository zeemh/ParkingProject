package com.project.dto;

import org.apache.ibatis.type.Alias;

@Alias("addparking")
public class AddParkingDTO {
	private String parkNum;
	private String address;
	private String parkSize;
	private String price;
	private String parkDescription;
	private String userNum;
	
	
	public AddParkingDTO(String address, String parkSize, String price, String parkDescription) {
		this.address = address;
		this.parkSize = parkSize;
		this.price = price;
		this.parkDescription = parkDescription;
	}



	
	public AddParkingDTO() {
	}


	public AddParkingDTO(String parkNum, String address, String price, String parkDescription, String userNum,
			String parkSize) {
		this.parkNum = parkNum;
		this.address = address;
		this.price = price;
		this.parkDescription = parkDescription;
		this.userNum = userNum;
		this.parkSize = parkSize;
	}


	public String getParkNum() {
		return parkNum;
	}


	public void setParkNum(String parkNum) {
		this.parkNum = parkNum;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getParkDescription() {
		return parkDescription;
	}


	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}


	public String getUserNum() {
		return userNum;
	}


	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}


	public String getParkSize() {
		return parkSize;
	}


	public void setParkSize(String parkSize) {
		this.parkSize = parkSize;
	}


	@Override
	public String toString() {
		return "AddParkingDTO [parkNum=" + parkNum + ", address=" + address + ", price=" + price + ", parkDescription="
				+ parkDescription + ", userNum=" + userNum + ", parkSize=" + parkSize + "]";
	}
	
	
}
