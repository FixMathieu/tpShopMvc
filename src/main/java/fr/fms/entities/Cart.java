package fr.fms.entities;

import java.util.HashMap;

public class Cart {
	
	private static double total;
	
	private static HashMap<Long, Integer> content;
	
	
	private Cart() {
			
		Cart.content = new HashMap<Long, Integer>();
		Cart.total = 0;
	
	}
	
	public HashMap<Long, Integer> getContent(){
		return Cart.content;
	}
	
	public void setContent(HashMap<Long, Integer> content) {
		Cart.content=content;
	}
	
	public double getTotal() {
		return Cart.total;
	}
	
	public void setTotal(double total) {
		Cart.total=total;
	}
	
}
