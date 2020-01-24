package edu.apaczai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.Collections;
import java.util.List;

import edu.apaczai.model.Difficulty;
import edu.apaczai.model.Task;
import edu.apaczai.service.TaskService;

import edu.apaczai.service.ServiceManager;

import static edu.apaczai.service.executor.CallExecutor.getInstance;

public class MainActivity extends AppCompatActivity {
    private static final String NAME = MainActivity.class.getName();

    // kell egy olyan service, ami eloallitja nekunk azokat a requesteket, amiket elkuldunk a backendnek, es erre fog o valaszolni
    private TaskService taskService;
    // progressbar, legyen jelen ameddig valaszol a szerver
    private ProgressBar progressBar;

    // a lista, amibe beletesszuk a taskokat
    private ListView listView;

    // adapter a listahoz
    private TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // megkeressuk a view-be az elemeket
        progressBar = findViewById(R.id.progress_load);
        listView = findViewById(R.id.task_list);

        // letrehozzuk az adapter-t, de ugy, hogy ne mutasson semmit (legyen egy ures lista), majd akkor toltjuk fel amikor valaszolt a szerver)
        taskListAdapter = new TaskListAdapter(this, Collections.emptyList());
        // beallitjuk az adaptert a listanak az adapterekent
        listView.setAdapter(taskListAdapter);

        // lekerjuk a service-t ami meg fogja nekunk fogalmazni a kereseket
        taskService = ServiceManager.getInstance().getTaskService();

        // jojjetek ra, hogy ez itt mit csinal
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedItem = taskListAdapter.getItem(position);

            if (selectedItem.getDifficulty().equals(Difficulty.EASY)) {
                selectedItem.setDifficulty(Difficulty.MEDIUM);
            } else if (selectedItem.getDifficulty().equals(Difficulty.MEDIUM)) {
                selectedItem.setDifficulty(Difficulty.HARD);
            } else {
                selectedItem.setDifficulty(Difficulty.EASY);
            }

            getInstance().execute(
                    taskService.update(selectedItem),
                    result -> runOnUiThread(() -> taskListAdapter.notifyDataSetChanged())
            );

        });


        // meghivjuk a fuggvenyt ami lekeri a szervertol a letezo taskokat
        updateView();
    }


    private void updateView() {
        // beszeltuk, hogy nem lehet a main threaden networkolni, ezert irtam egy
        // executort, ami 3 dolgot vesz parameterkent
        // 1) egy olyan kerest, amit megfogalmaz nekunk az taskService
        // 2) egy fuggveny, ami akkor fut le, ha sikeres volt a keres, es a szerver valaszolt
        // 3) egy olyan fuggveny ami akkor fut le ha valami hiba tortent a keres kozben
        getInstance().execute(
                taskService.list(),
                this::handleResultsFetched,
                this::handleError
        );
    }

    // egy olyan fuggveny ami akkor fut le ha sikeres volt egy keres
    private void handleResultsFetched(final List<Task> tasks) {
        runOnUiThread(() -> {
            taskListAdapter.addAll(tasks);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }


    // egy olyan fuggveny ami akkor fut le, ha hiba tortent
    private void handleError(Exception error) {
        runOnUiThread(() -> {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}
