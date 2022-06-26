package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsantev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 5f;
        double d = 6d;
        boolean bool = true;
        char ch = 'c';
        LOG.debug("The eight primitives defined in Java are :\n "
                + "byte : {}, short : {}, int : {}, long : {}, "
                + "float : {}, double : {}, boolean : {}, char : {}",
                b, s, i, l, f, d, bool, ch
        );
    }
}
