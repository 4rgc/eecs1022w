package com.andriibohdan.mcalc;

public class MortgageModel {
    private double principle;
    private double amortization;
    private double interest;
    private double monthlyPayment;

    public MortgageModel(String p, String a, String i) {
        principle = Double.parseDouble(p);
        amortization = Double.parseDouble(a);
        interest = Double.parseDouble(i)/100;
        monthlyPayment = computePayment();
    }

    public double computePayment() {
        return monthlyPayment = (principle * interest)/(1-Math.pow(1+interest, -amortization*12));
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public static void main(String args[]) {
        MortgageModel m1 = new MortgageModel("700000", "25", "2.75");
        System.out.println(m1.computePayment());

        MortgageModel m2 = new MortgageModel("300000", "20", "4.5");
        System.out.println(m2.computePayment());
    }
}