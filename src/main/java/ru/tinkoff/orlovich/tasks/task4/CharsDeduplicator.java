package ru.tinkoff.orlovich.tasks.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Orlovich Artem
 * <p>
 * Задача 4
 * Задача про строку.
 * Напишите функцию, которая будет из входной строки удалять парные, идущие друг за другом буквы
 * в одну и на выходе вернуть строку, которая не будет иметь парных, идущих друг за другом букв.
 * Пример: aaabccddd =&gt; abd, baab =&gt; пусто. Вывод – стандартный вывод.
 * <p>
 * Класс имеет метод getStringAndPrint который работает с любым диапазоном положительных чисел
 */
public class CharsDeduplicator {

    Logger logger = LoggerFactory.getLogger(CharsDeduplicator.class);

    // этод метод удаляет любые повторяющиеся последовательности до 1 символа
//    public String deduplicateCharsFromString(String string) {
//        if (string == null || string.isEmpty()) {
//            return "";
//        } else if (string.length() == 1) {
//            return string;
//        }
//        char[] chars = string.toCharArray();
//        StringBuilder temp = new StringBuilder();
//        temp.append(chars[0]);
//        int cursor = 0;
//        for (int i = 1; i < chars.length; i++) {
//            if (chars[cursor] != chars[i]) {
//                temp.append(chars[i]);
//                cursor = i;
//            }
//        }
//        return temp.toString();
//    }

    /**
     * Публичный метод, с проверкой  и вызовом основного процессинга
     * */
    public String deleteCharsPairFromString(String input) {
        logger.info("Input string: " + input);

        if (input == null || input.isEmpty()) {
            return "";
        } else if (input.length() == 1) {
            return input;
        }
        String afterProcessing = charProcess(input);
        logger.info("After dedup processing: " + afterProcessing);
        return afterProcessing;

    }


    /*основная логика работы метода удаления дубликатов
     *
     * Полученную строку преобразуем в массив char
     * поочередно начинаем искать двойные последовательности и перескакивать в случае если они есть на следующие позиции
     * добавляем элемент в кэш, по окончанию проверки если были двойные последовательности проходим по кэшу еще раз
     * дабы не появились новые
     * на выходе получаем строку в которой нет парных значений
     *
     */

    private String charProcess(String input) {
        boolean isDedup = true;
        char[] chars = input.toCharArray();
        String result = "";

        while (isDedup) {
            if (result.length() == 1) {
                return result;
            }
            StringBuilder temp = new StringBuilder();
            isDedup = false;
            int cursor = 0;
            for (int i = 1; i < chars.length; i++) {
                if (chars[cursor] == chars[i]) {
                    isDedup = true;
                    cursor = ++i;
                    if (i + 1 == chars.length) {
                        temp.append(chars[i]);
                        break;
                    }
                } else {
                    temp.append(chars[cursor]);
                    if (i + 1 == chars.length) {
                        temp.append(chars[i]);
                        break;
                    }
                    cursor = i;
                }
            }
            chars = temp.toString().toCharArray();
            result = temp.toString();
        }

        return result;
    }

}
