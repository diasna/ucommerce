package com.fursel.util.enums;

public enum ProductCondition {

    NEW('N'), SECOND('S'), REFURBISHED('R');

    private final char value;

    private ProductCondition(char value) {
        this.value = value;
    }

    public char value() {
        return this.value;
    }
}
