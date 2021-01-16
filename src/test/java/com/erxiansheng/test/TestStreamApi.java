package com.erxiansheng.test;

import com.erxiansheng.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreamApi {

    /**
     * 获取流的的几种种方式
     * collection 系列集合提供stream() 或者parallelSteam()
     * ①list.Stream();
     * ②Arrays.Stream();
     * ③Stream.of();
     * ④Stream.Iterate(0,(x)->x+2); 无限流
     * ⑤ Steam.generate(() ->Math.random)
     */

    /**
     * 给定一个数字列表，如何返回一个每个数的平方构成列表
     * 给定 【1，2，3，4】---->【1，4，9，16】
     */

    List<Employee> employees = Arrays.asList(new Employee("张三", 101, 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 102, 59, 4444.99, Employee.Status.FREE),
            new Employee("王五", 103, 28, 3333.99, Employee.Status.VACATION),
            new Employee("赵六", 104, 8, 7777.99, Employee.Status.FREE),
            new Employee("田七", 105, 38, 5555.99, Employee.Status.VACATION),
            new Employee("aaa", 106, 28, 6666.99, Employee.Status.BUSY),
            new Employee("bbb", 106, 28, 6666.99, Employee.Status.FREE));

    @Test
    public void test1(){
//        answer1();
//        answer2();
        Integer[] array={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Supplier<Stream<Integer>> streamSupplier=() -> Stream.of(array);
//       XX   每个 get() 都会返回一个新的流
        streamSupplier.get().map(e -> e * e).forEach(System.out::println);
        System.out.println(streamSupplier.get().map(e -> e * e).collect(Collectors.toList()));
        streamSupplier.get().map(e -> e * e).collect(Collectors.toList()).forEach(System.out::println);
//        list.forEach(System.out::println);
    }

    /*
	 2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 */
    @Test
    public void test2(){
        Optional<Integer> reduce = employees.stream().map(e -> 1).reduce((x, y) -> x + y);
        System.out.println(reduce.get());
        /**
         * BiFunction<T,T,T>
         * T apply(T t, T T);
         */
        Optional<Integer> reduce1 = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce1.get());
    }


    private void answer2() {
        int[] array={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntStream intStream = Arrays.stream(array).map(e -> e * e);
        intStream.forEach(System.out::println);
    }

    private void answer1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> value = list.stream().map(e -> e * e);
        value.forEach(System.out::println);
        System.out.println("------------------");
//        convert a stream  to List
        List<Integer> clist= value.collect(Collectors.toList());
        System.out.println(clist);
//        clist.forEach(System.out::println);
    }
}
