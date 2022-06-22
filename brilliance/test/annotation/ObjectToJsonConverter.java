package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import annotation.anno.Init;
import annotation.anno.JsonElement;
import annotation.anno.JsonSerializable;

/**
 * 自定义类。1. 根据对象，用动态代理/反射，拿到对象的一切。
 * 2.判断哪些类、方法、属性，用了注解，拿到用了注解的类，方法，属性及其值。
 * 3.实现自己的额外的独立的业务逻辑。
 * 这里是把标注了指定注解类下的指定注解属性，打印成json。
 */
public class ObjectToJsonConverter {
    public String convertToJson(Object object) throws Exception {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //对象转json
    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        //转换逻辑：对属性上有@JsonElement注解，拿到属性名和属性值。
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                //field.getAnnotation(JsonElement.class).key() 取属性的注解内的属性值。
                //如果注解值指定了就用自定的新值作为key，否则用原来的属性名作为key
                if("".equals(field.getAnnotation(JsonElement.class).key())) {
                    jsonElementsMap.put(field.getName(), (String) field.get(object));
                }else {
                    jsonElementsMap.put(field.getAnnotation(JsonElement.class).key(), (String) field.get(object));

                }
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    //校验逻辑：类上有@JsonSerializable注解的才能操作。
    private void checkIfSerializable(Object object) throws Exception {
        if (Objects.isNull(object)) {
            throw new Exception("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new Exception("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

    //初始化逻辑：方法上有@Init注解的方法，执行(把属性值首字母大写)。
    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

}