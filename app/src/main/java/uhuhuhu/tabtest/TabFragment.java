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
                    prescriptionsFragment = new PrescriptionsFragment();
                    ft.add(R.id.content_fragment, prescriptionsFragment).commit();
                } else {
                    if (prescriptionsFragment.isHidden() || moreFragment != null) {
                        hideAll();
                        ft.show(prescriptionsFragment).commit();
                    } else {
                        ft.hide(prescriptionsFragment).commit();
                    }
                }
            }
        });
        measurementsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (measurementsFragment == null) {
                    measurementsFragment = new MeasurementsFragment();
                    ft.add(R.id.content_fragment, measurementsFragment).commit();
                } else {
                    if (measurementsFragment.isHidden() || moreFragment != null) {
                        hideAll();
                        ft.show(measurementsFragment).commit();
                    } else {
                        ft.hide(measurementsFragment).commit();
                    }
                }
            }
        });
        journalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (journalFragment == null) {
                    journalFragment = new JournalFragment();
                    ft.add(R.id.content_fragment, journalFragment).commit();
                } else {
                    if (journalFragment.isHidden() || moreFragment != null) {
                        hideAll();
                        ft.show(journalFragment).commit();
                    } else {
                        ft.hide(journalFragment).commit();
                    }
                }
            }
        });
        feedbackTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if (feedbackFragment == null) {
                    feedbackFragment = new FeedbackFragment();
                    ft.add(R.id.content_fragment, feedbackFragment).commit();
                } else {
                    if (feedbackFragment.isHidden() || moreFragment != null) {
                        hideAll();
                        ft.show(feedbackFragment).commit();
                    } else {
                        ft.hide(feedbackFragment).commit();
                    }
                }
            }
        });
        moreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                if(moreFragment == null ) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content_fragment, moreFragment).commit();
                } else {
                    ft.remove(moreFragment).commit();
                    moreFragment = null;
                }
            }
        });

        return view;
    }

    private void hideAll() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (prescriptionsFragment != null) ft.hide(prescriptionsFragment);
        if (measurementsFragment != null) ft.hide(measurementsFragment);
        if (journalFragment != null) ft.hide(journalFragment);
        if (feedbackFragment != null) ft.hide(feedbackFragment);
        if (moreFragment != null) {
            ft.remove(moreFragment);
            moreFragment = null;
        }
        ft.commit();
    }
}
