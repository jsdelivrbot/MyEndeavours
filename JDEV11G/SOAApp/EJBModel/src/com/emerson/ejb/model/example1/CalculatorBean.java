package com.emerson.ejb.model.example1;

import javax.ejb.Stateless;

@Stateless(name = "Calculator", mappedName = "SOAApp-EJBModel-Calculator")
public class CalculatorBean implements CalculatorRemote, CalculatorLocal {
  public CalculatorBean() {
  }
  public int add(int x, int y){
    return x+y;
  }
  public int subtract(int x, int y){
    return x-y;
  }
}
