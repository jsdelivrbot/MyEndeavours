package com.emerson.inder.fault.services2;

public class HelloPoliciesPortTypeImpl {
    public String process(String requestPart) {
        System.out.println("Hello Service 2 got - "+requestPart);
        return "Hello "+requestPart;
    }
}
