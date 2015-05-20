package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TabFragment extends Fragment{
    private FragmentActivity myContext = null;

    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_layout, container, false);

        View tabView = view.findViewById(R.id.tab_layout);
        prescriptionTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_1);
        measurementsTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_2);
        journalTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_3);
        feedbackTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_4);
        moreTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_5);

        prescriptionTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrescriptionsFragment prescriptionsFragment = new PrescriptionsFragment();
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction transaction = fragManager.beginTransaction();
                transaction.replace(R.id.container_fragment, prescriptionsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        measurementsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeasurementsFragment measurementsFragment = new MeasurementsFragment();
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction transaction = fragManager.beginTransaction();
                transaction.replace(R.id.container_fragment, measurementsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        journalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JournalFragment journalFragment = new JournalFragment();
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction transaction = fragManager.beginTransaction();
                transaction.replace(R.id.container_fragment, journalFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        feedbackTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction transaction = fragManager.beginTransaction();
                transaction.replace(R.id.container_fragment, feedbackFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        moreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MoreFragment moreFragment = new MoreFragment();
//                FragmentManager fragManager = myContext.getSupportFragmentManager();
//                fragManager.beginTransaction()
//                        .remove(fragManager.findFragmentById(R.id.more_fragment))
//                        .commit();
                        //.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

//                if (moreFragment.isHidden()) {
//                    ft.show(moreFragment);
//                } else {
//                    ft.hide(moreFragment);
//                }
                //ft.commit();
//                FragmentManager fragManager = myContext.getSupportFragmentManager();
//                FragmentTransaction transaction = fragManager.beginTransaction();
//
//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack so the user can navigate back
//                transaction.replace(R.id.more_fragment, newFragment);
//                transaction.addToBackStack(null);
//
//// Commit the transaction
//                transaction.commit();
                //startActivity(new Intent(getActivity(), MoreActivity.class));
            }
        });

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
