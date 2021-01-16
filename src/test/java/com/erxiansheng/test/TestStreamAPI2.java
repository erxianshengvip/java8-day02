package com.erxiansheng.test;

import com.erxiansheng.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI2 {
   List<Employee> employees=Arrays.asList( new Employee("张三",101,18,9999.99),
            new Employee("李四",102,59,4444.99),
            new Employee("王五",103,28,3333.99),
            new Employee("赵六",104,8,7777.99),
            new Employee("田七",105,38,5555.99),
            new Employee("aaa",106,28,6666.99),
           new Employee("bbb",106,28,6666.99));

    /**
     *      筛选与切片
     *      filter --接收Lambda,从流中排除某些元素
     *      limit -- 截断流，是其元素不超过定数量
     *      skip(n) --路过元素，返回一个扔掉了了前n个元素的流，如流中元素不足n个，则返回一个空流，与limit（n）互补。
     *      distinct--筛选，通过流所生成的元素的hashCode()和equals()去掉重复元素
     *
     */
//    内部迭代有Stream API 完成
    @Test
    public  void test(){
//        中间操作
        Stream<Employee> stream=employees.stream().filter(e ->{
            System.out.println("Strean API 的中间操作！！！");
            return e.getAge() > 35;
        } );
//        终止操作  中间操作不会执行任何处理，而在终止操作时一次性全部处理，称之为“惰性求职”；
        stream.forEach(System.out::println);
    }

    /**
     * 映射
     * map --- 接受Lambda，将元素转换成其他形式或者提取信息，接收一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新的元素。
     * flagMap-------接收一个函数参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
     */
    @Test
    public void test03(){
        List<String> strs=Arrays.asList("aaa","bbb","ccc","ddd","eee");
        strs.stream().map((str)->str.toUpperCase()).forEach(System.out::println);
        System.out.println("-------------------------------");
        employees.stream().map(Employee::getName).forEach(System.out::println);
        System.out.println("-------------------------------");
        employees.stream().map(employee -> employee.getName()).forEach(System.out::println);
   // map Lambda表达式
       Stream<Stream<Character>> stm= strs.stream().map((str)->{ List<Character> list=new ArrayList<>();for(Character ch: str.toCharArray()){ list.add(ch);
        }return list.stream();});

        stm.forEach((st)->st.forEach(
                System.out::println
        ));
        System.out.println("-------------------------------------------");

//        map  Lambda 方法引用  将 元素转换 为其 他形式 或者 提去信息，接收一个函数作为参数，该函数会被应用到每个元素，并将其映射成每一个新的元素。
        Stream<Stream<Character>> sts1=strs.stream().map(this::filterCharacter);//{{a,a,a},{b,b,b}} 把流中的流加到流中；

        sts1.forEach((s) ->  s.forEach(System.out::println));

        System.out.println("=============================================");

        Stream<List<Character>> sts2=strs.stream().map(this::filterCharacter1);

        sts2.forEach((s) ->s.stream().forEach(System.out::println));


    }

    /**
     * 接收一个函数参数，将流中的每个值都转换成另外一个流，然后把所有的流都连到一起。
     *
     */
    @Test
    public void test06(){
        List<String> strs=Arrays.asList("aaa","bbb","ccc","ddd","eee");
        Stream<Character> sss=strs.stream().flatMap(this::filterCharacter); //{a,a,a,b,b,b} 把流中的元素加入到流中；
        sss.forEach(System.out::println);
    }
    @Test
    public void test07(){
        List<String> strs=Arrays.asList("aaa","bbb","ccc","ddd","eee");
        List list=new ArrayList<>();
        list.add("111");
        list.add("2222");
        list.add(strs);
        for (Object o : list) {
            System.out.println(o.toString());
        }
        List list1=new ArrayList<>();
        list1.add("111");
        list1.add("2222");
        list1.addAll(strs);
        for (Object o : list1) {
            System.out.println(o.toString());
        }
    }

    /**
     * sorted(); 自然排序
     * sorted(comparator<>) 自定义排序
     */
    @Test
    public void test8(){
        List<String > list=Arrays.asList("cccc","aaaaa","bbbb","eee","fff");

        list.stream().sorted().forEach(System.out::println);

        System.out.println("--------------------------------");

        employees.stream().sorted((e1,e2)->{
            int num=e2.getAge()-e1.getAge();
            return num==0?e2.getName().compareTo(e1.getName()):num;
        }).forEach(System.out::println);


    }

    public  Stream<Character> filterCharacter(String str){
         List<Character> list=new ArrayList<>();
        for(Character ch: str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    public  List<Character> filterCharacter1(String str){
        List<Character> list=new ArrayList<>();
        for(Character ch: str.toCharArray()){
            list.add(ch);
        }
        return list;
    }
    @Test
    public void test05(){

    }
    @Test
    public void test01(){
        employees.stream().filter(e ->e.getSalary()>5000).limit(2).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------");
        employees.stream().filter(e ->e.getSalary()>5000).skip(2).forEach(System.out::println);
        System.out.println("--------------------------------------------------------------");
        employees.stream().filter(e ->e.getSalary()>5000).skip(2).distinct().forEach(System.out::println);
    }
    @Test
    public void test02(){
        employees.stream().filter(e ->e.getSalary()>5000).skip(2).forEach(System.out::println);
    }
}
