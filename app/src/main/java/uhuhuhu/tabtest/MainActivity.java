package uhuhuhu.tabtest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TabHost tabHost = getTabHost();
        tabHost.getTabWidget().setStripEnabled(true);
        addTab(tabHost, "Home", R.layout.home_tab, HomeActivity.class);
        addTab(tabHost, "Prescriptions", R.layout.prescriptions_tab, PrescriptionsActivity.class);
        addTab(tabHost, "Measurements", R.layout.measurements_tab, MeasurementsActivity.class);
        addTab(tabHost, "Journal", R.layout.journal_tab, JournalActivity.class);
        addTab(tabHost, "Feedback", R.layout.feedback_tab, FeedbackActivity.class);
        addTab(tabHost, "More", R.layout.more_tab, MoreActivity.class);

        //Set listener on "More" tab
        tabHost.getTabWidget().getChildTabViewAt(5).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startActivity(new Intent(getBaseContext(), MoreActivity.class));
                    //view.setPressed(true);
                    view.setSelected(true);
                }
                return true;
            }
        });
    }

    private void addTab(final TabHost tabHost,
                        final String tabName, final int tabLayout, final Class activity) {

        //Create tab content
        View view = LayoutInflater.from(tabHost.getContext()).inflate(tabLayout, null);
        //If no tabs created yet, create faketab with home activity content and hide it from tabwidget.
        TabHost.TabSpec tab = tabHost.newTabSpec(tabName);
        if(tabHost.getTabWidget().getChildCount() == 0) {
            view.setVisibility(View.GONE);
        }
        tab.setIndicator(view);
        //Set tab content
        Intent intent = new Intent(this, activity);
        tab.setContent(intent);
        //Add tab
        tabHost.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
