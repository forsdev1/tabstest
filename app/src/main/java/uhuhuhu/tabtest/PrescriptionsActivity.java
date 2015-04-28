package uhuhuhu.tabtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class PrescriptionsActivity extends ActionBarActivity {
    private TabHost tabHost = null;
    private Button dailyButton = null;
    private Button listButton = null;
    private Button historyButton = null;

    private RelativeLayout Layout1 = null;
    private RelativeLayout Layout2 = null;
    private RelativeLayout Layout3 = null;

    private PrescriptionTimedAdapter prescTimeApapter;
    private PrescriptionNoTimeAdapter prescNoTimeApapter;

    ArrayList<TimedPrescriptionListItem> timedPrescriptionList = new ArrayList<>();
    ArrayList<NoTimePrescriptionListItem> noTimePrescriptionList = new ArrayList<>();

    private class TimedPrescriptionListItem {
        public String time;
        public String prescription;
        public boolean enabled;

        public TimedPrescriptionListItem(String time, String prescription, boolean enabled) {
            this.time = time;
            this.prescription = prescription;
            this.enabled = enabled;
        }

        public String getTime() {
            return time;
        }

        public String getPrescription() {
            return prescription;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }
    private class NoTimePrescriptionListItem {
        public String condition;
        public String prescription;
        public boolean enabled;

        public NoTimePrescriptionListItem(String condition, String prescription, boolean enabled) {
            this.condition = condition;
            this.prescription = prescription;
            this.enabled = enabled;
        }

        public String getCondition() {
            return condition;
        }

        public String getPrescription() {
            return prescription;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }





    private class PrescriptionTimedAdapter extends ArrayAdapter<TimedPrescriptionListItem>{
        ArrayList<TimedPrescriptionListItem> timedPrescriptionAdapterList = null;

        public PrescriptionTimedAdapter() {
            super(PrescriptionsActivity.this, R.layout.prescription_timed_listitem_layout, timedPrescriptionList);
            timedPrescriptionAdapterList = timedPrescriptionList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TimedViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.prescription_timed_listitem_layout, null, false);
                holder = new TimedViewHolder(convertView);
                convertView.setTag(holder);
            }
            else {
                holder = (TimedViewHolder) convertView.getTag();
            }

            holder.getTimeText().setText(timedPrescriptionAdapterList.get(position).getTime());
            holder.getDescriptionText().setText(timedPrescriptionAdapterList.get(position).getPrescription());
            holder.getEnabled().setPressed(timedPrescriptionAdapterList.get(position).isEnabled());

            return convertView;
        }
        public class TimedViewHolder {
            private View row;
            private TextView timeText = null, descText = null;
            private Switch enabled = null;

            public TimedViewHolder(View row) {
                this.row = row;
            }

            public TextView getTimeText() {
                if (this.timeText == null) {
                    this.timeText = (TextView) row.findViewById(R.id.presc_t_time_id);
                }
                return this.timeText;
            }

            public TextView getDescriptionText() {
                if (this.descText == null) {
                    this.descText = (TextView) row.findViewById(R.id.presc_t_activity_id);
                }
                return this.descText;
            }

            public Switch getEnabled() {
                if (this.enabled == null) {
                    this.enabled = (Switch) row.findViewById(R.id.presc_t_switch_id);
                }
                return this.enabled;
            }
        }
    }
    private class PrescriptionNoTimeAdapter extends ArrayAdapter<NoTimePrescriptionListItem>{
        ArrayList<NoTimePrescriptionListItem> noTimePrescriptionListAdapter = null;

        public PrescriptionNoTimeAdapter() {
            super(PrescriptionsActivity.this, R.layout.prescription_without_time_listitem_layout, noTimePrescriptionList);
            noTimePrescriptionListAdapter = noTimePrescriptionList;
        }

        @Override
         public View getView(int position, View convertView, ViewGroup parent) {
            NoTimeViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.prescription_without_time_listitem_layout, null, false);
                holder = new NoTimeViewHolder(convertView);
                convertView.setTag(holder);
            }
            else {
                holder = (NoTimeViewHolder) convertView.getTag();
            }

            holder.getActivityText().setText(noTimePrescriptionListAdapter.get(position).getPrescription());
            holder.getConditionText().setText(noTimePrescriptionListAdapter.get(position).getCondition());
            holder.getEnabled().setPressed(noTimePrescriptionListAdapter.get(position).isEnabled());

            return convertView;
        }
        public class NoTimeViewHolder {
            private View row;
            private TextView activityText = null, conditionText = null;
            private Switch enabled = null;

            public NoTimeViewHolder(View row) {
                this.row = row;
            }

            public TextView getActivityText() {
                if (this.activityText == null) {
                    this.activityText = (TextView) row.findViewById(R.id.presc_wt_activity_id);
                }
                return this.activityText;
            }

            public TextView getConditionText() {
                if (this.conditionText == null) {
                    this.conditionText = (TextView) row.findViewById(R.id.presc_wt_condition_id);
                }
                return this.conditionText;
            }

            public Switch getEnabled() {
                if (this.enabled == null) {
                    this.enabled = (Switch) row.findViewById(R.id.presc_wt_switch_id);
                }
                return this.enabled;
            }
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


        //Set button listeners
        {
            dailyButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dailyButton.setPressed(true);
                    listButton.setPressed(false);
                    historyButton.setPressed(false);

                    v.performClick();
                    return true;
                }
            });
            dailyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Layout1.setVisibility(View.VISIBLE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.GONE);
                    setDailyLayout();
                }
            });


            listButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dailyButton.setPressed(false);
                    listButton.setPressed(true);
                    historyButton.setPressed(false);

                    v.performClick();
                    return true;
                }
            });
            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.VISIBLE);
                    Layout3.setVisibility(View.GONE);
                    setListLayout();
                }
            });


            historyButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dailyButton.setPressed(false);
                    listButton.setPressed(false);
                    historyButton.setPressed(true);

                    v.performClick();
                    return true;
                }
            });
            historyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.VISIBLE);
                    setHistoryLayout();
                }
            });
        }

        ListView listView1 = (ListView) findViewById(R.id.presc_listview);
        ListView listView2 = (ListView) findViewById(R.id.presc_listView_withouttime_id);

        prescTimeApapter = new PrescriptionTimedAdapter();
        prescNoTimeApapter = new PrescriptionNoTimeAdapter();

        listView1.setAdapter(prescTimeApapter);
        listView2.setAdapter(prescNoTimeApapter);

        addPrescriptionTimed("10:30", "Morning walk", true);
        addPrescriptionTimed("10:31", "Blood preasure measurement", false);
        addPrescriptionTimed("10:33", "You missed prescription2:", true);

        addPrescriptionConditioned("Cycling", "Depends on weather", false);
        addPrescriptionConditioned("Walking", "If it's sunny", true);
        addPrescriptionConditioned("lolling", "-------", true);

        showDefaultConent();


    }

    public void addPrescriptionTimed(String time, String prescription, boolean state) {
        timedPrescriptionList.add(new TimedPrescriptionListItem(time, prescription, state));
        prescTimeApapter.notifyDataSetChanged();
    }
    public void addPrescriptionConditioned(String condition, String prescription, boolean state) {
        noTimePrescriptionList.add(new NoTimePrescriptionListItem(condition, prescription, state));
        prescNoTimeApapter.notifyDataSetChanged();
    }

    public void showDefaultConent(){
        dailyButton.setPressed(true);
        dailyButton.performClick();
    }

    public void setDailyLayout() {
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.presc_activity_container_id);
        View child1 = LayoutInflater.from(this).inflate(
                R.layout.presc_daily_layout, null);
        inclusionViewGroup.addView(child1);
    }

    public void setListLayout() {
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.presc_activity_container_id);
        View child1 = LayoutInflater.from(this).inflate(
                R.layout.presc_list_layout, null);
        inclusionViewGroup.addView(child1);
    }

    public void setHistoryLayout() {
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.presc_activity_container_id);
        View child1 = LayoutInflater.from(this).inflate(
                R.layout.presc_history_layout, null);
        inclusionViewGroup.addView(child1);
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
