package edu.apaczai;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.apaczai.model.Difficulty;
import edu.apaczai.model.Task;

public class TaskListAdapter extends ArrayAdapter<Task> {
    private final Activity activity;
    private List<Task> tasks;

    public TaskListAdapter(final Activity activity, final List<Task> tasks) {
        super(activity, R.layout.activity_entry, new ArrayList<>(tasks));
        this.activity = activity;
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_entry, parent, false);

            viewHolder.title = convertView.findViewById(R.id.task_title);
            viewHolder.description= convertView.findViewById(R.id.task_description);
            viewHolder.status = convertView.findViewById(R.id.task_status);
            viewHolder.difficulty = convertView.findViewById(R.id.task_difficulty);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Task task = this.tasks.get(position);

        viewHolder.title.setText(task.getTitle());
        viewHolder.description.setText(task.getDescription());
        viewHolder.status.setText(task.getStatus().toString());
        viewHolder.difficulty.setText(task.getDifficulty().toString());

        if (task.getDifficulty().equals(Difficulty.EASY)) {
            viewHolder.difficulty.setTextColor(Color.rgb(0, 255, 0));
        } else if (task.getDifficulty().equals(Difficulty.MEDIUM)) {
            viewHolder.difficulty.setTextColor(Color.rgb(255, 255, 0));
        } else {
            viewHolder.difficulty.setTextColor(Color.rgb(255 ,0, 0));
        }

        return convertView;
    }

    @Override
    public void addAll(@NonNull Collection<? extends Task> collection) {
        this.tasks = new ArrayList<>(collection);
        super.clear();
        super.addAll(collection);
    }

    private static class ViewHolder {
        private TextView title;
        private TextView description;
        private TextView status;
        private TextView difficulty;

    }
}
