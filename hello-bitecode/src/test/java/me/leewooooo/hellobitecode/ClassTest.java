package me.leewooooo.hellobitecode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassTest {

    @Test
    void isFull(){
        Class mathClass = new Class();

        mathClass.numberOfMaxAttendees = 10;
        mathClass.numberOfMaxAttendees = 5;

        Assertions.assertThat(mathClass.isEnrollmentFull()).isFalse();
    }
}