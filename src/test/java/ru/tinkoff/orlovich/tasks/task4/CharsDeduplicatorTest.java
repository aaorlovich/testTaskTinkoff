package ru.tinkoff.orlovich.tasks.task4;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharsDeduplicatorTest {

    private CharsDeduplicator deduplicator = new CharsDeduplicator();

    /*
     * пара тестов согласно заданию
     * */
    @Test(description = "TASK 4: Проверка дедубликации пар значений тест 1")
    public void deleteCharsPairFromStringTest1() {
        String expected = "abd";
        String input = "aaabccddd";
        String s = deduplicator.deleteCharsPairFromString(input);

        assertThat(s).isEqualTo(expected);
    }

    @Test(description = "TASK 4: Проверка дедубликации пар значений тест 2")
    public void deleteCharsPairFromStringTest2() {
        String input = "baab";
        String expected = "";
        String s = deduplicator.deleteCharsPairFromString(input);

        assertThat(s).isEqualTo(expected);
    }
}
