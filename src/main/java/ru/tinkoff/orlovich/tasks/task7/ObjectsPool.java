package ru.tinkoff.orlovich.tasks.task7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ObjectsPool<T> {

    static Logger logger = LoggerFactory.getLogger(ObjectsPool.class);

    private static BlockingQueue<ObjectFromPool> queue;
    private static ObjectsPool<?> instance = null;


    private ObjectsPool(Collection<T> objects) {
        List<ObjectFromPool> collect = objects.stream().map(s -> new ObjectFromPool(s)).collect(Collectors.toList());
        queue = new ArrayBlockingQueue<>(objects.size(), true, collect);
    }

    public static <T> ObjectsPool<T> getPool(Collection<T> objects) {
        if (instance == null) {
            instance = new ObjectsPool<>(objects);
        }
        return (ObjectsPool<T>) instance;
    }

    public ObjectFromPool<T> getObjectFromPool() throws InterruptedException {
        if (queue.isEmpty()) {
            queue.wait();
        }
        return queue.take();
    }


    public static class ObjectFromPool<T> {
        T value;

        public ObjectFromPool(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void returnObjectToPool() {
            queue.add(new ObjectFromPool(value));
            logger.info("Object " + value.toString() + " Returned in pool!");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ObjectsPool<Integer> objPool = ObjectsPool.getPool(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));

        IntStream.rangeClosed(1, 40).parallel()
                .forEach(value -> {
                    try {
                        ObjectFromPool<Integer> objectFromPool = objPool.getObjectFromPool();
                        System.out.println(String.format("First test. Thread %s have unique id %s", value, objectFromPool.get()));
                        threadImitateWork();
                        objectFromPool.returnObjectToPool();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void threadImitateWork() {
        Random random = new Random();
        int i = random.nextInt(10);
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {

        }

    }
}
