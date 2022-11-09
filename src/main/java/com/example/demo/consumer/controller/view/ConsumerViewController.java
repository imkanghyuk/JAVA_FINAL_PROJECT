package com.example.demo.consumer.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConsumerViewController {
	
	@GetMapping("/consumer/basket/cbasket")
	public void list() {
		
	}
	
	@GetMapping("/consumer/order/corder")
	public void order() {
		
	}
	
	@GetMapping("/consumer/consumer/consumerinfo")
	public void myinfopage() {
		
	}
	
	@GetMapping("/consumer/consumer/mypage")
	public void mypage() {
		
	}
	
	@GetMapping("/consumer/order/orderlist")
	public void orderlist() {
		
	}
	
	@GetMapping("/consumer/order/ordersuccess")
	public void orderSuccess() {
		
	}
	
	@GetMapping("/main/home")
	public void main() {
		
	}
	
	@GetMapping("/main/storelist")
	public void storelist() {
		
	}
	
	@GetMapping("/main/storeview")
	public void storeview() {
		
	}
	
	@GetMapping("/consumer/consumer/login")
	public void login() {
		
	}
	
	@GetMapping("/consumer/consumer/join")
	public void join() {
		
	}
	
	@GetMapping("/consumer/review/reviewlist")
	public void reviewlist() {
		
	}
}
