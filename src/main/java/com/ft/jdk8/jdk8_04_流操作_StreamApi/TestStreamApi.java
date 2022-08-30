package com.ft.jdk8.jdk8_04_流操作_StreamApi;

import com.ft.jdk8.common.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * 新添加的 Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
 * @Author: fangt
 * @CreateTime: 2021/6/9 21:01
 */
@Slf4j
public class TestStreamApi {
    /**
     * 流的构造
     */
    @Test
    public void streamConstructor(){
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        log.info("{}", stream.collect(Collectors.toList()));

        // 2. Arrays
        String[] array = new String[] {"1", "2", "3"};
        stream = Stream.of(array);
        log.info("{}", stream.collect(Collectors.toList()));
        stream = Arrays.stream(array);
        log.info("{}", stream.collect(Collectors.toList()));

        // 3. Collections
        List<String> list = Arrays.asList("f", "g", "h");
        stream = list.stream();
        log.info("{}", stream.collect(Collectors.toList()));
    }

    /**
     * 流的转换
     */
    @Test
    public void streamConvert(){
        // 流、Arrays 相互转换
        Integer[] a = {2, 3, 4, 5, 6};
        Stream<Integer> stream = Stream.of(a);
        Integer[] b = stream.toArray(Integer[]::new);
        log.info("{}", Arrays.asList(b));

        // 流、Collections 相互转换
        List<Integer> list = Arrays.asList(1, 2, 3);
        Stream<Integer> stream2 = list.stream();
        List<Integer> list2 = stream2.collect(Collectors.toList());
        log.info("{}", list2);
    }

    /**
     * 并行流、串行流
     */
    @Test
    public void streamTest(){
        Arrays.asList(1, 2, 3).stream().forEach(x -> log.info("{}", x));
        Arrays.asList(7, 8, 9).parallelStream().forEach(x -> log.info("{}", x));
    }


    /**
     * map：把inputStream的每个元素映射成outputStream的另外一个元素
     */
    @Test
    public void mapTest(){
        List<Integer> input = Arrays.asList(1, 2, 3, 4);
        List<Integer> output = input.stream().map(n -> n * n).collect(Collectors.toList());
        log.info("{}", output);
    }

    /**
     * flatmap：一对多映射，使层级结构扁平化（就是将最底层元素抽出来放到一起）
     */
    @Test
    public void flatmapTest(){
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> output = input.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        log.info("{}", output);
    }

    /**
     * peek：对每个元素执行操作
     *
     * map 、peek 区别：
     * 先看下map 、peek的定义：
     * 	<R> Stream<R> map(Function<? super T, ? extends R> mapper);
     * 	Stream<T> peek(Consumer<? super T> action);
     * 区别：
     * 	peek接收一个Consumer，而map接收一个Function。
     *     Consumer是没有返回值的，它只是对Stream中的元素进行某些操作，但是操作之后的数据并不返回到Stream中，所以Stream中的元素还是原来的元素。
     *     而Function是有返回值的，这意味着对于Stream的元素的所有操作都会作为新的结果返回到Stream中。
     *     这就是为什么peek String不会发生变化而peek Object会发送变化的原因。
     */
    @Test
    public void peekTest(){
        List<Integer> a = Arrays.asList(1, 2);
        List<Integer> b = a.stream()
                .peek(x -> log.info("{}", x))
                .peek(x -> x = 10)		// 未修改成功
                .peek(x -> log.info("{}", x))
                .collect(Collectors.toList());
        log.info("{}", b);

        List<Student> c = Arrays.asList(new Student("lucky", 1), new Student("show", 2));
        log.info("{}", c);
        List<Student> d = c.stream()
                .peek(x -> log.info("{}", x))
                .peek(x -> x.setAge(10))		// 修改成功
                .peek(x -> log.info("{}", x))
                .collect(Collectors.toList());
        log.info("{}", d);
    }

