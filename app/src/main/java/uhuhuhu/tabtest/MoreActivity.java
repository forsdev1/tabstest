package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

public class MoreActivity extends Activity {
    private TabHost tabHost = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);
    }

    public void onHomeClick(View view) {
        tabHost.setCurrentTab(0);
    }
    public void onProfileClick(View view) {
        startActivity(new Intent(getBaseContext(), PatientProfile.class));
    }
    public void onMessagesClick(View view) {
        startActivity(new Intent(getBaseContext(), PatientMessages.class));
    }
    public void onSettingsClick(View view) {
        startActivity(new Intent(getBaseContext(), PatientSettings.class));
    }

    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
