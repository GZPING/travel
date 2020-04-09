package com.gd.travel.utils;

import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : GD
 * @date :2020/3/31 : 11:10
 */
public class MockObjectUtil {

    /**
     * list 的大小
     */
    private final static Integer LIST_SIZE = 10;

    /**
     * 对象中list 的list 大小
     */
    private final static Integer CHILD_LIST_SIZE = 2;

    private final static ConcurrentHashMap<Class, HashMap<String,Object>> roles = new ConcurrentHashMap<>();
    
    public static <T> T mockObject(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        // 断指定类是否是List的子类或者父类
        if(List.class.isAssignableFrom(clazz) || clazz.newInstance() instanceof List
            || Set.class.isAssignableFrom(clazz) || clazz.newInstance() instanceof Set){
            return null;
        }
        Map<String, Object> mockTemplate;
        Field[] declaredFields = clazz.getDeclaredFields();
        if((mockTemplate = roles.get(clazz)) == null){
            mockTemplate = getMockTemplate(declaredFields);
        }
        Mock.reset(clazz, mockTemplate);
        MockObject<T> mockData = Mock.get(clazz);
        T result = mockData.getOne();
        // 对list 进行处理
        for (Field declaredField : declaredFields) {
            Class type = declaredField.getType();
            // List 数据处理
            if(List.class.equals(type) || Set.class.equals(type)){
                Type genericType = declaredField.getGenericType();
                List childList= getListByGenericType(genericType);
                declaredField.setAccessible(true);
                declaredField.set(result,childList);
            }
        }
        return result;
    }

    /**
     * 构造list 数据
     * @param clazz 获取对应List 的 class 的类型
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List mockList(Class clazz) throws InstantiationException, IllegalAccessException {
        List result = new ArrayList(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE; i++) {
            result.add(mockObject(clazz));
        }
        return result ;
    }

    /**
     * 处理集合
     * @param genericType 当前集合的泛型类型
     * @return
     */
    private static List getListByGenericType(Type genericType){
        if (null == genericType) {
            return null;
        }
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            // 得到泛型里的class类型对象
            Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
            Field[] listFields = actualTypeArgument.getDeclaredFields();
            Map<String, Object> listTemplate;
            if((listTemplate = roles.get(actualTypeArgument)) == null){
                listTemplate = getMockTemplate(listFields);
            }
            Mock.reset(actualTypeArgument, listTemplate);
            MockObject mockUser = Mock.get(actualTypeArgument);
            List listParallel = mockUser.getListParallel(CHILD_LIST_SIZE);
            return listParallel;
        }
        return null;
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
            Class type = declaredField.getType();
            // 过滤数组
            if(List.class.equals(type) || Set.class.equals(type)){
                continue;
            }
            String fieldName = declaredField.getName();
            String role = convert2MockWork(fieldName, declaredField.getType());
            // 只有匹配上规则的才会添加规则
            if(role != null){
                template.put(fieldName,role);
            }
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
        }else if(type.equals(Integer.class) || type.equals(Long.class)){
            return "@doubles(0,10000000)";
        }else if(type.equals(Date.class)){
            return "@date";
        }else if(type.equals(String.class)){
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
            }else if(fieldName.toLowerCase().contains("url")||fieldName.toLowerCase().contains("link")){
                return "url";
            }else if(fieldName.toLowerCase().contains("date")){
                return "toDateStr";
            }else if(fieldName.toLowerCase().contains("time")){
                return "toDateTime";
            }
            // 默认显示一段6,16 字的话
            return "@word(6,16)";
        }
        return null;
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<CompaniesDTO> users = mockList(CompaniesDTO.class);
        System.out.println(users);
//        User o = mockObject(User.class);
//        System.out.println(o);
    }
}
