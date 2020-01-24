package edu.apaczai.backend.data.dao;

import edu.apaczai.backend.data.model.Task;
import edu.apaczai.backend.data.model.Difficulty;
import edu.apaczai.backend.data.model.Status;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActivityDao {
    private static final Map<Long, Task> activityMap = new ConcurrentHashMap<Long, Task>();
    private static Long COUNT = 0L;

    public ActivityDao() {
        this.create(new Task(Difficulty.EASY, "Doggie", "Walk your dog for 1 hour!", Status.PENDING));
    }

    public Task create(final Task task) {
        if (Objects.nonNull(task.getId())) {
            activityMap.put(task.getId(), task);
        } else {
            task.setId(COUNT);
            activityMap.put(COUNT, task);
            COUNT++;
        }

        return task;
    }

    public Task read(final Long id) {
        return activityMap.getOrDefault(id, null);
    }

    public Task update(final Task task) {
        if (Objects.nonNull(task.getId())) {
            activityMap.put(task.getId(), task);
            return task;
        }

        return null;
    }

    public Task delete(final Task task) {
        if (Objects.nonNull(task.getId())) {
            activityMap.remove(task.getId(), task);
            return task;
        }

        return null;
    }

    public List<Task> readAll() {
        return new ArrayList<>(activityMap.values());
    }
}
