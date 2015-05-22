package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends Activity {
    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;
    private TextView dateBox;
    private ListView listView;
    ArrayList<PrescriptionListItem> prescriptionList = new ArrayList<>();
    private PrescriptionsAdapter listAdapter;

    private class PrescriptionListItem {
        public int imgResource;
        public String time;
        public String description;
        public String message;

        public PrescriptionListItem(int imgRes, String time, String description, String message){
            this.imgResource = imgRes;
            this.time = time;
            this.description = description;
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }

        public String getMessage() {
            return message;
        }

    }

    private class PrescriptionsAdapter extends ArrayAdapter<PrescriptionListItem> {

        ArrayList<PrescriptionListItem> prescriptionListItems = null;

        public PrescriptionsAdapter() {
            super(HomeActivity.this, R.layout.prescription_listitem_layout, prescriptionList);
            prescriptionListItems = prescriptionList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.prescription_listitem_layout, null, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.getImage().setImageResource(prescriptionListItems.get(position).imgResource);
            holder.getTimeText().setText(prescriptionListItems.get(position).getTime());
            holder.getDescriptionText().setText(prescriptionListItems.get(position).getDescription());
            holder.getMsgText().setText(prescriptionListItems.get(position).getMessage());

            return convertView;
        }

        public class ViewHolder {
            private View row;
            private TextView timeText = null, descText = null, msgText = null;
            private ImageView imageView = null;

            public ViewHolder(View row) {
                this.row = row;
            }

            public ImageView getImage() {
                if (this.imageView == null) {
                    this.imageView = (ImageView) row.findViewById(R.id.presc_icon_id);
                }
                return this.imageView;
            }

            public TextView getTimeText() {
                if (this.timeText == null) {
                    this.timeText = (TextView) row.findViewById(R.id.presc_time_id);
                }
                return this.timeText;
            }

            public TextView getDescriptionText() {
                if (this.descText == null) {
                    this.descText = (TextView) row.findViewById(R.id.presc_desc_id);
                }
                return this.descText;
            }

            public TextView getMsgText() {
                if (this.msgText == null) {
                    this.msgText = (TextView) row.findViewById(R.id.presc_msg_id);
                }
                return this.msgText;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_content);

        View tabView = findViewById(R.id.tab_layout);
        prescriptionTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_1);
        measurementsTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_2);
        journalTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_3);
        feedbackTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_4);
        moreTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_5);

        prescriptionTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), PrescriptionsActivity.class));
            }
        });
        measurementsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MeasurementsActivity.class));
            }
        });
        journalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), JournalActivity.class));
            }
        });
        feedbackTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), FeedbackActivity.class));
            }
        });
        moreTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MoreActivity.class));
            }
        });

        dateBox = (TextView)findViewById(R.id.dateTextView_id);
        Calendar calendar = Calendar.getInstance();
        setDate(new StringBuilder().append(String.format(Locale.US, "%tB", calendar))
        .append(" <b>")
        .append(calendar.get(Calendar.DAY_OF_MONTH))
        .append("</b>").toString());

       // listView = (ListView) findViewById(R.id.prescription_list_id);
        listAdapter = new PrescriptionsAdapter();
        listView.setAdapter(listAdapter);
        addPrescription("10:30", "You missed prescription:", "Measurable Pressure", R.drawable.alert_icon1_48);
        addPrescription("09:43", "Prescription added:", "Aspirin twice a day", R.drawable.presc_added);
        addPrescription("09:30", "Prescription canceled:", "Aspirin twice a day", R.drawable.presc_canceled);
        addPrescription("09:30", "Prescription added:", "Aspirin twice a day", R.drawable.alert_icon4_48);
        addPrescription("10:30", "You missed prescription:", "Measurable Pressure", R.drawable.alert_icon1_48);
        addPrescription("09:43", "Prescription added:", "Aspirin twice a day", R.drawable.presc_added);
        addPrescription("09:30", "Prescription canceled:", "Aspirin twice a day", R.drawable.presc_canceled);
        addPrescription("09:30", "Prescription added:", "Aspirin twice a day", R.drawable.alert_icon4_48);
    }

    public void onResume() {
        super.onResume();
        prescriptionTab.setSelected(false);
        measurementsTab.setSelected(false);
        journalTab.setSelected(false);
        feedbackTab.setSelected(false);
        moreTab.setSelected(false);
    }

    public void setDate(String dateStr) {
        dateBox.setText(Html.fromHtml(dateStr));
    }

    public void addPrescription(String time, String description, String message, int mode) {
        prescriptionList.add(new PrescriptionListItem(mode, time, description, message));
        listAdapter.notifyDataSetChanged();
    }
}
