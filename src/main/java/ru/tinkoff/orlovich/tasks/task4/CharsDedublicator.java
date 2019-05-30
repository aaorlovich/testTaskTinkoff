package ru.tinkoff.orlovich.tasks.task4;

public class CharsDedublicator {

    /*Задача про строку.
    Напишите функцию, которая будет из входной строки удалять парные, идущие друг за другом буквы
    в одну и на выходе вернуть строку, которая не будет иметь парных, идущих друг за другом букв.
          Пример: aaabccddd =&gt; abd, baab =&gt; пусто. Вывод – стандартный вывод.
    */


    public static void main(String[] args) {
        CharsDedublicator dedublicator = new CharsDedublicator();

        String aaaabbddddffff = dedublicator.deleteCharsPairFromString("baabaabasdaaassdssss");

        System.out.println(aaaabbddddffff);
    }

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
//
//    }

    public String deleteCharsPairFromString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        } else if (input.length() == 1) {
            return input;
        }
        return charProcess(input);

    }

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
