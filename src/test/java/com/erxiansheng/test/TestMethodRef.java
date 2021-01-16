package com.erxiansheng.test;

import com.erxiansheng.entity.Employee;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一：方法引用：若Lambda体中的内容方法已经实现了，我们可以使用"方法引用"
 * (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 * 主要有三种语法格式
 * <p>
 * 对象 :: 实例方法名
 * <p>
 * 类 :: 静态方法名
 * <p>
 * 类 :: 实例方法名
 * <p>
 * 注意
 * ①@Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * ②(x,y) ->x.equals(y);第一个参数是方法的调用者，而第二个参数是要调用方法的参数时，我们就可以使用 类名::实例方法名
 * <p>
 * 二：构造器引用
 * 格式
 * ClassName :: new
 *
 * 注意：需要调用的构造器的参数列表要与函数接口中抽象方法的参数列表保持一致；
 *
 * 三、数组引用；
 *
 * Type::new;
 */
public class TestMethodRef {

//    数组引用
    @Test
    public  void test6(){
        Function<Integer,String[]> fun=(x) -> new String[x];
        String[] strs=fun.apply(10);
        System.out.println(strs.length);
        System.out.println("Test");
        System.out.println("qujianhua");
        System.out.println("qujianhua");
        Function<Integer,String[]> fun1=String[]::new;
        String[] strs1=fun1.apply(20);
        System.out.println(strs1.length);

    }
    // 构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();
        Supplier<Employee> sup2 = Employee::new;
        Employee ey = sup2.get();
        System.out.println(ey);
        Function<String, Employee> fc = Employee::new;
        Employee ey1 = fc.apply("BBB");
        System.out.println(ey1);
        BiFunction<String, Integer, Employee> bf = Employee::new;
        Employee ep3 = bf.apply("黎明", 123);
        System.out.println(ep3);

    }

    @Test
    public void test4() {
        /**
         * (x,y) ->x.equals(y);第一个参数是方法的调用者，而第二个参数是要调用方法的参数时，我们就可以使用 类名::实例方法名
         */
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
//         (x,y) ->x.equals(y);第一个参数是方法的调用者，而第二个参数是要调用方法的参数时，我们就可以使用 类名::实例方法名
        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("AAA", "AAA"));
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com1 = Integer::compare;
    }

    @Test
    public void test1() {
        Consumer<String> con = x -> System.out.println(x + "AAAAA");
        con.accept("DDd");
        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
        con1.accept("DDd" + "AAAAA");
    }

    @Test
    public void test2() {
        Employee emp = new Employee();
        Supplier<String> sup = () -> emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = emp::getAge;
        int num = sup2.get();
        System.out.println(num);
    }
}
