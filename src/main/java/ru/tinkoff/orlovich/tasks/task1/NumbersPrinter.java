package ru.tinkoff.orlovich.tasks.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumbersPrinter {

    Logger logger = LoggerFactory.getLogger(NumbersPrinter.class);


    public static void main(String[] args) {
        NumbersPrinter numbersPrinter = new NumbersPrinter();
        numbersPrinter.print(1, 500);
    }

    public void print(int lower, int higher) {
        for (int i = lower; i < higher; i++) {
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


}
