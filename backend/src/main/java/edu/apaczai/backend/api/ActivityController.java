package edu.apaczai.backend.api;

import edu.apaczai.backend.data.dao.ActivityDao;
import edu.apaczai.backend.data.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ActivityController {
    @Autowired
    private ActivityDao activityDao;

    @GetMapping("/activities")
    protected ResponseEntity<List<Activity>> getAll() {
        return new ResponseEntity<>(activityDao.readAll(), HttpStatus.OK);
    }

    @GetMapping("/activities/{id}")
    protected ResponseEntity<Activity> getById(final @PathVariable Long id) {
        Activity activity = activityDao.read(id);

        if (Objects.nonNull(activity)) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/activities")
    protected ResponseEntity<Activity> create(final @RequestBody Activity activity) {
        return new ResponseEntity<>(
                activityDao.create(activity),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/activities")
    protected ResponseEntity<Activity> delete(final @RequestBody Activity activity) {
        Activity deletedActivity = activityDao.delete(activity);

        if (Objects.nonNull(deletedActivity)) {
            return new ResponseEntity<>(deletedActivity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activities")
    protected ResponseEntity<Activity> update(final @RequestBody Activity activity) {
        Activity updatedActivity = activityDao.update(activity);

        if (Objects.nonNull((updatedActivity))) {
            return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