    /**
     * filter：过滤
     */
    @Test
    public void filterTest(){
        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5, 6);
        List b = a.stream().filter(x -> (x % 2 == 0)).collect(Collectors.toList());
        log.info("{}", b);
    }

    /**
     * sorted：自然排序 / 定制排序
     */
    @Test
    public void sortedTest(){
        Integer[] arr = {8, 5, 1, 4, 6, 10, 9, 2, 3};

        // 自然排序（顺序）
        log.info("{}", Stream.of(arr).sorted().collect(Collectors.toList()));

        // 定制排序
        log.info("{}", Stream.of(arr).sorted((x, y) -> {
                if(x % 2 == 0) {return -1;}	// 偶数在前
                return 1;
            }).collect(Collectors.toList()));

        // 定制排序 -- 常搭配比较函数使用
        // (1) compareTo
        log.info("{}", Stream.of(arr).sorted((x, y) -> x.compareTo(y)).collect(Collectors.toList()));
        // (2) Comparator.comparingInt() --顺序
        log.info("{}", Stream.of(arr).sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList()));
        // (3) Comparator.comparingInt().reversed() --逆序
        log.info("{}", Stream.of(arr).sorted(Comparator.comparingInt(Integer::intValue).reversed()).collect(Collectors.toList()));
    }

    /**
     * forEach：流循环
     * 1、parallelStream().forEach() 可以实现并行处理
     * 2、forEach 不能修改自己包含的本地变量值，也不能用break/return之类的关键字提前结束循环。
     */
    @Test
    public void forEachTest(){
        Arrays.asList(4,5,6).stream().forEach(x -> log.info("{}", x));
    }

    /**
     * findFirst 返回第一个元素
     * 如果流中没有元素，findFirst返回空的Optional。
     * 如果流没有顺序，那么findFirst可以选择任何元素。
     * 如果findFirst选择的元素为null，它将抛出NullPointerException。
     */
    @Test
    public void findFirstTest(){
        List<String> input = Arrays.asList("qqq", "www", "eee");
        String output = input.stream().findFirst().orElse("NullValue");
        log.info("{}", output);
    }

    /**
     * skip 用法：扔掉前n个元素后返回
     * limit 用法：返回Stream的前面n个元素
     */
    @Test
    public void limitSkipTest(){
        List<Integer> collect = Stream.of(1,2,3,4,5,6).skip(2).limit(3).collect(Collectors.toList());
        log.info("{}", collect);
    }

    /**
     * reduce：把Stream元素组合起来（字符串拼接、数值的sum、min、max、average都是特殊的reduce）
     */
    @Test
    public void reduceTest(){
        // 它可以提供一个起始值（种子），有种子返回类型为 T
        int k1 = Stream.of(1, 2, 4).reduce(3, (x, y) -> {
            log.info("{},{}", x, y);
            return x + y;
        });
        log.info("k1：{}", k1);

        int k2 = Stream.of(1, 2, 4).filter(x -> x > 5).reduce(3, (x, y) -> {
            log.info("{},{}", x, y);
            return x + y;
        });
        log.info("k2：{}", k2);

        // 它也可以不提供一个起始值（种子），没有种子返回类型为 Optional<T>
        int k3 = Stream.of(1, 2, 4).reduce((x, y) -> {
            log.info("{},{}", x, y);
            return x + y;
        }).orElse(666);
        log.info("k3：{}", k3);

        int k4 = Stream.of(1, 2, 4).filter(x -> x>5).reduce((x, y) -> {
            log.info("{},{}", x, y);
            return x + y;
        }).orElse(666);
        log.info("k4：{}", k4);
    }

    /**
     * count：计数
     */
    @Test
    public void countTest(){
        List<Student> list = Arrays.asList(new Student("lucky", 19), new Student("show", 25), new Student("hh", 22));
        long b = list.stream().count();
        log.info("{}", b);
    }

    /**
     * min、max：取最值
     * min和max的功能也可以通过对Stream元素先排序，再findFirst来实现，
     * 但前者的性能会更好为O(n)，而sorted的成本是O(nlogn)。
     * 同时它们作为特殊的reduce方法被独立出来也是因为求最大最小值是很常见的操作。
     */
    @Test
    public void minMaxTest(){
        String a = Stream.of("cat", "show", "lucky", "ft").max(Comparator.comparingInt(String::length)).orElse("N");
        log.info("{}", a);
    }

    /**
     * distinct：去重
     */
    @Test
    public void distinctTest(){
        List<String> a = Stream.of("cat", "show", "lucky", "cat", "cat").distinct().collect(Collectors.toList());
        log.info("{}", a);
    }

    /**
     * allMatch、anyMatch、noneMatch 判断匹配
     * 它们都不是要遍历全部元素才能返回结果。例如allMatch只要一个元素不满足条件，就skip剩下的所有元素，返回false
     */
    @Test
    public void matchTest(){
        List<Student> list = Arrays.asList(new Student("lucky", 19), new Student("show", 25), new Student("hh", 22));
        boolean b = list.stream().allMatch(x -> x.getAge() > 18);
        log.info("{}", b);
    }

    /**
     * 字符串转流
     */
    @Test
    public void test01(){
        String s = "1357924680";
        Integer i = s.chars().boxed().map(x -> x - '0').sorted().reduce((x, y) -> x * 10 + y).orElse(0);
        log.info("{}", i);
    }


}
