package edu.apaczai.backend.api;

import edu.apaczai.backend.data.dao.ActivityDao;
import edu.apaczai.backend.data.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ActivityDao activityDao;

    @GetMapping("/tasks")
    protected ResponseEntity<List<Task>> getAll() {
        LOG.info("get all");
        return new ResponseEntity<>(activityDao.readAll(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    protected ResponseEntity<Task> getById(final @PathVariable Long id) {
        LOG.info("get by id {}", id);

        Task task = activityDao.read(id);

        if (Objects.nonNull(task)) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    protected ResponseEntity<Task> create(final @RequestBody Task task) {
        LOG.info("create {}", task);
        return new ResponseEntity<>(
                activityDao.create(task),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/tasks")
    protected ResponseEntity<Task> delete(final @RequestBody Task task) {
        Task deletedTask = activityDao.delete(task);
        LOG.info("delete {}", task);

        if (Objects.nonNull(deletedTask)) {
            return new ResponseEntity<>(deletedTask, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tasks")
    protected ResponseEntity<Task> update(final @RequestBody Task task) {
        LOG.info("update {}", task);
        Task updatedTask = activityDao.update(task);

        if (Objects.nonNull((updatedTask))) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
