# ft-jdk8
学习 JDK8 新特性

 * jdk8_01_Lambda表达式
 
        Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）
        Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力
        
 * jdk8_02_函数式接口_FunctionalInterface
 
        函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
        
 * jdk8_03_接口默认方法and静态方法
 
        JDK8 开始新加了接口默认方法，便于接口的扩展
        接口中同时还可以定义静态方法，静态方法通过接口名调用
        
 * jdk8_04_流操作_StreamApi
 
        新添加的 Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
        
 * jdk8_05_Optional容器
 
        Optional 类是个容器：它可以保存类型T的值，或者仅仅保存null
        Optional 类的引入很好的解决空指针异常
        
 * jdk8_06_DateTimeApi
 
        加强对日期与时间的处理（线程安全）
        
 * jdk8_07_编码解码器_Base64
 
        Base64工具类提供了一套静态BASE64编解码器，可以获取下面三种BASE64编解码方案：
            基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/
            URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件
            MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割
