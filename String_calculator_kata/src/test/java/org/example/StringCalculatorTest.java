package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    @DisplayName("Empty string returns zero")
    void emptyStringReturnsZero() {
        int actual = calc.add("");
        int expected = 0;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "5", "7", "9", "10"})
    @DisplayName("Single number returns number")
    void oneNumberReturnsNumber() {
        int actual = calc.add("5");
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers returns sum")
    void numbersReturnsSum() {
        int actual = calc.add("1,2,3");
        int expected = 6;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers returns sum")
    void numbersReturnsSumSeparated() {
        int actual = calc.add("1\n2,3");
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers returns sum")
    void numbersReturnsSumDesignatedDelimiter() {
        int actual = calc.add("//;1;2");
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing for negative numbers")
    void singleNegativesNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("//;1;2\n-3");
        });

        String expectedMessage = "Negative numbers aren't allowed negative numbers: [-3]";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);


    }

    @Test
    @DisplayName("Testing for negative numbers")
    void multipleNegativesNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("//;1;2\n-3;-4");
        });

        String expectedMessage = "Negative numbers aren't allowed negative numbers: [-3, -4]";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);


    }

    @Test
    @DisplayName("Testing for numbers greater than 1000")
    void greaterThan1000() {
        int actual = calc.add("//;2;1001");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing for longer delimiters")
    void longerDelimiters() {
        int actual = calc.add("//[***]\n1***2***3");
        int expected = 6;
        assertEquals(expected, actual);
    }
}
