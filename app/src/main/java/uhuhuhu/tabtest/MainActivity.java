package uhuhuhu.tabtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {

    private HomeFragment homeFragment = null;
    private PrescriptionsFragment prescriptionsFragment = null;
    private MeasurementsFragment measurementsFragment = null;
    private JournalFragment journalFragment = null;
    private FeedbackFragment feedbackFragment = null;
    private MoreFragment moreFragment = null;
    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(findViewById(R.id.content_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            loadAllFragments();

        }
    }

    private void loadAllFragments() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction t = fm.beginTransaction();

        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        t.add(R.id.content_fragment, homeFragment);


//        if (prescriptionsFragment == null) {
//            prescriptionsFragment = new PrescriptionsFragment();
//        }
//        if(!prescriptionsFragment.isAdded()) {
//           t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.prescriptions_fragment, prescriptionsFragment)
//                    .hide(fm.findFragmentById(R.id.prescriptions_fragment));
//        }
//
//
//        if (measurementsFragment == null) {
//            measurementsFragment = new MeasurementsFragment();
//        }
//        if(!measurementsFragment.isAdded()) {
//            t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.measurements_fragment, measurementsFragment)
//                    .hide(fm.findFragmentById(R.id.measurements_fragment));
//        }
//
//
//        if (journalFragment == null) {
//            journalFragment = new JournalFragment();
//        }
//        if(!journalFragment.isAdded()) {
//            t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.journal_fragment, journalFragment)
//                    .hide(fm.findFragmentById(R.id.journal_fragment));
//        }
//
//
//        if (feedbackFragment == null) {
//            feedbackFragment = new FeedbackFragment();
//        }
//        if(!feedbackFragment.isAdded()) {
//            t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.feedback_fragment, feedbackFragment)
//                    .hide(fm.findFragmentById(R.id.feedback_fragment));
//        }
//
//
//        if (moreFragment == null) {
//            moreFragment = new MoreFragment();
//        }
//        if(!moreFragment.isAdded()) {
//            t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .add(R.id.more_fragment, moreFragment)
//                    .hide(fm.findFragmentById(R.id.more_fragment));
//        }
//
//        Fragment y = fm.findFragmentById(R.id.more_fragment);
//        y.startActivity(new Intent(this, MoreActivity.class));
//        if (moreFragment == null) {
//            moreFragment = new MoreFragment();
//        }
//        if(!moreFragment.isAdded()) {
//                    t.add(R.id.more_fragment, moreFragment)
//                    .hide(fm.findFragmentById(R.id.more_fragment))
//                            .show(fm.findFragmentById(R.id.more_fragment));
//        }

        t.commit();
    }

    public void showSlider(View view) {
        ViewGroup p1View = (ViewGroup) view.getParent();
        ViewGroup p2View = (ViewGroup) p1View.getParent();
        RelativeLayout childLayout = (RelativeLayout) p1View.findViewById(R.id.feedback_slider_layout);

        if(childLayout.getVisibility() == View.GONE) {
            for (int i = 0; i < p2View.getChildCount(); ++i) {
                RelativeLayout childLayout2 = (RelativeLayout) p2View.getChildAt(i).findViewById(R.id.feedback_slider_layout);
                childLayout2.setVisibility(View.GONE);
                childLayout2.animate().translationY(0.0f).alpha(0.0f);
            }

            childLayout.setVisibility(View.VISIBLE);
            childLayout.setAlpha(0.0f);
            childLayout.animate().translationY(0.5f).alpha(1.0f);
        }
    }

    @Override
    public void onBackPressed(){

    }

//    private void addTab(final TabHost tabHost,
//                        final String tabName, final int tabLayout, final Class activity) {
//
//        //Create tab content
//        View view = LayoutInflater.from(tabHost.getContext()).inflate(tabLayout, null);
//        //If no tabs created yet, create faketab with home activity content and hide it from tabwidget.
//        TabHost.TabSpec tab = tabHost.newTabSpec(tabName);
//        if(tabHost.getTabWidget().getChildCount() == 0) {
//            view.setVisibility(View.GONE);
//        }
//        tab.setIndicator(view);
//        //Set tab content
//        Intent intent = new Intent(this, activity);
//        tab.setContent(intent);
//        //Add tab
//        tabHost.addTab(tab);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
