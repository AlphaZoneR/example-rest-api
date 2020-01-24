package edu.apaczai.backend.data.dao;

import edu.apaczai.backend.data.model.Activity;
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
    private static final Map<Long, Activity> activityMap = new ConcurrentHashMap<Long, Activity>();
    private static Long COUNT = 0L;

    public ActivityDao() {
        this.create(new Activity(Difficulty.EASY, "Doggie", "Walk your dog for 1 hour!", Status.PENDING));
    }

    public Activity create(final Activity activity) {
        if (Objects.nonNull(activity.getId())) {
            activityMap.put(activity.getId(), activity);
        } else {
            activity.setId(COUNT);
            activityMap.put(COUNT, activity);
            COUNT++;
        }

        return activity;
    }

    public Activity read(final Long id) {
        return activityMap.getOrDefault(id, null);
    }

    public Activity update(final Activity activity) {
        if (Objects.nonNull(activity.getId())) {
            activityMap.put(activity.getId(), activity);
            return activity;
        }

        return null;
    }

    public Activity delete(final Activity activity) {
        if (Objects.nonNull(activity.getId())) {
            activityMap.remove(activity.getId(), activity);
            return activity;
        }

        return null;
    }

    public List<Activity> readAll() {
        return new ArrayList<>(activityMap.values());
    }
}
