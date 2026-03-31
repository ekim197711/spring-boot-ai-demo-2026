package dk.mike.springbootaidemo2026.finaldemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class IcecreamShopServiceTest {


    @Test
    void buyIceCreamChocolate() {
        IcecreamShopService icecreamShopService = new IcecreamShopService();
        BigDecimal expense = icecreamShopService.buyIceCream("Chocolate");
        Assertions.assertThat(expense).isNotNull();
        Assertions.assertThat(expense).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    void buyIceCreamScam() throws NoSuchFieldException, IllegalAccessException {
        IcecreamShopService icecreamShopService = new IcecreamShopService();
        java.lang.reflect.Field f = icecreamShopService.getClass().getField("areIcecreamsFree");
        f.setAccessible(true);
        f.set(icecreamShopService, true);
        BigDecimal expense = icecreamShopService.buyIceCream("Chocolate");
        Assertions.assertThat(expense).isNotNull();
        Assertions.assertThat(expense).isEqualTo(BigDecimal.ZERO);
    }
}