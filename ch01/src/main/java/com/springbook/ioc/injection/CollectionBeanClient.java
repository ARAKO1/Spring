package com.springbook.ioc.injection;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		//1.컨테이너 기동
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext2.xml");
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		CollectionBean2 bean2 = (CollectionBean2)factory.getBean("collectionBean2");
		CollectionBean3 bean3 = (CollectionBean3)factory.getBean("collectionBean3");
		CollectionBean4 bean4 = (CollectionBean4)factory.getBean("collectionBean4");
		
		//list객체 얻기//중복허용
		List<String> list= bean.getAddressList();
		
		for(String s:list) {System.out.println(s);}
		for(int i=0; i<list.size();i++) {System.out.println(list.get(i));}
		
		//set객체 얻기//중복되는값 등록하지 않는다. 
		Set<String> set = bean2.getAddressList();
		Iterator<String> itor = set.iterator();
		while(itor.hasNext()) {
			System.out.println(itor.next());
		}
		
		//Map객체 얻기
		Map<String,String> map = bean3.getAddressList();
		Set<String> keySet = map.keySet();
		Iterator<String> itor2 = keySet.iterator();
		while(itor2.hasNext()) {
			String name = itor2.next();
			String address = map.get(name);
			System.out.println(name+":"+address);
		}
		
		//Properties 얻기
		Properties addrList = bean4.getAddressList();
		Set<Object> kS = addrList.keySet();
		Iterator<Object> itor3 = kS.iterator();
		while(itor3.hasNext()) {
			String name = itor3.next().toString();
			String address =addrList.getProperty(name);
			System.out.println(name+":"+address);
		}
		
	}
}
