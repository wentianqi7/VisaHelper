package tianqiw.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.R;
import tianqiw.myapplication.model.enums.DocType;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public class DocImageListFragment extends ListFragment {
    private List<String> imageList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageList = new ArrayList<String>();
        initRecords(DocType.DS160);
        DocAdapter adapter = new DocAdapter(imageList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((DocAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private void initRecords(DocType docType) {
        if (imageList == null) {
            return;
        }
        switch(docType) {
            case DS160:
                imageList.add("");
                imageList.add("");
                break;
        }
    }

    protected class DocAdapter extends ArrayAdapter<String> {
        public DocAdapter(List<String> docs) {
            super(getActivity(), android.R.layout.simple_list_item_1, docs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.doc_image_fragment, null);
            }

            final String image = getItem(position);

            ImageView repoImageView = (ImageView) convertView.findViewById(R.id.item_image);
            repoImageView.setImageResource(R.drawable.img0);

            return convertView;
        }
    }
}
