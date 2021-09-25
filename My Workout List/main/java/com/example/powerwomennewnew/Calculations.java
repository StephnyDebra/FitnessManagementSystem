package com.example.powerwomennewnew;

public class Calculations {

    protected float convertKilogramToPounds(Float value) {
        Float ans = (value * 11/5) + 1/5;
        return ans;
    }
    protected float convertKilogramToStone(Float value) {
        Float ans = (value * 1/6) - 1/2;
        return ans;
    }

}


