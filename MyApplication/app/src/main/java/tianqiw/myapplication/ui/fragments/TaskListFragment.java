package tianqiw.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.R;
import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.ui.activities.DocActivity;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class TaskListFragment extends ListFragment {
    private List<TaskItem> taskItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String visaType = "F1";
        getActivity().setTitle(String.format("VisaHelper - %s", visaType));
        taskItemList = new ArrayList<TaskItem>();
        initTasks(visaType);
        TaskAdapter adapter = new TaskAdapter(taskItemList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((TaskAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private void initTasks(String visaType) {
        if (taskItemList == null) {
            return;
        }
        switch (visaType) {
            case "F1":
                taskItemList.add(new TaskItem(0, "Go to the website", "This is the description for the first task."));
                taskItemList.add(new TaskItem(1, "Register", "This is the description for the second task."));
                break;
        }
    }

    protected class TaskAdapter extends ArrayAdapter<TaskItem> {
        public TaskAdapter(List<TaskItem> tasks) {
            super(getActivity(), android.R.layout.simple_list_item_1, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.task_item, null);
            }

            final TaskItem task = getItem(position);

            TextView titleView =
                    (TextView) convertView.findViewById(R.id.item_title);
            titleView.setText(task.getTitle());

            TextView statusView =
                    (TextView) convertView.findViewById(R.id.item_status);
            statusView.setText(task.getDate());

            final TextView descpView = (TextView) convertView.findViewById(R.id.description);
            descpView.setText(task.getDescription());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (descpView.getVisibility() == View.VISIBLE) {
                        descpView.setVisibility(View.GONE);
                    } else {
                        descpView.setVisibility(View.VISIBLE);
                    }
                    // Log.e("[click]", Integer.toString(descpView.getLayoutParams().height));
                }
            });
            return convertView;
        }
    }
}
