package com.example.user.Leszy;

public class NotesBuilder {

    public NotesBuilder() {}

    private String title, content;

    public NotesBuilder(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
