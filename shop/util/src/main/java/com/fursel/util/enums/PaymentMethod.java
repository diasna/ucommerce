/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.util.enums;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
public enum PaymentMethod {
    
    BANK_TRANSFER(1);
    
    private final int code;
    
    private PaymentMethod(int code) {
        this.code = code;
    }
    
    public int code(){
        return this.code;
    }
    
}
