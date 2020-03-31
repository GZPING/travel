package com.gd;

import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.gd.travel.entity.User;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author : GD
 * @date :2020/3/31 : 10:28
 */
public class MockTest extends TestCase {

    public static void main(String[] args) {
        // 准备模板载体
        Map<String, Object> template = new HashMap<>();
        //name是一个随机的中文名称。
        template.put("name", "@cname");
        //age 是一个18-80之间的随机数。
        template.put("age|18-80", 0);
        //email是一个随机的163邮箱。
        template.put("email", "@email(163,com)");
        //password是一个6-16位数的随机字符。
        template.put("password", "@word(6,16)");
        // 设置
        Mock.set(User.class, template);
        // 获取一个MockObject
        MockObject<User> mockUser = Mock.get(User.class);
        // 拿到一个假对象
        User user = mockUser.getOne();
        // 打印展示
        System.out.println(user);

        // 拿到20个user的list
        List<User> list = mockUser.getList(20);
        // 拿到20个user的list，并转化为它们的toString字符串
        Set<String> set = mockUser.getSet(20, User::toString);
        // 获取一个生成User对象的无限流
        Stream<User> stream = mockUser.getStream();
        // 通过并行流(即多线程)的方式获取20个user对象
        List<User> listParallel = mockUser.getListParallel(20);

        System.out.println(list);
        System.out.println(set);
        System.out.println(stream);
        System.out.println(listParallel);

    }
}
