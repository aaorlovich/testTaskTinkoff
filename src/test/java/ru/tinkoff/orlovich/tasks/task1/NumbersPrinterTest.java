package ru.tinkoff.orlovich.tasks.task1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

public class NumbersPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final NumbersPrinter numbersPrinter = new NumbersPrinter();

    @BeforeClass
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterClass
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    //как видно тестировать системный вывод добавляет сложностей с использование сквозных функций
//    String expectedTestDataPrint = "---- start test: printTest\r\n" +
//            "\t--- desc: Проверка вывода в консоль\r\n" +
//            "1\r\n" +
//            "2\r\n" +
//            "Fizz\r\n" +
//            "4\r\n" +
//            "Buzz\r\n" +
//            "Fizz\r\n" +
//            "7\r\n" +
//            "8\r\n" +
//            "Fizz\r\n" +
//            "Buzz\r\n" +
//            "11\r\n" +
//            "Fizz\r\n" +
//            "13\r\n" +
//            "14\r\n" +
//            "FizzBuzz\r\n";
//
//    @Test(description = "Проверка вывода в консоль")
//    public void printTest() {
//        numbersPrinter.print(1, 15);
//        assertEquals(outContent.toString(), expectedTestDataPrint);
//    }

    //Некрасивая строчка ожидаемого значения, но это мелочи потому что метод теперь тестируется и можно в дальнейшем использовать например DataProvider
    String expectedTestDataGetStringAndPrint = "1\n" + "2\n" + "Fizz\n" + "4\n" + "Buzz\n" + "Fizz\n" + "7\n" + "8\n" + "Fizz\n" + "Buzz\n" + "11\n" + "Fizz\n" + "13\n" + "14\n" + "FizzBuzz\n" + "16\n" + "17\n" + "Fizz\n" + "19\n" + "Buzz\n" + "Fizz\n" + "22\n" + "23\n" + "Fizz\n" + "Buzz\n" + "26\n" + "Fizz\n" + "28\n" + "29\n" + "FizzBuzz\n" + "31\n" + "32\n" + "Fizz\n" + "34\n" + "Buzz\n" + "Fizz\n" + "37\n" + "38\n" + "Fizz\n" + "Buzz\n" + "41\n" + "Fizz\n" + "43\n" + "44\n" + "FizzBuzz\n" + "46\n" + "47\n" + "Fizz\n" + "49\n" + "Buzz\n" + "Fizz\n" + "52\n" + "53\n" + "Fizz\n" + "Buzz\n" + "56\n" + "Fizz\n" + "58\n" + "59\n" + "FizzBuzz\n" + "61\n" + "62\n" + "Fizz\n" + "64\n" + "Buzz\n" + "Fizz\n" + "67\n" + "68\n" + "Fizz\n" + "Buzz\n" + "71\n" + "Fizz\n" + "73\n" + "74\n" + "FizzBuzz\n" + "76\n" + "77\n" + "Fizz\n" + "79\n" + "Buzz\n" + "Fizz\n" + "82\n" + "83\n" + "Fizz\n" + "Buzz\n" + "86\n" + "Fizz\n" + "88\n" + "89\n" + "FizzBuzz\n" + "91\n" + "92\n" + "Fizz\n" + "94\n" + "Buzz\n" + "Fizz\n" + "97\n" + "98\n" + "Fizz\n" + "Buzz\n";

    @Test(description = "TASK 1 : Проверка метода который агрегирует числа кратные заданным , возвращает в виде строки и печатает в консоль")
    public void getStringAndPrint() {
        String stringAndPrint = numbersPrinter.getStringAndPrint(1, 100);
        assertEquals(stringAndPrint, expectedTestDataGetStringAndPrint);
    }


}
