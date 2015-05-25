package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.Configuration;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;


public class PrescriptionsFragment extends Fragment {
    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;
    private Button dailyButton = null;
    private Button listButton = null;
    private Button historyButton = null;
    private Button addButton = null;

    private RelativeLayout Layout1 = null;
    private RelativeLayout Layout2 = null;
    private RelativeLayout Layout3 = null;

    private LinearLayout dayViewPrescription1 = null;
    private LinearLayout dayViewPrescription2 = null;
    private LinearLayout listViewPrescription1 = null;
    private LinearLayout listViewPrescription2 = null;
    private LinearLayout historyViewPrescription1 = null;
    private LinearLayout historyViewPrescription2 = null;

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

        public boolean isEnabled() {
            return enabled;
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

        public String getCondition() {
            return condition;
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

        public String getTimeEnd() {
            return timeEnd;
        }

        public String getDescription() {
            return description;
        }

        public int getProgress() {
            return progress;
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

        public int getProgress() {
            return progress;
        }
    }

    private View tabView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prescriptions_content, container, false);

        tabView = getFragmentManager().findFragmentById(R.id.tab_fragment).getView().findViewById(R.id.tab_layout);
        FragmentHolder.setActualFragment(this);
        tabView.findViewById(R.id.tab_layout_1).setSelected(true);

        Layout1 = (RelativeLayout)view.findViewById(R.id.prl_id_1);
        Layout2 = (RelativeLayout)view.findViewById(R.id.prl_id_2);
        Layout3 = (RelativeLayout)view.findViewById(R.id.prl_id_3);
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);

        dailyButton = (Button) view.findViewById(R.id.button_daily_id);
        listButton = (Button) view.findViewById(R.id.button_list_id);
        historyButton = (Button) view.findViewById(R.id.button_history_id);
        addButton = (Button) view.findViewById(R.id.presc_add_button);
        addButton.setVisibility(View.GONE);

