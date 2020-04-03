//Student name: Andrii Bohdan

//This lab was done individually

package com.andriibohdan.caps;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

import static java.lang.Math.random;

public class Game {
    private final String CountryCapitalQuestion = "What is the capital of %s?";
    private final String CapitalCountryQuestion = "%s is the capital of which country?";
    private final int model_size = 241;

    private CountryDB db;
    public int score;
    private int qNumber;
    public int getQNumber() { return qNumber;}

    public Game() {
        db = new CountryDB();
        score = qNumber = 0;
    }

    public String qa() {
        //Retrieve the data from the model
        List<String> capitals = db.getCapitals();
        Map<String, Country> countries = db.getData();

        //Initialize the return string
        String res = "";

        //Get a random Country-Capital pair from the model
        String curCapital = capitals.get((int)(random()*model_size));
        Country curCountry = countries.get(curCapital);

        //Pack the return string in the following format: "question\nanswer"
        //Randomize the question format in a 50-50 fashion
        if(random() < 0.5d) {
            res =  String.format(CountryCapitalQuestion, curCountry.getName())
                    + "\n" + curCapital;
        }
        else {
            res = String.format(CapitalCountryQuestion, curCapital)
                    + "\n" + curCountry.getName();
        }

        //Update the question counter
        qNumber++;
        return res;
    }
}
