package tianqiw.myapplication.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tianqiw.myapplication.R;

/**
 * Created by STuotuo.Wen on 2016/4/2.
 */
public class HeaderFragment extends Fragment {
    private Button headerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.header_fragment, parent, false);
        headerButton = (Button) v.findViewById(R.id.header_button);
        headerButton.setText("Visa Helper");
        return v;
    }
}
