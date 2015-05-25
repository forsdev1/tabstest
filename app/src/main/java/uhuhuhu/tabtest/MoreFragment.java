package uhuhuhu.tabtest;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoreFragment extends Fragment {

    private View tabView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_tab_content, container, false);

        tabView = getFragmentManager().findFragmentById(R.id.tab_fragment).getView().findViewById(R.id.tab_layout);
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            tabView.findViewById(R.id.tab_layout_5).setSelected(true);
        } else {
            tabView.findViewById(R.id.tab_layout_5).setSelected(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tabView.findViewById(R.id.tab_layout_5).setSelected(true);
    }
    @Override
    public void onStop() {
        super.onStop();
        tabView.findViewById(R.id.tab_layout_5).setSelected(false);
    }
}
