package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public final String pathPhrases = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public final String separator = System.lineSeparator();

    private List<Comment> comments = new ArrayList<>();

    public final int count = 50;

    private List<String> phrases;

    private UserGenerator userGenerator;

    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(pathPhrases);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }


    @Override
    public void generate() {
        comments.clear();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct().limit(3).forEach(ints::add);
        for (int i = 0; i < count; i++) {
            String comment = "";
            comment = comment.concat(phrases.get(ints.get(0)))
                    .concat(separator)
                    .concat(phrases.get(ints.get(1)))
                    .concat(separator)
                    .concat(phrases.get(ints.get(2)));
            comments.add(new Comment(comment, userGenerator.randomUser()));
        }
    }
}
