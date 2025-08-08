package com.company.inventory.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    Calculadora calc;

    @BeforeAll
    public static void primero() {
        System.out.println("primero");
    }

    @AfterAll
    public static void ultimo() {
        System.out.println("ultimo");
    }

    @AfterEach
    public void ultimoPorCadaPrueba() {
        System.out.println("ultimpo por cada prueba");
    }

    @BeforeEach
    public void primeroPorCadaPrueba() {
        System.out.println("primero por cada prueba");
        calc = new Calculadora();
    }

    @Test
    @DisplayName("prueba de sumar calculadora")
    public void sumarTTest() {
        assertEquals(2, calc.sumar(1, 1));
        assertFalse(calc.sumar(2, 2) == 5);
    }

    @Test
    @Disabled("desabilitada")
    public void restarTest() {
        assertEquals(4, calc.restar(5, 1));
        // assertFalse(calc.sumar(2, 2) == 5);
    }

    @Test
    public void multiplicar() {
        assertEquals(25, calc.multiplicar(5, 5));
        // assertFalse(calc.sumar(2, 2) == 5);
    }

    @Test
    public void dividir() {
        assertTrue(calc.dividir(10, 2) == 5);
        // assertFalse(calc.sumar(2, 2) == 5);
    }
}
