package edu.apaczai.service;

import java.util.List;

import edu.apaczai.model.Activity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ActivityService {
    @GET("/activities")
    Call<List<Activity>> list();

    @GET("/activities/{id}")
    Call<Activity> getById(final @Path("id") Long id);

    @POST("/activities")
    Call<Activity> create(final @Body Activity activity);

    @PUT("/activities")
    Call<Activity> update(final @Body Activity activity);

    @DELETE("/activities")
    Call<Activity> delete(final @Body Activity activity);
}
