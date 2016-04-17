package tianqiw.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import tianqiw.myapplication.R;
import tianqiw.myapplication.entity.TaskManager;
import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;
import tianqiw.myapplication.utils.XmlParserHandler;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class TaskListFragment extends ListFragment {
    private List<TaskItem> taskItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VisaType visaType = VisaType.values()[getArguments().getInt(getString(R.string.visa_type))];
        getActivity().setTitle(String.format("VisaHelper - %s", visaType));
        initTasks(visaType);
        TaskAdapter adapter = new TaskAdapter(taskItemList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((TaskAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private void initTasks(VisaType visaType) {
        // try to read all tasks from db
        // if not exists, read from xml and insert into db
        taskItemList = TaskManager.get(getActivity()).readAllTasksWithType(visaType);
        if (taskItemList.size() == 0) {
            XmlParserHandler xmlParserHandler = new XmlParserHandler();
            switch (visaType) {
                case F1:
                    taskItemList = xmlParserHandler.parse(getResources().openRawResource(R.raw.f1_tasks));
                    break;
            }

            for (TaskItem task : taskItemList) {
                TaskManager.get(getActivity()).createTask(task);
            }
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

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            // set background color according to task status
            switch (task.getStatus()) {
                case UNDEFINED:

                    break;
                case IN_PROGRESS:
                    // convertView.setBackgroundColor(getResources().getColor(R.color.colorTaskTrue));
                    break;
                case COMPLETED:
                    checkBox.setChecked(true);
                    convertView.setBackgroundColor(getResources().getColor(R.color.colorTaskFalse));
                    break;
            }

            // change task status and background color according to checkbox
            final View tempView = convertView;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        TaskManager.get(getActivity()).updateTaskStatus(task.getTid(), TaskStatus.COMPLETED);
                        tempView.setBackgroundColor(getResources().getColor(R.color.colorTaskFalse));
                    } else {
                        TaskManager.get(getActivity()).updateTaskStatus(task.getTid(), TaskStatus.IN_PROGRESS);
                        tempView.setBackgroundColor(getResources().getColor(R.color.colorTaskTrue));
                    }
                }
            });

            // expand to show details when touch on task
            final TextView descriptionView = (TextView) convertView.findViewById(R.id.description);
            descriptionView.setText(task.getDescription());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (descriptionView.getVisibility() == View.VISIBLE) {
                        descriptionView.setVisibility(View.GONE);
                    } else {
                        descriptionView.setVisibility(View.VISIBLE);
                    }
                }
            });
            return convertView;
        }
    }
}
