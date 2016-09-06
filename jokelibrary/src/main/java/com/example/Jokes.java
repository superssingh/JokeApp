package com.example;

import java.util.Random;

public class Jokes {
    String freejoke[] = {
            "This is the first free joke",
            "This is the second free joke",
            "This is the third free joke",
            "This is the fourth free joke",
            "This is the fifth free joke",
            "This is the sixth free joke",
            "This is the seventh free joke"
    };

    String paidjoke[] = {
            "This is the first paid joke",
            "This is the second paid joke",
            "This is the third paid joke",
            "This is the fourth paid joke",
            "This is the fifth paid joke",
            "This is the sixth paid joke",
            "This is the seventh paid joke"
    };

    public String getJoke(String type){
        Random rm = new Random();
        if (type.equalsIgnoreCase("paid")){
            return paidjoke[rm.nextInt(paidjoke.length)];
        }else{
            return freejoke[rm.nextInt(freejoke.length)];
        }
    }
}