package com.emerson.ejb.model.example1;

import javax.ejb.Remote;

@Remote
public interface CalculatorRemote {
  
  public int add(int x, int y);
  public int subtract(int x, int y);
}
