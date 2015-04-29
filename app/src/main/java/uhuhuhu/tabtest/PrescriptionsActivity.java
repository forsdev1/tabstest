package uhuhuhu.tabtest;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

import com.kyleduo.switchbutton.Configuration;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;

public class PrescriptionsActivity extends ActionBarActivity {
    private TabHost tabHost = null;
    private Button dailyButton = null;
    private Button listButton = null;
    private Button historyButton = null;

    private RelativeLayout Layout1 = null;
    private RelativeLayout Layout2 = null;
    private RelativeLayout Layout3 = null;

    private LinearLayout dayViewPrescription1 = null;
    private LinearLayout dayViewPrescription2 = null;
    private LinearLayout listViewPrescription1 = null;
    private LinearLayout listViewPrescription2 = null;

    private ArrayList<PrescriptionsWithTime> pwtList = new ArrayList<>();
    private ArrayList<PrescriptionsWithoutTime> pwotList = new ArrayList<>();
    private ArrayList<PListWithTime> listpwtList = new ArrayList<>();
    private ArrayList<PListWithoutTime> listpwotList = new ArrayList<>();

    private class PrescriptionsWithTime {
        private String time;
        private String prescription;
        private boolean enabled;

        private PrescriptionsWithTime(String time, String prescription, boolean enabled) {
            this.time = time;
            this.prescription = prescription;
            this.enabled = enabled;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
    private class PrescriptionsWithoutTime {
        private String activity;
        private String condition;
        private boolean enabled;

        private PrescriptionsWithoutTime(String activity, String condition, boolean enabled) {
            this.activity = activity;
            this.condition = condition;
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }
    }

    private class PListWithTime {
        private String timeStart;
        private String timeEnd;
        private String description;
        private int progress;

        private PListWithTime(String timeStart, String timeEnd, String description, int progress) {
            this.timeStart = timeStart;
            this.timeEnd = timeEnd;
            this.description = description;
            this.progress = progress;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
    private class PListWithoutTime {
        private String activity;
        private String condition;
        private int progress;

        private PListWithoutTime(String activity, String condition, int progress) {
            this.activity = activity;
            this.condition = condition;
            this.progress = progress;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }

    public void onCreate(Bundle savedInstanceState) {

        //TODO try ViewFlipper;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescriptions_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        Layout1 = (RelativeLayout)findViewById(R.id.prl_id_1);
        Layout2 = (RelativeLayout)findViewById(R.id.prl_id_2);
        Layout3 = (RelativeLayout)findViewById(R.id.prl_id_3);
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);

        dailyButton = (Button) findViewById(R.id.button_daily_id);
        listButton = (Button) findViewById(R.id.button_list_id);
        historyButton = (Button) findViewById(R.id.button_history_id);

        dailyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Layout1.setVisibility(View.VISIBLE);
                Layout2.setVisibility(View.GONE);
                Layout3.setVisibility(View.GONE);
                v.setPressed(true);
            }
        });
        listButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Layout1.setVisibility(View.GONE);
                Layout2.setVisibility(View.VISIBLE);
                Layout3.setVisibility(View.GONE);
                v.setPressed(true);
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Layout1.setVisibility(View.GONE);
                Layout2.setVisibility(View.GONE);
                Layout3.setVisibility(View.VISIBLE);
                v.setPressed(true);
            }
        });

        dailyButton.setPressed(true);
        listButton.setPressed(false);
        historyButton.setPressed(false);
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);

        ScrollView sv = (ScrollView)findViewById(R.id.scrollView_daily_id);
        sv.setVerticalScrollBarEnabled(false);
        sv = (ScrollView)findViewById(R.id.scrollView_list_id);
        sv.setVerticalScrollBarEnabled(false);

        dayViewPrescription1 = (LinearLayout) findViewById(R.id.presc_t_list_id);
        dayViewPrescription2 = (LinearLayout) findViewById(R.id.presc_wt_list_id);

        listViewPrescription1 = (LinearLayout) findViewById(R.id.list_t_list_id);
        listViewPrescription2 = (LinearLayout) findViewById(R.id.list_wt_list_id);

        loadTestDayData();
        loadTestListData();
        loadTestHistoryData();

    }

    public void dayPressed(View view) {
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);
        //view.setPressed(true);
        view.setSelected(true);
        listButton.setSelected(false);
        historyButton.setSelected(false);
    }
    public void listPressed(View view) {
        Layout1.setVisibility(View.GONE);
        Layout2.setVisibility(View.VISIBLE);
        Layout3.setVisibility(View.GONE);
        dailyButton.setSelected(false);
        view.setPressed(true);
        view.setSelected(true);
        historyButton.setSelected(false);
    }
    public void historyPressed(View view) {
        Layout1.setVisibility(View.GONE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.VISIBLE);
        dailyButton.setSelected(false);
        listButton.setSelected(false);
        view.setPressed(true);
        view.setSelected(true);
    }

    public void loadTestDayData(){
        addPrescriptionTimedDay("10:30", "Morning walk", true);
        addPrescriptionTimedDay("10:31", "Blood preasure measurement", false);
        addPrescriptionTimedDay("10:33", "Some other activity", true);
        addPrescriptionTimedDay("10:30", "Morning walk", true);
        addPrescriptionTimedDay("10:31", "Blood preasure measurement", false);
        addPrescriptionTimedDay("10:33", "Some other activity", true);
        addPrescriptionTimedDay("10:30", "Morning walk", true);
        addPrescriptionTimedDay("10:31", "Blood preasure measurement", false);
        addPrescriptionTimedDay("10:33", "Some other activity", true);

        addPrescriptionConditionedDay("Cycling", "Depends on weather", false);
        addPrescriptionConditionedDay("Walking", "If it's sunny", true);
        addPrescriptionConditionedDay("lolling", "-------", true);
        addPrescriptionConditionedDay("Cycling", "Depends on weather", false);
        addPrescriptionConditionedDay("Walking", "If it's sunny", true);
        addPrescriptionConditionedDay("lolling", "-------", true);
        addPrescriptionConditionedDay("Cycling", "Depends on weather", false);
        addPrescriptionConditionedDay("Walking", "If it's sunny", true);
        addPrescriptionConditionedDay("lolling", "-------", true);

        showPrescriptionsDayTime();
        showPrescriptionsDayConditioned();
    }
    public void loadTestListData() {
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 40);
        addPrescriptionTimedList("11:30", "16:30", "Blood pressure measurement", 67);
        addPrescriptionTimedList("15:30", "19:30", "Enalapril 5mg", 80);
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 40);
        addPrescriptionTimedList("11:30", "16:30", "Blood pressure measurement", 67);
        addPrescriptionTimedList("15:30", "19:30", "Enalapril 5mg", 80);

        addPrescriptionConditionedList("Cycling", "Depending on weather", 100);
        addPrescriptionConditionedList("Ketorol", "In case of pain", 40);
        addPrescriptionConditionedList("Cycling", "Depending on weather", 100);
        addPrescriptionConditionedList("Ketorol", "In case of pain", 40);
        addPrescriptionConditionedList("Cycling", "Depending on weather", 100);
        addPrescriptionConditionedList("Ketorol", "In case of pain", 40);

        showPrescriptionsListTime();
        showPrescriptionsListConditioned();
    }
    public void loadTestHistoryData() {}


    public void showPrescriptionsDayTime(){
        for(int i = 0; i < pwtList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(this).inflate(R.layout.prescription_timed_listitem_layout, null);
            TextView t_time = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_time_id);
            TextView t_activity = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_activity_id);
            FrameLayout t_fl = (FrameLayout)prescriptionListLayout.findViewById(R.id.presc_t_layout_id);

            Configuration conf = Configuration.getDefault(3);
            conf.setOnColor(getResources().getColor(R.color.green));
            conf.setOffColor(getResources().getColor(R.color.darkred));
            SwitchButton sb = new SwitchButton(t_fl.getContext());
            sb.setConfiguration(conf);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT );
            lp.gravity = Gravity.CENTER | Gravity.RIGHT;
            sb.setLayoutParams(lp);

            sb.setChecked(pwtList.get(i).isEnabled());
            t_time.setText(pwtList.get(i).getTime());
            t_activity.setText(pwtList.get(i).getPrescription());
            t_fl.addView(sb);

            if((i % 2) != 0) prescriptionListLayout.setBackgroundColor(getResources().getColor(R.color.lightblue));

            dayViewPrescription1.addView(prescriptionListLayout);
        }
    }
    public void showPrescriptionsDayConditioned(){
        for(int i = 0; i < pwotList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(this).inflate(R.layout.prescription_without_time_listitem_layout, null);
            TextView wt_activity = (TextView)prescriptionListLayout.findViewById(R.id.presc_wt_activity_id);
            TextView wt_condition = (TextView)prescriptionListLayout.findViewById(R.id.presc_wt_condition_id);
            FrameLayout t_fl = (FrameLayout)prescriptionListLayout.findViewById(R.id.presc_wt_layout_id);

            Configuration conf = Configuration.getDefault(3);
            conf.setOnColor(getResources().getColor(R.color.green));
            conf.setOffColor(getResources().getColor(R.color.darkred));
            SwitchButton sb = new SwitchButton(t_fl.getContext());
            sb.setConfiguration(conf);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT );
            lp.gravity = Gravity.CENTER | Gravity.RIGHT;
            sb.setLayoutParams(lp);

            sb.setChecked(pwotList.get(i).isEnabled());
            wt_activity.setText(pwotList.get(i).getActivity());
            wt_condition.setText(pwotList.get(i).getCondition());
            t_fl.addView(sb);

            if((i % 2) != 0) prescriptionListLayout.setBackgroundColor(getResources().getColor(R.color.lightblue));

            dayViewPrescription2.addView(prescriptionListLayout);
        }
    }
    public void addPrescriptionTimedDay(String time, String prescription, boolean state) {
        pwtList.add(new PrescriptionsWithTime(time, prescription, state));
    }
    public void addPrescriptionConditionedDay(String activity, String condition, boolean state) {
        pwotList.add(new PrescriptionsWithoutTime(activity, condition, state));
    }

    public void showPrescriptionsListTime(){
        for(int i = 0; i < listpwtList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(this).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView t_time = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_time);
            TextView t_activity = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_activity_id);
            ProgressBar l_progressBar = (ProgressBar)prescriptionListLayout.findViewById(R.id.list_progressBar_id);


            l_progressBar.setProgress(listpwtList.get(i).getProgress());
            t_time.setText(listpwtList.get(i).getTimeStart() + ", " + listpwtList.get(i).getTimeEnd());
            t_activity.setText(listpwtList.get(i).getDescription());

            if((i % 2) != 0) prescriptionListLayout.setBackgroundColor(getResources().getColor(R.color.lightblue));

            listViewPrescription1.addView(prescriptionListLayout);
        }
    }
    public void showPrescriptionsListConditioned(){
        for(int i = 0; i < listpwotList.size(); ++i){
            View withoutTimePrescriptionList = LayoutInflater.from(this).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView activity = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_activity_id);
            TextView condition = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_time);
            ProgressBar l_progressBar = (ProgressBar)withoutTimePrescriptionList.findViewById(R.id.list_progressBar_id);


            l_progressBar.setProgress(listpwotList.get(i).getProgress());
            activity.setText(listpwotList.get(i).getActivity());
            condition.setText(listpwotList.get(i).getCondition());

            if((i % 2) != 0) withoutTimePrescriptionList.setBackgroundColor(getResources().getColor(R.color.lightblue));

            listViewPrescription2.addView(withoutTimePrescriptionList);
        }
    }
    public void addPrescriptionTimedList(final String timeStart, final String timeEnd, final String prescription, int progress) {
        listpwtList.add(new PListWithTime(timeStart, timeEnd, prescription, progress));
    }
    public void addPrescriptionConditionedList(String activity, String condition, int progress) {
        listpwotList.add(new PListWithoutTime(activity, condition, progress));
    }

    public void showDefaultContent(){
        dailyButton.setPressed(true);
        dailyButton.performClick();
    }

    public void doLeftClick(View view) {
    }
    public void doRightClick(View view) {
    }





    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
