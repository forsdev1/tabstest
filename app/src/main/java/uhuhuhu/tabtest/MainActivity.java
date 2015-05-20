package uhuhuhu.tabtest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends FragmentActivity {

    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(findViewById(R.id.container_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }


            HomeFragment homeFragment = new HomeFragment();
            //Calendar calendar = Calendar.getInstance();

//            dateBox = (TextView)homeFragment.findViewById(R.id.dateTextView_id);
//            setDate(new StringBuilder().append(String.format(Locale.US, "%tB", calendar))
//                    .append(" <b>")
//                    .append(calendar.get(Calendar.DAY_OF_MONTH))
//                    .append("</b>").toString());

            getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, homeFragment).commit();
//            homeFragment.setDate(new StringBuilder().append(String.format(Locale.US, "%tB", calendar))
//                    .append(" <b>")
//                    .append(calendar.get(Calendar.DAY_OF_MONTH))
//                    .append("</b>").toString());

        }
//        View tabView = findViewById(R.id.tab_layout_id);
//        RelativeLayout prescriptionTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_1);
//        RelativeLayout measurementsTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_2);
//        RelativeLayout journalTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_3);
//        RelativeLayout feedbackTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_4);
//        RelativeLayout moreTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_5);
//
//        prescriptionTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), PrescriptionsActivity.class));
//                view.setSelected(true);
//            }
//        });
//        measurementsTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), MeasurementsActivity.class));
//                view.setSelected(true);
//            }
//        });
//        journalTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), JournalActivity.class));
//                view.setSelected(true);
//            }
//        });
//        feedbackTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), FeedbackActivity.class));
//                view.setSelected(true);
//            }
//        });
//        moreTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), MoreActivity.class));
//                view.setSelected(true);
//            }
//        });

//        final TabHost tabHost = getTabHost();
//        tabHost.getTabWidget().setStripEnabled(true);
//        addTab(tabHost, "Home", R.layout.home_tab, HomeActivity.class);
//        addTab(tabHost, "Prescriptions", R.layout.prescriptions_tab, PrescriptionsActivity.class);
//        addTab(tabHost, "Measurements", R.layout.measurements_tab, MeasurementsActivity.class);
//        addTab(tabHost, "Journal", R.layout.journal_tab, JournalActivity.class);
//        addTab(tabHost, "Feedback", R.layout.feedback_tab, FeedbackActivity.class);
//        addTab(tabHost, "More", R.layout.more_tab, MoreActivity.class);
//
//        //Set listener on "More" tab
//        tabHost.getTabWidget().getChildTabViewAt(5).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    startActivity(new Intent(getBaseContext(), MoreActivity.class));
//                    //view.setPressed(true);
//                    view.setSelected(true);
//                }
//                return true;
//            }
//        });
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
