package com.springbook.ioc.injection;

import java.util.List;

public class CollectionBean {
	private List<String> addressList;

	//get.set메소드
	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	
	

}
