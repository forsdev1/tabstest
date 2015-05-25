package uhuhuhu.tabtest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TabFragment extends Fragment{
    Fragment prescriptionsFragment = null;
    Fragment measurementsFragment = null;
    Fragment journalFragment = null;
    Fragment feedbackFragment = null;
    Fragment moreFragment = null;

    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);

        View tabView = view.findViewById(R.id.tab_layout);
        prescriptionTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_1);
        measurementsTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_2);
        journalTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_3);
        feedbackTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_4);
        moreTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_5);

        final FragmentManager fm = getFragmentManager();

        prescriptionTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (prescriptionsFragment == null) {
                    hideAll();
                    prescriptionsFragment = new PrescriptionsFragment();
                    ft.add(R.id.content_fragment, prescriptionsFragment);
                    if(fm.getBackStackEntryCount() == 0) {
                        ft.addToBackStack("Prescription");
                    }
                } else {
                    if (prescriptionsFragment.isHidden() ||
                            (moreFragment != null && moreFragment.isVisible())) {
                        hideAll();
                        ft.show(prescriptionsFragment);
                    } else {
                        ft.hide(prescriptionsFragment);
                    }
                }
                ft.commit();
            }
        });
        measurementsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (measurementsFragment == null) {
                    hideAll();
                    measurementsFragment = new MeasurementsFragment();
                    ft.add(R.id.content_fragment, measurementsFragment);
                    if(fm.getBackStackEntryCount() == 0) {
                        ft.addToBackStack("Measurements");
                    }
                } else {
                    if (measurementsFragment.isHidden() ||
                            (moreFragment != null && moreFragment.isVisible())) {
                        hideAll();
                        ft.show(measurementsFragment);
                    } else {
                        ft.hide(measurementsFragment);
                    }
                }
                ft.commit();
            }
        });
        journalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (journalFragment == null) {
                    hideAll();
                    journalFragment = new JournalFragment();
                    ft.add(R.id.content_fragment, journalFragment);
                    if(fm.getBackStackEntryCount() == 0) {
                        ft.addToBackStack("Journal");
                    }
                } else {
                    if (journalFragment.isHidden() ||
                            (moreFragment != null && moreFragment.isVisible())) {
                        hideAll();
                        ft.show(journalFragment);
                    } else {
                        ft.hide(journalFragment);
                    }
                }
                ft.commit();
            }
        });
        feedbackTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (feedbackFragment == null) {
                    hideAll();
                    feedbackFragment = new FeedbackFragment();
                    ft.add(R.id.content_fragment, feedbackFragment);
                    if(fm.getBackStackEntryCount() == 0) {
                        ft.addToBackStack("Feedback");
                    }
                } else {
                    if (feedbackFragment.isHidden() ||
                            (moreFragment != null && moreFragment.isVisible())) {
                        hideAll();
                        ft.show(feedbackFragment);
                    } else {
                        ft.hide(feedbackFragment);
                    }
                }
                ft.commit();
            }
        });
        moreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if(fm.findFragmentById(R.id.content_fragment) != moreFragment ) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content_fragment, moreFragment).addToBackStack(null).commit();
                } else {
                    ft.remove(moreFragment).commit();
                }
            }
        });

        return view;
    }

    private void hideAll() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (moreFragment != null) ft.remove(moreFragment);
        if (prescriptionsFragment != null) ft.hide(prescriptionsFragment);
        if (measurementsFragment != null) ft.hide(measurementsFragment);
        if (journalFragment != null) ft.hide(journalFragment);
        if (feedbackFragment != null) ft.hide(feedbackFragment);
        ft.commit();
        prescriptionTab.setSelected(false);
        measurementsTab.setSelected(false);
        journalTab.setSelected(false);
        feedbackTab.setSelected(false);
    }
}
