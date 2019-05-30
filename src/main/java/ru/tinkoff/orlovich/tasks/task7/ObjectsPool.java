package ru.tinkoff.orlovich.tasks.task7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;


/**
 * @author Orlovich Artem
 * <p>
 * Задача 7
 * Задача на знание Java
 * Необходимо реализовать пул (см примеры jdbc connection pool, thread pool etc) цифр для
 * многопоточного приложения. Дан массив интов [0..9]. Необходимо реализовать пул и написать
 * функцию с условием:
 * - каждый вызов функции может взять из пула только 1 инт, который в данный момент находится в
 * пуле.
 * - если в пуле не осталось интов необходимо чтобы поток остановился и подождал появления инта в
 * пуле
 * - после работы (достаточно поставить thread.sleep в секундах с рандомным ожиданием [1..10]
 * секунд) поток должен вернуть инт в пул
 * Использование Thread не обязательно, но допустимо. Главное условие – многопоточность и
 * параллельность. Учтите, что при количестве потоков &lt;= 10 каждый поток получит в свое управление
 * строго 1 уникальный инт для себя; при количестве потоков &gt; 10 первые 10 потоков-вызовов получат
 * в свое управление строго 1 уникальный инт для себя, поработать (считай поспать через thread.sleep)
 * <p>
 * и вернуть в пул свой инт – в этот момент оставшиеся потоки должны взять появляющиеся в пуле
 * инты для себя.
 * <p>
 * Класс в своей реализации использует блокирующую очередь, может быть от меня ожидали какие нибудь 2 листа с использованием синхронизации
 * но в 4 утра если честно мало что приходит в голову, а очередь сама справляется с блокировками
 * <p>
 * Класс обязывает возвращать объекты в пулл и кстате работает с любым объектом и не только интами
 * <p>
 * возврат происходит с помощью специального вложенного объекта который содержит в себе значения взятое из самого пула
 * той коллекции которую мы изначально создали для работы с пуллом
 * <p>
 * <p>
 * ObjectFromPool - класс ячейка которому можно сказать вернуться на место то что взяли
 */
public class ObjectsPool<T> {

    static Logger logger = LoggerFactory.getLogger(ObjectsPool.class);

    private static BlockingQueue<ObjectFromPool> queue;


    //Сам пулл выступает в качесве Singleton и инициализируется единажды это коряво потому что передавая новую коллекцию объектов
    // сам пулл не измениться, но таких условий вроде бы не было
    //работает
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


    //метод получение ячейки с объектом
    public ObjectFromPool<T> getObjectFromPool() throws InterruptedException {
        return queue.take();
    }


    //сама ячейка с объектом  и возможностью положить его обратно
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


    //утилитарно заставляем поток поспать, сделать вид что он работает
    public static void threadImitateWork() {
        Random random = new Random();
        int i = random.nextInt(10);
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {

        }

    }
}
