package com.example.powerwomen;

public class Calculations { //class calculation

    protected float convertCabohydrate(Float value) {
        Float ans = (value * 4) ;//calculating the cabohydrate value
        return ans;
    }
    protected float convertProtein(Float value) {
        Float ans = (value * 4) ; //calculating th protein value
        return ans;
    }
    protected float convertFat(Float value) {
        Float ans = (value * 9) ; //calculating fat value
        return ans;
    }
    protected float convertTotal(Float value) {
        Float ans = (value * 4) + (value * 4) + (value * 9) ; //calculating total calories
        return ans;
    }

}

