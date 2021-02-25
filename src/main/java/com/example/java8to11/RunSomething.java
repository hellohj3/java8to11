package com.example.java8to11;

@FunctionalInterface
public interface RunSomething {

    // 추상 메서드가 하나만 있으면 = 함수형 인터페이스(두개 안됨, abstract 는 인터페이스에서는 제거 가능)
    // @FunctionalInterface 이 있으면 위반되었을때 컴파일 에러 발생 - 붙여쓰는게 좋음
    // 첫번째
    //void doIt();

    // 두번째
    int doIt(int number);
}
