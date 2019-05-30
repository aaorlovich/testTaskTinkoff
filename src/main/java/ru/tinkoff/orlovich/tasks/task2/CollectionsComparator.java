package ru.tinkoff.orlovich.tasks.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;


/**
 * @author Orlovich Artem
 * <p>
 * Задача 2
 * НЗадача про сравнение двух коллекций
 * На вход функции подаются две коллекции строк. Первая коллекция – эталонные строки, вторая –
 * строки, которые получили из внешнего источника. Напишите функцию, которая бы выводила в
 * стандартный вывод:
 * а) строки, которые «лишние» в списке из внешнего источника, т.е. их нет в эталонном списке
 * б) строки, которых нет в списке из внешнего источника, но присутствуют в эталонном списке
 * Напишите такую функцию.
 * Пример: эталонные строки List«A, B, C, D»; строки из внешнего источника List«A, B, C, D, E» =&gt; а)
 * вывести «E»; б) ничего не выводим. Эталонные строки List«A, B, C, D»; строки из внешнего
 * источника List«A, B, C, E» =&gt; а) выводим «E»; б) выводим «D».
 * <p>
 * Класс имеет метод compareAndPrintDifferent сравнивающий две коллекции объектов и выдающий сообщение о разнице в этих коллекциях
 * по сути может работать с любыми объектами у которых правильно определен метод equals и не только строками
 */
public class CollectionsComparator {

    //logger
    Logger logger = LoggerFactory.getLogger(CollectionsComparator.class);

//    public static void main(String[] args) {
//
//        List<String> standard = Arrays.asList("A", "B", "C", "D");
//        List<String> outer = Arrays.asList("A", "B", "C", "D", "E");
//
//        List<String> standard2 = Arrays.asList("A", "B", "C", "D");
//        List<String> outer2 = Arrays.asList("A", "B", "C", "E");

//        CollectionsComparator comparator = new CollectionsComparator();
//        comparator.compareAndPrintDifferent(standard, outer);
//        comparator.compareAndPrintDifferent(standard2, outer2);
//    }


    /*
     * методу поступают на вход 2 коллекции - эталонная (standard) и из внешнего источника - outer
     * Алгоритм не самый оптимальный за 2 итерации, сначала ищем лишние объекты во внешнем истонике
     * потом ищем наоборот
     * Визуального тестирования фугкции хватило бы но раз должно тестироваться пришлось добавить возвращаемую строку
     *
     * */
    public <T> String compareAndPrintDifferent(Collection<T> standard, Collection<T> outer) {
        StringBuilder consoleLog = new StringBuilder();

        logger.info("Сравнение коллекций");
        //первая итерация поиска лишних объектов во внешней коллекции
        consoleLog.append("Лишние объекты которые содержаться во внешнем источнике (нет в эталонном источнике): \n");
        outer.stream().forEach(s -> {
            if (!standard.contains(s)) {
                consoleLog.append(s.toString() + "\n");
            }
        });

        //вторая итерация поиска объектов которые есть в эталонной коллекции но нет во внешней
        consoleLog.append("Объекты, которых нет в списке из внешнего источника, но присутствуют в эталонном списке: \n");
        standard.stream().forEach(s -> {
            if (!outer.contains(s)) {
                consoleLog.append(s.toString() + "\n");
            }
        });

        logger.info(consoleLog.toString());
        return consoleLog.toString();
    }


}
