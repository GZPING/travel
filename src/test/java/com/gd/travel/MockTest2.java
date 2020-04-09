package com.gd.travel;

import com.gd.travel.entity.User;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author : GD
 * @date :2020/4/2 : 16:23
 */
public class MockTest2 {


    @Test
    void testMock1() {
        User user = EasyMock.mock(User.class);
        System.out.println(user);
    }
}
