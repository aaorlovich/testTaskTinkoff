package ru.tinkoff.orlovich.tasks.task7;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static ru.tinkoff.orlovich.tasks.task7.ObjectsPool.logger;
import static ru.tinkoff.orlovich.tasks.task7.ObjectsPool.threadImitateWork;

public class ObjectPoolTest {


    @Test(description = "TASK 7: Тест получения объектов потоками. Смотрим вывод на консоль")
    public void poolObjectTest() {
        ObjectsPool<Integer> objPool = ObjectsPool.getPool(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        IntStream.rangeClosed(1, 10).parallel()
                .forEach(value -> {
                    try {
                        ObjectsPool.ObjectFromPool<Integer> objectFromPool = objPool.getObjectFromPool();
                        logger.info(String.format("First test. Thread %s have unique id %s", value, objectFromPool.get()));
                        threadImitateWork();
                        objectFromPool.returnObjectToPool();
                    } catch (InterruptedException e) {
                        logger.info("HELP");
                    }
                });
    }
}
