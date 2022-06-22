package annotation;

public class AnnotationTest {

    public static void main(String[] args) throws Exception {
        Person person = new Person("russell", "j", "32", "SH");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
        /**
         * 可见没用注解的address没被输出。age属性用了新属性名(@JsonElement(key = "personAge"))
         * output:
         *   {"personAge":"32","firstName":"Russell","lastName":"J"}
         */
    }
}