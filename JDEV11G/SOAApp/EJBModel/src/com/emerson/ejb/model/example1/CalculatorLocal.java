package com.emerson.ejb.model.example1;

import javax.ejb.Local;

@Local
public interface CalculatorLocal {
  public int add(int x, int y);
  public int subtract(int x, int y);
}
