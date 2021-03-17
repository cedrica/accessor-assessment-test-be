package com.cedricleumaleu.accessorassessmenttest.enums;

public enum ColorEnums {

	BLAU(1, "blau"), GREEN(2, "grün"), VIOLA(3, "violett"), RED(4, "rot"), YELLOW(5, "gelb"), TURKIS(6, "türkis"), WHITE(7,"weiß");
	
	private int number;
	private String name;
	
	ColorEnums(int number, String name) {
		this.number = number;
		this.name = name;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getName() {
		return this.name;
	}
}
