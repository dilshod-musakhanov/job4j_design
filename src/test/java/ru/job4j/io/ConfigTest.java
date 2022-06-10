package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("dilshod"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsEmptyThenIllegalArgumentException() {
        String path = "./data/pair_key_is_empty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsEmptyThenIllegalArgumentException() {
        String path = "./data/pair_value_is_empty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenWrongKeyInsertedThenUnsupportedOperationException() {
        String path = "./data/pair_key_value.properties";
        Config config = new Config(path);
        config.load();
        config.value("hiber");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenEmptyFileButCorrectKeyThenUnsupportedOperationException() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
        config.value("hibernate.dialect");
    }

    @Test
    public void whenTwoAssignmentOperators() {
        String path = "./data/two_assignments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value=1"));
    }

    @Test
    public void whenMethodReadsBetweenBlankLines() {
        String path = "./data/blank_lines_in_between.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value"));
    }

}