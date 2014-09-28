package com.fursel.util.enums;

public enum ProductCondition {
	NEW('N'), SECOND('S'), REFURBISHED('R');
	
	private char value;
	 
    private ProductCondition(char value) {
        this.value = value;
    }

}
