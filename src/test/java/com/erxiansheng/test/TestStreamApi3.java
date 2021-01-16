package com.erxiansheng.test;

import com.erxiansheng.entity.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamApi3 {

    /**
     * 查找与匹配
     * allMatch 一检查是否匹配所有的元素
     * anyMatch 一检查是否至少匹配一个元素
     * noneMatch 一检查是否没有匹配所有元素
     * findFirst 一返回第一个元素
     * findAny  一返回当前流中任意的元素
     * count -> 返回流中元素的总个数
     * max 一返回流中的最大值
     * min 一返回流中的最小值
     */
    List<Employee> employees = Arrays.asList(new Employee("张三", 101, 18, 9999.99, Employee.Status.BUSY),
            new Employee("李四", 102, 59, 4444.99, Employee.Status.FREE),
            new Employee("王五", 103, 28, 3333.99, Employee.Status.VACATION),
            new Employee("赵六", 104, 8, 7777.99, Employee.Status.FREE),
            new Employee("田七", 105, 38, 5555.99, Employee.Status.VACATION),
            new Employee("aaa", 106, 28, 6666.99, Employee.Status.BUSY),
            new Employee("bbb", 106, 28, 6666.99, Employee.Status.FREE));

    /**
     * 归约
     * reduce(T identity,BinaryOperator) / deduce(BinaryPerator)-->可以将流中的元素反复结合起来，得到一个值;
     */

    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer num = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(num);
        System.out.println("-------------------------------------");
        System.out.println(employees.stream().map(Employee::getSalary).reduce((x, y) -> x + y));
        System.out.println("-------------------------------------");
        Optional<Double> op = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(new BigDecimal(op.get()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


    }

    /**
     * 收集
     * collect--将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总方法
     */
    @Test
    public void test10(){
        String str = employees.stream().map(Employee::getName).collect(Collectors.joining("，"));
        System.out.println(str);
    }
//    分组
    @Test
    public void test6() {

        Map<Employee.Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(e->e.getStatus()));

        for (Map.Entry<Employee.Status, List<Employee>> item : map.entrySet()) {
            System.out.println(item.getKey() + " -----------> ");
            System.out.println(item.getValue());
        }
    }
    @Test
    public void test9(){
        DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
    }
    // 分区
    @Test
    public void test8(){

        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        for (Map.Entry<Boolean, List<Employee>> item : collect.entrySet()) {

            System.out.println(item.getKey()+" ------ " );
            System.out.println(item.getValue());
        }
        ;
    }

    //    多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> mapMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) ->
                        {
                            if (e.getAge() <= 30) {
                                return "青年";
                            } else if (e.getAge() > 60) {
                                return "老年";
                            } else {
                                return "中年";
                            }
                        }
                )));
        for (Map.Entry<Employee.Status, Map<String, List<Employee>>> statusMapEntry : mapMap.entrySet()) {
            System.out.println(statusMapEntry.getKey() + " -------> ");
            for (Map.Entry<String, List<Employee>> item : statusMapEntry.getValue().entrySet()) {
                System.out.println(item.getKey() + " -------> ");
                System.out.println(item.getValue());
            }
        }

    }

    @Test
    public void test5() {
//        总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);
        System.out.println("------------------");
//        平均数
        Double dv = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(dv);
//        总和
        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
// 最大值
        Optional<Employee> max = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println(max.get());
//        最小值
        Optional<Double> min = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    @Test
    public void test4() {
        List<String> nameList = employees.stream().map(Employee::getName).collect(Collectors.toList());
        nameList.forEach(System.out::println);
        System.out.println("--------------------------");
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        HashSet<String> hs = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test2() {
        System.out.println(employees.parallelStream().count());
        Optional<Employee> max = employees.parallelStream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max);
        Optional<Employee> min
                = employees.parallelStream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);
        Optional<Double> min1 = employees.parallelStream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min1.get());
    }

    @Test
    public void test1() {
        boolean b1 = employees.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b2);

        boolean b3 = employees.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.VACATION));

        System.out.println(b3);

        // 有可能为空
        Optional<Employee> op = employees.stream().sorted((e1, e2) -> -Double.compare(e2.getSalary(), e1.getSalary())).findFirst();

//        op.orElse("");
        System.out.println(op.get());

        System.out.println("-----------------");

        Optional<Employee> any = employees.parallelStream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();

        System.out.println(any.get());

    }
}
