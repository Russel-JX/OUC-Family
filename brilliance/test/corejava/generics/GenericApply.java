package corejava.generics;


import java.util.Date;

import java.util.List;


public class GenericApply {


    /**
     * @param args
     */

    public static void main(String[] args) {

        //泛型方法测试。
        Integer[] a1 = new Integer[]{
            6, 3, 2, 345, 34
        } ;
        Double[] a2 = new Double[]{ 6.0, 3.0, 2.0, 345.0, 34.0} ;
        System.out.println("maxvalue of a1=" + getMax(a1));
        System.out.println("maxvalue of a2=" + getMax(a2));

        //泛型类测试
        Car<Integer> car = new Car < Integer > ();

//                car.setT("a");

//                Integer i = (Integer)car.getT();

        //将类中的属性定义成泛型，即接受任意类型参数。就不需要再使用属性时强制将Object向下转型成具体类型。这里在创建类的实例式，确定参数类型，之后类型确定，T就是Integer。

        car.setT(100);

        Integer i = car.getT();

        System.out.println("i =" + i);

        //泛型接口搭配确定类型实现类 - 测试
        GenericIntf<String> a = new A();
        a.method("abc");
        GenericIntf<Integer> b = new B();
        b.method(123);
        //泛型接口搭配泛型实现类 - 测试

        /*

         *List接口和ArrayList实现类就使用的泛型接口和泛型类的架构方式。

         *达到的效果是： 不管实现类的实体中方什么对象类型的元素，都可以完成对集合元素的查询/get，添加/add，删除/remove，清空/clear等操作。

         *接口定义： publicinterface List<E>{... }

         *实现类定义： publicclass ArrayList<E> implements List<E>

         *使用：List<String> list = new ArrayList<String>();List<User> list =new ArrayList<User>();

         */

        GenericIntf<String> c1 = new C<String>();
        GenericIntf<Integer> c2 = new C<Integer>();
        GenericIntf<Double> c3 = new C<Double>();
        c1.method("def");

        c2.method(456);

        c3.method(789.0);


    }


    /**
     * 返回数组中最大的元素
     * <p>
     * 数组的数据类型只能是数字。所以限制泛型的范围是数字类型
     *
     * @param array
     * @return
     */

    public static <T extends Number> T getMax(T array[]) {
        T max = null;
        if (array == null || array.length < 1) {

            return max;

        }

        max = array[0];

        for (T ele : array) {

            max = ele.doubleValue() > max.doubleValue() ? ele : max;

        }

        return max;
    }

    //使用限制
    public void upperBound(List<? extends Date>list, Date date){
        //这句话无法编译  。因为如果list传过来的是Date子类组成的集合，如timestamp，则不能把Date类型数据添加进集合。
        //<? extends Date>换成<? super Date>可以编译。
    //    list.add(date);
        list.add(null);//这句可以编译，因为null没有类型信息

    }

    //泛型类
    public static class Car<T> {

        public T t;

        public T getT() {

            return t;

        }
        public void setT(T t) {

            this.t = t;
        }
    }

    public static interface FatherIntf<T> {
        public void method(T t);

    }


    //泛型接口
    public static interface GenericIntf<T> extends FatherIntf<T> {
        public void method(T t);
    }


    //接口实现类
    public static class A implements GenericIntf<String> {
        public void method (String s) {
            System.out.println("在实现类定义中就已知了参数类型 - 这里是String:" + s);
        }

    }

    //接口实现类
    public static class B implements GenericIntf<Integer> {
        public void method (Integer i) {
            System.out.println("在实现类定义中就已知了参数类型 - 这里是Integer:" + i);

        }

    }

    //接口实现类.只能在实现类的实例中确定参数类型。
    public static class C<T> implements GenericIntf<T> {
        public void method (T t){
            System.out.println("在实现类定义中不知道参数类型，在实现类的实例中确定参数类型,参数类型是" + t.getClass());

        }
    }

}


