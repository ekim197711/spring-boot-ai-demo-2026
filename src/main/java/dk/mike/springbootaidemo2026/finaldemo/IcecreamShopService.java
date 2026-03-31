package dk.mike.springbootaidemo2026.finaldemo;

import java.math.BigDecimal;


public class IcecreamShopService {
    public final Boolean areIcecreamsFree = false;

    public BigDecimal buyIceCream(String iceCreamName) {
        BigDecimal cost;
        System.out.println("Are icecreams free -> " + areIcecreamsFree);
        if (areIcecreamsFree) {
            cost = BigDecimal.ZERO;
        }
        else {
            cost = BigDecimal.valueOf(30);
        }
        System.out.printf("Buying ice cream: %s, it cost %s".formatted(iceCreamName, cost));
        return cost;
    }
}
