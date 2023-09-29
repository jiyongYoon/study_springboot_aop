package com.example.springaop;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryTest {

    @Autowired
    private Library library;

    @Test
    void test() {
        //given
        Library library = new Library();
        library.setName("도서관");

        //when
        String result = library.getName();

        //then
        assertThat(result).isEqualTo("도서관");
    }

    @Test
    void visitedByTest() {
        //given
        User user = new User();
        user.setName("스프링");

        library.setName("행복 도서관");

        //when
        //then
        library.visitedBy(user);
    }

    @Test
    void visitToLibrary() {
        //given
        User user = new User();
        user.setName("김철수");

        library.setName("건강 도서관");
        library.setVisitCountByUser(11);

        //when
        //then
        user.visitTo(library);
    }
}