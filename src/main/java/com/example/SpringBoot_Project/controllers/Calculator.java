package com.example.SpringBoot_Project.controllers;

public class Calculator {
    public int add(int a,int b){
        int addition = a+b;
        return addition;
    }
    public int sub(int a,int b){
        int subtraction = a-b;
        return subtraction;
    }
    public int mul(int a,int b){
        int multiplicate = a*b;
        return multiplicate;
    }
    public int div(int a,int b){
        if(b==0) return 0;
        int division = a/b;
        return division;
    }
}
