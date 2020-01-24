package edu.apaczai.service;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {
    private static ServiceManager INSTANCE;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ActivityService activityService;

    public static ServiceManager getInstance() {
        synchronized (ServiceManager.class) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new ServiceManager();
            }
        }

        return INSTANCE;
    }

    public ActivityService getActivityService() {
        if (Objects.isNull(this.activityService)) {
            this.activityService = retrofit.create(ActivityService.class);
        }

        return activityService;
    }
}
