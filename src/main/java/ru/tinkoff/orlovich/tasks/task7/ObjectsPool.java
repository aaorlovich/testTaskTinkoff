package ru.tinkoff.orlovich.tasks.task7;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ObjectsPool<T> {

    int maxPoolSize = 5;
    private Collection<T> objects;
    private BlockingQueue<T> queue;
    private static ObjectsPool<?> instance = null;

    private ObjectsPool(Collection<T> objects) {
        this.objects = objects;
    }

    private ObjectsPool(int maxPoolSize, Collection<T> objects) {
        queue = new ArrayBlockingQueue<T>(objects.size(), true, objects);
        this.maxPoolSize = maxPoolSize;
    }

    public static <T> ObjectsPool<T> getPool(int maxPoolSize, Collection<T> objects) {
        if (instance == null) {
            instance = new ObjectsPool<>(maxPoolSize, objects);
        }
        return (ObjectsPool<T>) instance;
    }


    public ObjectFromPool getObjectFromPool() throws InterruptedException {
        if (queue.isEmpty()) {
            queue.wait();
        }
        return new ObjectFromPool(queue.take());
    }


    class ObjectFromPool {
        T value;

        public ObjectFromPool(T value) {
            this.value = value;
        }


        public T get() {
            return value;
        }


        public void returnObject() {
            queue.add(value);
            queue.notify();
        }
    }


    public static void main(String[] args) {
        ObjectsPool<Integer> pool = ObjectsPool.getPool(10, Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));


        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    pool.getObjectFromPool();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

    }

}
