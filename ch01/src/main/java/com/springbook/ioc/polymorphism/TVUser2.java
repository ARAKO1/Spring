package com.springbook.ioc.polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser2 {
	public static void main(String[] args) {
		//1.Spring 컨테이너 구동
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext3.xml");
		
		//2.Spring컨테이너로부터 필요한 객체 요청
		Tv tv = (Tv)factory.getBean("Tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		//3.Spring컨테이너 종료;
		factory.close();
	}

}
