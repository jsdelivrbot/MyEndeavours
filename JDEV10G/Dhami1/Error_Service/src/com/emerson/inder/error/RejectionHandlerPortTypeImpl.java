package com.emerson.inder.error;

import com.emerson.com.oracle.xmlns.pcbpel.errorhandling.RejectedMessageType;

public class RejectionHandlerPortTypeImpl {
    public RejectionHandlerPortTypeImpl(){
            System.out.println("Constructor SOP Coming...");
        }
    public void handleError(RejectedMessageType message) {
                if(message!=null){
                    System.out.println("Got Message - "+message);
                }
                else{
                    System.out.println("NULL Message");
                }
    }
}
