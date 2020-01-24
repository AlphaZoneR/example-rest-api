package edu.apaczai.model;

public final class Activity extends Storable {
    private Difficulty difficulty;
    private String title;
    private String description;
    private Status status;

    public Activity() {}

    public Activity(Difficulty difficulty, String title, String description, Status status) {
        this.difficulty = difficulty;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
}