        setupDayLayout(view);
        setupListLayout(view);
        setupHistoryLayout(view);

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            FragmentHolder.setActualFragment(this);
            tabView.findViewById(R.id.tab_layout_1).setSelected(true);
        } else {
            tabView.findViewById(R.id.tab_layout_1).setSelected(false);
        }
    }

    public void setupDayLayout(View view) {
        dailyButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setPressed(true);
                listButton.setPressed(false);
                historyButton.setPressed(false);
                Layout1.setVisibility(View.VISIBLE);
                Layout2.setVisibility(View.GONE);
                Layout3.setVisibility(View.GONE);

                addButton.setVisibility(View.GONE);
                return true;
            }
        });

        dailyButton.setPressed(true);
        Layout1.setVisibility(View.VISIBLE);
        Layout2.setVisibility(View.GONE);
        Layout3.setVisibility(View.GONE);
        //ScrollView sv = (ScrollView)view.findViewById(R.id.scrollView_daily_id);
        //sv.setVerticalScrollBarEnabled(false);

        dayViewPrescription1 = (LinearLayout) view.findViewById(R.id.presc_t_list_id);
        dayViewPrescription2 = (LinearLayout) view.findViewById(R.id.presc_wt_list_id);

        loadTestDayData();
    }
    public void setupListLayout(View view) {
        listButton.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setPressed(true);
                dailyButton.setPressed(false);
                historyButton.setPressed(false);
                Layout1.setVisibility(View.GONE);
                Layout2.setVisibility(View.VISIBLE);
                Layout3.setVisibility(View.GONE);
                addButton.setVisibility(View.VISIBLE);
                return true;
            }
        });

        //ScrollView sv = (ScrollView)view.findViewById(R.id.scrollView_list_id);
        //sv.setVerticalScrollBarEnabled(false);

        listViewPrescription1 = (LinearLayout) view.findViewById(R.id.list_t_list_id);
        listViewPrescription2 = (LinearLayout) view.findViewById(R.id.list_wt_list_id);

        historyViewPrescription1 = (LinearLayout) view.findViewById(R.id.list_t_history_id);
        historyViewPrescription2 = (LinearLayout) view.findViewById(R.id.list_wt_history_id);

        loadTestListData();
    }
    public void setupHistoryLayout(View view) {
        historyButton.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setPressed(true);
                dailyButton.setPressed(false);
                listButton.setPressed(false);
                Layout1.setVisibility(View.GONE);
                Layout2.setVisibility(View.GONE);
                Layout3.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.GONE);
                return true;
            }
        });

        //ScrollView sv = (ScrollView)view.findViewById(R.id.scrollView_history_id);
        //sv.setVerticalScrollBarEnabled(false);

        historyViewPrescription1 = (LinearLayout) view.findViewById(R.id.list_t_history_id);
        historyViewPrescription2 = (LinearLayout) view.findViewById(R.id.list_wt_history_id);

        loadTestHistoryData();
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
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 0);
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 1);
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 3);
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 10);
        addPrescriptionTimedList("10:30", "12:30", "Morning walk", 20);
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
    public void loadTestHistoryData() {
        showPrescriptionsHistoryTime();
        showPrescriptionsHistoryConditioned();
    }


    public void showPrescriptionsDayTime(){
        for(int i = 0; i < pwtList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_timed_listitem_layout, null);
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
            View prescriptionListLayout = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_without_time_listitem_layout, null);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPrescriptionsListTime(){
        for(int i = 0; i < listpwtList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView t_time = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_time);
            TextView t_activity = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_activity_id);
            ProgressBar l_progressBar = (ProgressBar)prescriptionListLayout.findViewById(R.id.list_progressBar_id);

            int progress = listpwtList.get(i).getProgress();
            LayerDrawable pgDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.prescription_progressbar);
            pgDrawable.setColorFilter(getColorForCurrentProgress(progress), PorterDuff.Mode.MULTIPLY);
            l_progressBar.setProgressDrawable(pgDrawable);
            l_progressBar.setProgress(progress);

            String timeStart = listpwtList.get(i).getTimeStart();
            String timeEnd = listpwtList.get(i).getTimeEnd();
            t_time.setText(timeStart + (timeEnd.length() == 0 ? "" : ", ") + timeEnd);
            t_activity.setText(listpwtList.get(i).getDescription());

            if((i % 2) != 0) prescriptionListLayout.setBackgroundColor(getResources().getColor(R.color.lightblue));

            listViewPrescription1.addView(prescriptionListLayout);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPrescriptionsListConditioned(){
        for(int i = 0; i < listpwotList.size(); ++i){
            View withoutTimePrescriptionList = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView activity = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_activity_id);
            TextView condition = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_time);
            ProgressBar l_progressBar = (ProgressBar)withoutTimePrescriptionList.findViewById(R.id.list_progressBar_id);

            int progress = listpwotList.get(i).getProgress();
            LayerDrawable pgDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.prescription_progressbar);
            pgDrawable.setColorFilter(getColorForCurrentProgress(progress), PorterDuff.Mode.MULTIPLY);
            l_progressBar.setProgressDrawable(pgDrawable);
            l_progressBar.setProgress(progress);

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


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPrescriptionsHistoryTime(){
        for(int i = 0; i < listpwtList.size(); ++i){
            View prescriptionListLayout = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView t_time = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_time);
            TextView t_activity = (TextView)prescriptionListLayout.findViewById(R.id.presc_t_list_activity_id);
            ProgressBar l_progressBar = (ProgressBar)prescriptionListLayout.findViewById(R.id.list_progressBar_id);

            int progress = listpwtList.get(i).getProgress();
            LayerDrawable pgDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.prescription_progressbar);
            pgDrawable.setColorFilter(getColorForCurrentProgress(progress), PorterDuff.Mode.MULTIPLY);
            l_progressBar.setProgressDrawable(pgDrawable);
            l_progressBar.setProgress(progress);

            String timeStart = listpwtList.get(i).getTimeStart();
            String timeEnd = listpwtList.get(i).getTimeEnd();
            t_time.setText(timeStart + (timeEnd.length() == 0 ? "" : ", ") + timeEnd);
            t_activity.setText(listpwtList.get(i).getDescription());

            if((i % 2) != 0) prescriptionListLayout.setBackgroundColor(getResources().getColor(R.color.lightblue));

            historyViewPrescription1.addView(prescriptionListLayout);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPrescriptionsHistoryConditioned(){
        for(int i = 0; i < listpwotList.size(); ++i){
            View withoutTimePrescriptionList = LayoutInflater.from(getActivity()).inflate(R.layout.prescription_list_timed_listitem, null);
            TextView activity = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_activity_id);
            TextView condition = (TextView)withoutTimePrescriptionList.findViewById(R.id.presc_t_list_time);
            ProgressBar l_progressBar = (ProgressBar)withoutTimePrescriptionList.findViewById(R.id.list_progressBar_id);

            int progress = listpwotList.get(i).getProgress();
            LayerDrawable pgDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.prescription_progressbar);
            pgDrawable.setColorFilter(getColorForCurrentProgress(progress), PorterDuff.Mode.MULTIPLY);
            l_progressBar.setProgressDrawable(pgDrawable);
            l_progressBar.setProgress(progress);

            activity.setText(listpwotList.get(i).getActivity());
            condition.setText(listpwotList.get(i).getCondition());

            if((i % 2) != 0) withoutTimePrescriptionList.setBackgroundColor(getResources().getColor(R.color.lightblue));

            historyViewPrescription2.addView(withoutTimePrescriptionList);
        }
    }

    public void doLeftClick(View view) {
    }
    public void doRightClick(View view) {
    }

    public int getColorForCurrentProgress(int progressPercentage){
        int rColor = 255;
        int gColor = 0;

        Double resultPreColorValueDouble = (progressPercentage * 5.1);
        int resultPreColorValue = resultPreColorValueDouble.intValue();

        if(resultPreColorValue > 255) {
            resultPreColorValue -= 255;
            rColor -= resultPreColorValue;
            gColor = 255;
        } else {
            rColor = 255;
            gColor = resultPreColorValue;
        }

        String color = colorDecToHex(rColor, gColor, 0);
        return Color.parseColor(color);
    }
    public static String colorDecToHex(int p_red, int p_green, int p_blue)
    {
        String red = Integer.toHexString(p_red);
        String green = Integer.toHexString(p_green);
        String blue = Integer.toHexString(p_blue);

        if (red.length() == 1)
        {
            red = "0" + red;
        }
        if (green.length() == 1)
        {
            green = "0" + green;
        }
        if (blue.length() == 1)
        {
            blue = "0" + blue;
        }

        String colorHex = "#" + red + green + blue;
        return colorHex;
    }
}
