package tianqiw.myapplication.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tianqiw.myapplication.R;

/**
 * Created by STuotuo.Wen on 2016/3/27.
 */
public class DocContentFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_content_fragment, parent, false);
        return v;
    }
}
