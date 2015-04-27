package uhuhuhu.tabtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class PrescriptionsActivity extends ActionBarActivity {
    private TabHost tabHost = null;
    private Button dailyButton;
    private Button listButton;
    private Button historyButton;

//    ActionBar.Tab Tab1;
//    ActionBar.Tab Tab2;
//    ActionBar.Tab Tab3;
//
//    public class FragmentTab1 extends Fragment {
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.more_tab_content, container, false);
//            return rootView;
//        }
//
//    }
//
//    public class FragmentTab2 extends Fragment {
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.more_tab_content, container, false);
//            return rootView;
//        }
//
//    }
//
//    public class FragmentTab3 extends Fragment {
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.more_tab_content, container, false);
//            return rootView;
//        }
//
//    }
//    public class TabListener implements ActionBar.TabListener {
//
//        Fragment fragment;
//
//        public TabListener(Fragment fragment) {
//            // TODO Auto-generated constructor stub
//            this.fragment = fragment;
//        }
//
//        @Override
//        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//            // TODO Auto-generated method stub
//            ft.replace(R.id.fragment_container, fragment);
//        }
//
//        @Override
//        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//            // TODO Auto-generated method stub
//            ft.remove(fragment);
//        }
//
//        @Override
//        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//            // TODO Auto-generated method stub
//
//        }
//    }
//    Fragment fragmentTab1 = new FragmentTab1();
//    Fragment fragmentTab2 = new FragmentTab2();
//    Fragment fragmentTab3 = new FragmentTab3();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescriptions_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        dailyButton = (Button) findViewById(R.id.button_daily_id);
        listButton = (Button) findViewById(R.id.button_list_id);
        historyButton = (Button) findViewById(R.id.button_history_id);

        dailyButton.setPressed(true);

        dailyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dailyButton.setPressed(true);
                listButton.setPressed(false);
                historyButton.setPressed(false);

//                dailyButton.setBackgroundResource(R.drawable.button_selected_round);
//                listButton.setBackgroundResource(R.drawable.button_unselected_square);
//                historyButton.setBackgroundResource(R.drawable.button_unselected_round);

                v.performClick();
                return true;
            }
        });
        dailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        listButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dailyButton.setPressed(false);
                listButton.setPressed(true);
                historyButton.setPressed(false);

//                dailyButton.setBackgroundResource(R.drawable.button_unselected_round);
//                listButton.setBackgroundResource(R.drawable.button_selected_square);
//                historyButton.setBackgroundResource(R.drawable.button_unselected_round);

                v.performClick();
                return true;
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        historyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dailyButton.setPressed(false);
                listButton.setPressed(false);
                historyButton.setPressed(true);

//                dailyButton.setBackgroundResource(R.drawable.button_unselected_round);
//                listButton.setBackgroundResource(R.drawable.button_unselected_square);
//                historyButton.setBackgroundResource(R.drawable.button_selected_round);

                v.performClick();
                return true;
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


//        ActionBar actionBar = getSupportActionBar();
//
//        // Hide Actionbar Icon
//        actionBar.setDisplayShowHomeEnabled(false);
//
//        // Hide Actionbar Title
//        actionBar.setDisplayShowTitleEnabled(false);
//
//        // Create Actionbar Tabs
//        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
//
//        // Set Tab Icon and Titles
//        Tab1 = actionBar.newTab().setIcon(R.drawable.alert_icon1_48);
//        Tab2 = actionBar.newTab().setText("Tab2");
//        Tab3 = actionBar.newTab().setText("Tab3");
//
//        // Set Tab Listeners
//        Tab1.setTabListener( new TabListener(fragmentTab1));
//        Tab2.setTabListener( new TabListener(fragmentTab2));
//        Tab3.setTabListener( new TabListener(fragmentTab3));
//
//        // Add tabs to actionbar
//        actionBar.addTab(Tab1);
//        actionBar.addTab(Tab2);
//        actionBar.addTab(Tab3);
    }


    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
