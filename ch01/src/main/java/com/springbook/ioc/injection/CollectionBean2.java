package com.springbook.ioc.injection;

import java.util.Set;

public class CollectionBean2 {
	private Set<String> addressList;

	public Set<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(Set<String> addressList) {
		this.addressList = addressList;
	}
	
}
