package com.ferropaz.materialDispenser.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utilities {

    public static String formatterDecimal(double number){
        BigDecimal decimal = new BigDecimal(number).setScale(2, RoundingMode.HALF_EVEN);
        return decimal.toString();
    }

    public static String formatterInteger(double number){
        int integerNumber = (int)Math.ceil(number);
        return String.valueOf(integerNumber);
    }
}
