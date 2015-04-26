package uhuhuhu.tabtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class MeasurementsActivity extends Activity {
    private TabHost tabHost = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurements_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);
    }

    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
