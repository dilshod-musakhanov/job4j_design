package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Disabled
public class TemplateGeneratorTest {

    private final static String TEMPLATE = "I am a ${name}, Who are ${subject}? ";

    @Ignore
    @Test
    public void whenTemplateProduce() {
        TemplateGenerator tg = new TemplateGenerator();
        Map<String, String> map = Map.of("name", "Peter", "subject", "you");
        String expected = "I am a Peter, Who are you? ";
        assertThat(expected).isEqualTo(tg.produce(TEMPLATE, map));
    }

    @Test()
    public void whenMapIsEmpty() {
        TemplateGenerator tg = new TemplateGenerator();
        Map<String, String> map = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> tg.produce(TEMPLATE, map));
    }

    @Test()
    public void whenKeyIsAbsent() {
        TemplateGenerator tg = new TemplateGenerator();
        Map<String, String> map = Map.of("we", "job", "you", "student");
        assertThrows(IllegalArgumentException.class, () -> tg.produce(TEMPLATE, map));
    }

    @Test()
    public void whenTemplateIsNotValid() {
        TemplateGenerator tg = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}, ${name}? ";
        Map<String, String> map = Map.of("name", "Peter", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> tg.produce(template, map));
    }
}