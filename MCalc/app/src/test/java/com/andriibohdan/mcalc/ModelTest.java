package com.andriibohdan.mcalc;

import org.junit.Assert;
import org.junit.Test;

public class ModelTest {
    @Test
    public void exampleTest1() {
        MortgageModel myModel;

        myModel = new MortgageModel("700000", "25", "2.75");
        Assert.assertEquals("C1", "$3,229.18", myModel.getMonthlyPaymentString());
    }

    @Test
    public void exampleTest2() {
        MortgageModel myModel;

        myModel = new MortgageModel("300000", "20", "4.5");
        Assert.assertEquals("C2", "$1,897.95", myModel.getMonthlyPaymentString());
    }
}
