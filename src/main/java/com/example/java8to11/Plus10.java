package com.example.java8to11;

import java.util.function.Function;
/**
 * JAVA 기본 함수형 인터페이스 구현해서 사용
 */
public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
