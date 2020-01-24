package edu.apaczai.service;

import java.util.List;

import edu.apaczai.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// itt definialjuk, hogy milyen hivasokat tud kezelni a Task service
public interface TaskService {
    // lekerjuk az osszes taskot (REST konvencio)
    @GET("/tasks")
    Call<List<Task>> list();

    // lekerunk egyetlen taskot id - szerint
    @GET("/tasks/{id}")
    Call<Task> getById(final @Path("id") Long id);

    // letrehozunk egy taskot
    @POST("/tasks")
    Call<Task> create(final @Body Task task);

    // ujitunk egy taskon
    @PUT("/tasks")
    Call<Task> update(final @Body Task task);

    // letorlunk egy task-t
    @DELETE("/tasks")
    Call<Task> delete(final @Body Task task);
}
