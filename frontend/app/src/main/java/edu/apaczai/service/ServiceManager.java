package edu.apaczai.service;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceManager {
    private static ServiceManager INSTANCE;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.152:5000/") // ez az IP cim vigyazzatok hogy a ti szamitogepeteke legyen.
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TaskService taskService;

    public static ServiceManager getInstance() {
        synchronized (ServiceManager.class) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new ServiceManager();
            }
        }

        return INSTANCE;
    }


    // a retrofit hozza nekunk letre a service-ket, ezert ha lesz uj definialt service,
    // itt kell letrehozni
    public TaskService getTaskService() {
        if (Objects.isNull(this.taskService)) {
            this.taskService = retrofit.create(TaskService.class);
        }

        return taskService;
    }
}
