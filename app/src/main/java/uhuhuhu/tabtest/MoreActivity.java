package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MoreActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_tab_content);


//        moreParentLayout = (RelativeLayout) findViewById(R.id.more_parent_layout);
//
//        moreParentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public void onHomeClick(View view) {
        finish();
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
        finish();
    }
}
