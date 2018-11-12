package com.emerson.ejb.model.example1;

import javax.ejb.MessageDriven;

import javax.ejb.TransactionManagement;

import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "weblogic.wsee.DefaultQueue")
public class MessageDrivenEJBBean implements MessageListener {
  public void onMessage(Message message) {
  
  }
}
