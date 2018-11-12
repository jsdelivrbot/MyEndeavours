package com.assignment.stocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StockComputationClass {
  
  private static void compute(){
    String stockNameInput;
    int sharesPurchased;
    float amountPerShareBought;
    int sharesSold;
    float amountPerShareSold;
    float totalBoughtAmount;
    float boughtCommission;
    float totalSoldAmount;
    float soldCommission;
    float profitAmount;
    
    System.out.println("*************************************");
    System.out.println("********** STOCK COMPUTATION ********");
    System.out.println("*************************************");
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      System.out.print("Enter the stock's name: ");
      stockNameInput = br.readLine();
      
      System.out.print("Enter the total number of shares purchased: ");
      sharesPurchased = Integer.parseInt(br.readLine());

      System.out.print("Enter the total dollar amount paid per share: ");
      amountPerShareBought = Float.parseFloat(br.readLine());
      
      System.out.print("Enter the total number of shares sold : ");
      sharesSold = Integer.parseInt(br.readLine());
      
      System.out.print("Enter the dollar amount paid per share: ");
      amountPerShareSold = Float.parseFloat(br.readLine());
      
      boughtCommission = getCommissionAmount(sharesPurchased, true);
      soldCommission = getCommissionAmount(sharesSold, false);
      
      
      totalBoughtAmount = sharesPurchased * amountPerShareBought;
      totalSoldAmount = sharesSold * amountPerShareSold;
      
      // profit = total return - total investment
      profitAmount = (totalSoldAmount-soldCommission)-(totalBoughtAmount+boughtCommission);
      
      System.out.println("\n");
      System.out.println("Mario and luigi software inc.");
      System.out.println("Total amount paid $"+totalBoughtAmount);
      System.out.println("Total commission paid at time of purchase $"+boughtCommission);
      System.out.println("Total amount sold $"+totalSoldAmount);
      System.out.println("Total commission paid at time of purchase $"+soldCommission);
      System.out.println("Total amount of profit made $"+profitAmount);
      System.out.println("*************************************");
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private static float getCommissionAmount(int numberOfShares, boolean wereBought){
    float commissionAmount;
    if(wereBought == true){
      if(numberOfShares<1000){
        commissionAmount = 100;
      }
      else{
        commissionAmount = 0;
      }
    }
    else{
      if(numberOfShares<1000){
        commissionAmount = 100;
      }
      else if(numberOfShares>=1000 && numberOfShares<2000){
        commissionAmount=50;
      }
      else{
        commissionAmount = 0;
      }
    }
    return commissionAmount;
  }
  
  public static void main(String[] args) {
    compute();
  }
  
}
