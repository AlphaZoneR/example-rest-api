package edu.apaczai.model;

public final class Task extends Storable {
    private Difficulty difficulty;
    private String title;
    private String description;
    private Status status;

    public Task() {}

    public Task(Difficulty difficulty, String title, String description, Status status) {
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

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "difficulty=" + difficulty +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
