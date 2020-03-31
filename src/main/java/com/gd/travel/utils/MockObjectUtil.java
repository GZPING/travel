package com.gd.travel.utils;

import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.User;
import org.springframework.data.domain.Sort.Order;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : GD
 * @date :2020/3/31 : 11:10
 */
public class MockObjectUtil {
    
    public static Object mockObject(Class clazz) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String, Object> mockTemplate = getMockTemplate(declaredFields);
        Mock.set(clazz, mockTemplate);
        MockObject mockData = Mock.get(clazz);
        Object result = mockData.getOne();

        /*for (Field declaredField : declaredFields) {
            Class type = declaredField.getType();
            // List 数据处理
            if(List.class.equals(type)){
                // 当前集合的泛型类型
                Type genericType = declaredField.getGenericType();
                if (null == genericType) {
                    continue;
                }
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    // 得到泛型里的class类型对象
                    Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
                    Field[] listFields = actualTypeArgument.getDeclaredFields();
                    Map<String, Object> listTemplate = getMockTemplate(listFields);
                    Mock.set(actualTypeArgument, mockTemplate);
                    // 获取一个MockObject
                    MockObject mockUser = Mock.get(actualTypeArgument);
                    // 通过并行流(即多线程)的方式获取20个user对象
                    List listParallel = mockUser.getListParallel(20);
                    declaredField.set(result, listParallel);
                }
            }else if(Set.class.equals(declaredField.getType())){
                // Set 数据处理
            }
        }*/
        return result;
    }


    /**
     * 获取模板
     * @param declaredFields
     * @return
     */
    public static  Map<String, Object> getMockTemplate(Field[] declaredFields){
        // 准备模板载体
        Map<String, Object> template = new HashMap<>();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            template.put(fieldName,convert2MockWork(fieldName,declaredField.getType()));
        }
        return template;
    }

    /**
     * 规则生成
     * 将feed 数组转换为mock 对应的字符串
     * @param fieldName
     * @return
     */
    private static String convert2MockWork(String fieldName,Class type){
        /**
         * 先处理类型
         */
        if(type == null){
            return "";
        }else if(type.equals(Double.class)){
            return "@doubles(0,1000000,0,100)";
        }else if(type.equals(Integer.class)){
            return "@doubles(0,10000000)";
        }else if(type.equals(Date.class)){
            return "@date";
        }
        /**
         * String 类型处理
         */
        // 名字处理
        if(fieldName.toLowerCase().contains("name")){
            return "@cname";
            //句子处理
        }else if(fieldName.toLowerCase().contains("email")){
            return "@email";
        }else if(fieldName.toLowerCase().contains("password")){
            return "@word(6,16)";
        }else if(fieldName.toLowerCase().contains("url")){
            return "url";
        }else if(fieldName.toLowerCase().contains("date")){
            return "toDateStr";
        }else if(fieldName.toLowerCase().contains("time")){
            return "toDateTime";
        }
        // 默认显示一段6,16 字的话
        return "@paragraph(6,16)";

    }


    public static void main(String[] args) throws Exception {
        List<User> users = new ArrayList<>();
        //Object o = MockObjectUtil.mockObject(users.getClass());
        Object o1 = MockObjectUtil.mockObject(User.class);
       // System.out.println(o);
        System.out.println(o1);
    }
}
