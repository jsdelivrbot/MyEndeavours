package com.emerson.inder.fault.services;

public class HelloPoliciesPortTypeImpl {
    public String process(String requestPart){
        System.out.println("Hello Service got - "+requestPart);
        if(true){
           // throw (new RuntimeException());
        }
        return "kiddan "+requestPart;
    }
}
