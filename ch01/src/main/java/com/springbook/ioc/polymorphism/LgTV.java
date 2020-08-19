package com.springbook.ioc.polymorphism;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("Tv")
public class LgTV implements Tv {
	//@Autowired//자동 주입(componentScan에 의해 등록된 component중에 speaker객체 주입)
	//@Qualifier("sony")
	@Resource(name="sony")//java제공
	private Speaker speaker;
	private int price;
	
	/*
	 * //생성자 public LgTV(Speaker speaker) { System.out.println("===>LgTv 객체 생성");
	 * this.speaker = speaker; }
	 * 
	 * //생성자 public LgTV(Speaker speaker, int price) { this.speaker = speaker;
	 * this.price = price; }
	 */
	@Override
	public void powerOn() {  
		System.out.println("LgTv----전원켠다.");
	}
	@Override
	public void powerOff() {
		System.out.println("LgTv----전원끈다");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("LgTv----소리올린다.");
	}
	@Override
	public void volumeDown() {
		System.out.println("LgTv----소리 내린다.");
	}
	
}
