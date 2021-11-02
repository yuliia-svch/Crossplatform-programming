package com.test;

import com.regex.Main;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RegexTests {
    @Test
    public void oneSentenceTest() {
        String text = "She eyes me like a Pisces when I am weak.";
        Assertions.assertEquals("am", Main.find(text).get(0));
    }

    @Test
    public void moreThan1SentenceTest() {
        String text = "She eyes me like a Pisces when I am weak." +
                "I've been locked inside your heart-shaped box for weeks.";
        Assertions.assertEquals(2, Main.find(text).size());
    }

    @Test
    public void noTextTest() {
        Assertions.assertTrue(Main.find("").isEmpty());
    }

    @Test
    public void textHasOneWordTest() {
        Assertions.assertTrue(Main.find("hey").isEmpty());
    }
}
