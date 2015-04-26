package uhuhuhu.tabtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class MoreActivity extends Activity {
    private TabHost tabHost = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);
    }

    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
