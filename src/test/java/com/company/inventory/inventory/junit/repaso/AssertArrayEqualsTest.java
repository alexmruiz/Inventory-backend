package com.company.inventory.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTest {

    @Test
    public void assertArrayTest(){

        String [] arre = {"aa", "bb"};
        String [] arre1 = {"aa", "bb"};
        //String [] arre2 = {"bb", "bb", "cc"};

        assertArrayEquals(arre1, arre);
        //assertArrayEquals(arre, arre2);
    }

}
