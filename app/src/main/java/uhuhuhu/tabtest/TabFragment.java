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
    private FragmentActivity myContext = null;

    private Fragment tabFragment = null;
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

        final FragmentManager fm = getFragmentManager();

        prescriptionTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
                ft.replace(R.id.content_fragment, new PrescriptionsFragment());
                ft.commit();
            }
        });
        measurementsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
                ft.replace(R.id.content_fragment, new MeasurementsFragment());
                ft.commit();
            }
        });
        journalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
                ft.replace(R.id.content_fragment, new JournalFragment());
                ft.commit();
            }
        });
        feedbackTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
                ft.replace(R.id.content_fragment, new FeedbackFragment());
                ft.commit();
            }
        });
        moreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MoreActivity.class));
//                Fragment fragment = new MoreFragment();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.content_fragment, fragment);
//
//                if (fragment.isVisible()) {
//                    //ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
//                    ft.show(fragment);
//                } else {
//                    //ft.setCustomAnimations(R.animator.slide_out, R.animator.slide_in);
//                    ft.hide(fragment);
//                }
//                ft.commit();
            }
        });

        return view;
    }

    private void showFragment(Fragment fragment) {
//        FragmentManager fm = getFragmentManager();
//
//        Fragment frag = fm.findFragmentById(R.id.prescriptions_fragment);
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frag).commit();
//
//        frag = fm.findFragmentById(R.id.measurements_fragment);
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frag).commit();
//
//        frag = fm.findFragmentById(R.id.journal_fragment);
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frag).commit();
//
//        frag = fm.findFragmentById(R.id.feedback_fragment);
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frag).commit();
//
//        frag = fm.findFragmentById(R.id.more_fragment);
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frag).commit();
//
//        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(fragment).commit();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        //myContext = (FragmentActivity) activity;
//        super.onAttach(activity);
//    }
}
