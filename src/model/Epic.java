package model;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    public Epic(String title, String description, int id, TaskStatus status, ArrayList<Integer> subtaskIds) {
        super(title, description, id, status);
        this.subtaskIds = subtaskIds;
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Epic otherEpic = (Epic) obj;
        return Objects.equals(title, otherEpic.title) &&
                Objects.equals(description, otherEpic.description) &&
                id == otherEpic.id &&
                Objects.equals(status, otherEpic.status) &&
                Objects.equals(subtaskIds, otherEpic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, id, status, subtaskIds);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskIds=" + subtaskIds +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}