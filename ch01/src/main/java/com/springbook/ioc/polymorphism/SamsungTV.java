package com.springbook.ioc.polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Tv")
public class SamsungTV implements Tv{
	//속성	
	//@Autowired
	//@
	private Speaker speaker;
		private int price;
	
	//생성자
		public SamsungTV() {
			System.out.println("===>SamsungTV(1)객체 생성");			
	}
		public SamsungTV(Speaker speaker) {
			System.out.println("===>SamsungTV(1)객체 생성");			
			this.speaker = speaker;
		}
		public SamsungTV(Speaker speaker, int price) {
			System.out.println("===>SamsungTV(1)객체 생성");			
			this.speaker = speaker;
			this.price = price;
		}



		public void setSpeaker(Speaker speaker) {
			System.out.println("===>setSpeaker() 호출");			
			this.speaker = speaker;
		}

		public void setPrice(int price) {
			System.out.println("===>setPrice() 호출");			
			this.price = price;
		}
		public int getPrice() {
			return price;
		}
		
		@Override
		public void powerOn() {
			System.out.println("SamsungTv----전원켜다.(가격:"+price+")");			
		}
		
		@Override
		public void powerOff() {
			System.out.println("SamsungTv----전원끈다.");			
		}
		
		@Override
		public void volumeUp() {
			System.out.println("SamsungTv----전원끈다.");			
		}
		@Override
		public void volumeDown() {
			System.out.println("SamsungTv----전원끈다.");			
		}
		
		

}
