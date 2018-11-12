package com.training.services.casting;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        Parent p1 = new Child1();
        Parent p2 = new Class2();
        main.show(p2);
    }
    
    private void show(Parent p){
        System.out.println("Object - "+p);
        Child1 c = (Child1)p;
    }
}
