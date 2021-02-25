package com.example.java8to11;

import java.time.Duration;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Foo {
    public static void main(String[] args) {
        /* 첫번째
        // 8 이전 동작 하나(한줄) - 익명 내부 클래스
        RunSomething oneLineBefore = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // 8 이후 동작 하나(한줄) - 람다
        RunSomething oneLineAfter = () -> System.out.println("Hello");

        // 8 이전 동작 여러개(여러줄) - 익명 내부 클래스
        RunSomething moreLineBefore = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("Lambda");
            }
        };

        // 8 이후 동작 여러개(여러줄) - 람다
        RunSomething moreLineAfter = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };

        // 사용가능 - 파라매터, 리턴 모두 가능
        moreLineAfter.doIt(); */

        /* 두번째
        RunSomething intSomething = (number) -> number + 10;

        // 파라매터를 받을경우에 동일값에 동일결과를 표출해내지 못하면 함수형 인터페이스라 할 수 없다
        // 상태값에 의존하는경우 혹은 외부의 값을 변경하려는 경우 위에 위반되어 순수한 함수라 볼 수 없음
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1));
        System.out.println(intSomething.doIt(1)); */

        /* 세번째
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(5));

        Function<Integer, Integer> multiplyFunction = (number) -> number * 2;
        System.out.println(multiplyFunction.apply(3));

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiplyFunction);
        System.out.println(multiply2AndPlus10.apply(2));

        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiplyFunction);
        System.out.println(plus10AndMultiply2.apply(2));

        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10); */

        /* 네번째
        Supplier<Integer> get10 = () -> 10;
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Foo foo = new Foo();
        foo.run(); */

        /* 다섯번째 - 객체에서 꺼내쓰기 가능 ( = 메소드 레퍼런스)
        // UnaryOperator<String> hi = (s) -> "hi" + s;
        UnaryOperator<String> hi = Greeting::hi;

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;  // 호출한거 아님
        System.out.println(hello.apply("something"));   // 호출

        Function<String, Greeting> getParamInstance = Greeting::new;    // 파라매타 String 인 생성자 사용
        Greeting sang = getParamInstance.apply("sang");
        System.out.println(sang.getName());
        Supplier<Greeting> getEmptyInstance = Greeting::new;            // 파라매타 없는 생성자 사용

        List<String> names = new ArrayList<>();
        names.add("AAA");
        names.add("BBB");
        names.add("CCC");
        names.add("DDD");

        names.forEach(System.out::println);
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> stringSpliterator = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("====================");
        while (stringSpliterator.tryAdvance(System.out::println));

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        System.out.println("====================");
        names.forEach(System.out::println);

        // 싱글스레드
        List<String> collect = names.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println("====================");
        collect.forEach(System.out::println);

        // 멀티쓰레드
        List<String> collect1 = names.parallelStream()
                .map((s) -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toLowerCase();
                })
                .collect(Collectors.toList());
        System.out.println("====================");
        collect1.forEach(System.out::println); */

        /* stream api
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("=========================");
        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("====================");
        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                //.filter(oc -> !oc.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("================================");
        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("=============================================");
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("========================================================================");
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 배고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("=================================================");
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);

        System.out.println("===============================================================");
        System.out.println("스프링 수업 중에 제목에 spring 들어간 제목만 모아서 List로 만들기");
        List<String> includeSpringClasses = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        includeSpringClasses.forEach(System.out::println); */

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // Optional 미사용 버전
        OnlineClass spring_security = new OnlineClass(6, "spring security", true);
        Progress progress = spring_security.getProgress();
        if (progress != null) {
            System.out.println("before java 8 = " + progress.getStudyDuration());
        }

        // Optional 사용 버전 - Optional은 return 으로만 사용하자 !!
        Optional<Progress> progress1 = spring_security.getProgress();
    }

    private void run() {
        // 공통적으로 참조 가능 (final 이거나 사실상 final 인 경우 - 스코프안에서 값이 변하지 않을 경우)
        final int baseNumber = 10;

        // 로컬 클래스 - 변수 스코프 가능
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스 - 변수 스코프 가능
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다 - 변수 스코프 불가능, 상위 스코프를 공유(여기서는 run)
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);

        printInt.accept(10);
    }
}
