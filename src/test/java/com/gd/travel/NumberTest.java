package com.gd.travel;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @Author : GD
 * @Create :2019/10/30 : 10:01
 */
public class NumberTest {

    @Test
    void ScientificNotation() {
        BigDecimal bd = new BigDecimal("1.6442120E+7");
        System.out.println(bd.toString());

        Integer s = 1032196300;
        Object o = s;
        System.out.println(o.toString());
    }
}
