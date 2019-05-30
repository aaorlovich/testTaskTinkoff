package ru.tinkoff.orlovich.tasks.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Orlovich Artem
 * <p>
 * Задача 1
 * Напишите программу, которая выводит на экран числа от 1 до 100. При этом вместо чисел, кратных
 * 3, программа должна выводить слово Fizz, а вместо чисел, кратных 5 — слово Buzz. Если число
 * кратно 15, то программа должна выводить слово FizzBuzz.
 * Для 15 выводим FizzBuzz, а не FizzBuzzFizzBuzz.
 * <p>
 * Класс имеет метод getStringAndPrint который работает с любым диапазоном положительных чисел
 */


public class NumbersPrinter {

    Logger logger = LoggerFactory.getLogger(NumbersPrinter.class);


//    public static void main(String[] args) {
//        NumbersPrinter numbersPrinter = new NumbersPrinter();
//        numbersPrinter.print(1, 500);
//        numbersPrinter.getStringAndPrint(1, 500);
//    }

    /*
     * Метод вполне справляется с задачей, пишет в консоль(а так же в лог файл) то что нужно но совершенно не тестируемая
     * протестировать можно только исползовав стандартный вывод в который подложить ByteArrayOutputStream, а это дополнительные сложности
     * */
    @Deprecated
    public void print(int lower, int higher) {
        for (int i = lower; i <= higher; i++) {
            if (i % 15 == 0) {
                logger.info("FizzBuzz");
            } else if (i % 3 == 0) {
                logger.info("Fizz");
            } else if (i % 5 == 0) {
                logger.info("Buzz");
            } else
                logger.info("" + i);
        }
    }

    /*
     * Метод проходит циклом по заданному диапазону чисел и агрегирует в себе строку которую потом возвращает и пишет
     * в консоль а так же в лог файл
     * */
    public String getStringAndPrint(int lower, int higher) {
        StringBuilder condoleLog = new StringBuilder();
        for (int i = lower; i <= higher; i++) {
            if (i % 15 == 0) {
                condoleLog.append("FizzBuzz\n");
            } else if (i % 3 == 0) {
                condoleLog.append("Fizz\n");
            } else if (i % 5 == 0) {
                condoleLog.append("Buzz\n");
            } else
                condoleLog.append(i + "\n");
        }
        logger.info(condoleLog.toString());
        return condoleLog.toString();
    }


}
