package edu.apaczai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.apaczai.model.Activity;
import edu.apaczai.service.ActivityService;
import edu.apaczai.service.ServiceManager;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityService activityService = ServiceManager.getInstance().getActivityService();
        Call<List<Activity>> listCall = activityService.list();
        try {
            Response<List<Activity>> response = listCall.execute();
            List<Activity> activities = response.body();

            for (Activity activity : activities) {
                Log.d(this.getClass().getCanonicalName(), activity.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
