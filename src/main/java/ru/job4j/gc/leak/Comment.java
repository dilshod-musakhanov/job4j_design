package ru.job4j.gc.leak;

import java.util.Objects;

public class Comment {

    private String text;

    private User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(getText(), comment.getText())
                && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), user);
    }
}
