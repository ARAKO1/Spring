package com.springbook.ioc.polymorphism;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker{
	
	//생성자
	public SonySpeaker() {
		System.out.println("===>SonySpeaker 객체 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("===>SonySpeaker-----소리 울린다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("===>SonySpeaker---소리 내린다.");
	}
}
