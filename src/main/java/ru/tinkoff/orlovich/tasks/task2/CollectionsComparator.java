package ru.tinkoff.orlovich.tasks.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionsComparator {

    Logger logger = LoggerFactory.getLogger(CollectionsComparator.class);

    public static void main(String[] args) {

        List<String> standard = Arrays.asList("A", "B", "C", "D");
        List<String> outer = Arrays.asList("A", "B", "C", "D", "E");


        List<String> standard2 = Arrays.asList("A", "B", "C", "D");
        List<String> outer2 = Arrays.asList("A", "B", "C", "E");
        CollectionsComparator comparator = new CollectionsComparator();

        comparator.compare(standard, outer);
        comparator.compare(standard2, outer2);
    }


    public <T> void compare(Collection<T> standard, Collection<T> outer) {
        logger.info("Лишние объекты которые содержаться во внешнем источнике (нет в эталонном источнике): ");
        outer.stream().forEach(s -> {
            if (!standard.contains(s)) {
                logger.info(s.toString());
            }
        });

        logger.info("Объекты, которых нет в списке из внешнего источника, но присутствуют в эталонном списке: ");
        standard.stream().forEach(s -> {
            if (!outer.contains(s)) {
                logger.info(s.toString());
            }
        });
    }


}
