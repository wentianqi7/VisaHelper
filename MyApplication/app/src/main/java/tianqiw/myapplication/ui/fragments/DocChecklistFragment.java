package tianqiw.myapplication.ui.fragments;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import tianqiw.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.model.DocRecord;
import tianqiw.myapplication.ui.activities.DocActivity;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public class DocChecklistFragment extends ListFragment {
    private List<DocRecord> docRecordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        docRecordList = new ArrayList<DocRecord>();
        initRecords("F1");
        DocAdapter adapter = new DocAdapter(docRecordList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((DocAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private void initRecords(String visaType) {
        if (docRecordList == null) {
            return;
        }
        switch(visaType) {
            case "F1":
                docRecordList.add(new DocRecord(0, "Passport"));
                docRecordList.add(new DocRecord(1, "DS-160"));
                docRecordList.add(new DocRecord(4, "Fee Payment"));
                docRecordList.add(new DocRecord(2, "I-20"));
                docRecordList.add(new DocRecord(3, "SEVIS Fee"));
                docRecordList.add(new DocRecord(4, "Two Passport Photo"));
                break;
        }
    }

    protected class DocAdapter extends ArrayAdapter<DocRecord> {
        public DocAdapter(List<DocRecord> docs) {
            super(getActivity(), android.R.layout.simple_list_item_1, docs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.doc_checklist_fragment, null);
            }

            final DocRecord dr = getItem(position);

            ImageView repoImageView = (ImageView) convertView.findViewById(R.id.item_image);
            repoImageView.setImageResource(R.drawable.img0);

            TextView docNameView =
                    (TextView) convertView.findViewById(R.id.item_name);
            docNameView.setText(dr.getTitle());

            TextView dateView =
                    (TextView) convertView.findViewById(R.id.item_time);
            dateView.setText(dr.getDate());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DocActivity.class);
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
