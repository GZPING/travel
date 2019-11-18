package com.gd;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description
 * @Author GD
 * @Date 2019/11/14 22:51
 */
public class Test1 {

    @Test
    public void testBigDecimal() {
        int int1 = 1000000000;
        Integer int2 = 1000000000;
        BigDecimal b1 = new BigDecimal(int1);
        BigDecimal b2 = new BigDecimal(int2);
        BigDecimal b3 = new BigDecimal("6");
        int r1 = b1.divide(b3,4, RoundingMode.HALF_DOWN).intValue();
        Integer r2 = b2.divide(b3,4, RoundingMode.HALF_DOWN).intValue();
        System.out.println(r1);
        System.out.println(r2);
    }
}
