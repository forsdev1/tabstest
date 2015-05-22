package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class JournalActivity extends Activity {
    private RelativeLayout prescriptionTab = null;
    private RelativeLayout measurementsTab = null;
    private RelativeLayout journalTab = null;
    private RelativeLayout feedbackTab = null;
    private RelativeLayout moreTab = null;
    private LinearLayout journalListLayout = null;
    private ArrayList<JournalItem> journalList = new ArrayList<>();
    private Button addButton = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_tab_content);

        View tabView = findViewById(R.id.tab_layout);
        prescriptionTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_1);
        measurementsTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_2);
        journalTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_3);
        feedbackTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_4);
        moreTab = (RelativeLayout) tabView.findViewById(R.id.tab_layout_5);

        setSelectedTab(journalTab);

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

        addButton = (Button) findViewById(R.id.journal_add_button);
        journalListLayout = (LinearLayout) findViewById(R.id.journal_list_layout);
        loadTestListData();

        addButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startActivityForResult(new Intent(getBaseContext(), NewJournalActivity.class), 1);
                }
                return false;
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void addJournalItem(final String drawable, final String date, final String time, String comment) {
        Drawable dr = getDrawable(R.drawable.label_rounded_green);
        switch(drawable){
            case "red":
                dr = getDrawable(R.drawable.label_rounded_red);
                break;
            case "orange":
                dr = getDrawable(R.drawable.label_rounded_orange);
                break;
            case "yellow":
                dr = getDrawable(R.drawable.label_rounded_yellow);
                break;
            case "green":
                dr = getDrawable(R.drawable.label_rounded_green);
                break;
            case "sky":
                dr = getDrawable(R.drawable.label_rounded_sky);
                break;
            case "blue":
                dr = getDrawable(R.drawable.label_rounded_blue);
                break;
        }
        journalList.add(new JournalItem(dr, date, time, comment));
    }
    public void loadTestListData() {
        addJournalItem("blue", "Today", "15:30", "Nice day");
        addJournalItem("red", "Yesterday", "11:30", "Bad day");
        addJournalItem("sky", "18/14/15", "18:10", "Awesome day");
        addJournalItem("green", "18/14/15", "18:10",
                "............Magnificent special omnipresent long caturday");
        addJournalItem("orange", "Today", "15:30", "Nice day");
        addJournalItem("red", "Yesterday", "11:30", "Bad day");
        addJournalItem("blue", "Today", "15:30", "Nice day");
        addJournalItem("green", "18/14/15", "18:10", "Awesome day");
        addJournalItem("sky", "Yesterday", "11:30", "Bad day");

        showJournalItems();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                addJournalItem(data.getStringExtra("drawable"),
                                data.getStringExtra("date"),
                                data.getStringExtra("time"),
                                data.getStringExtra("comment") );
                showJournalItems();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showJournalItems() {
        journalListLayout.removeViews(0, journalListLayout.getChildCount());
        for(int i = journalList.size() - 1; i >= 0; --i){
            View itemView = LayoutInflater.from(this).inflate(R.layout.journal_list_item, null);
            RelativeLayout icon = (RelativeLayout) itemView.findViewById(R.id.journal_item_icon);
            TextView date = (TextView) itemView.findViewById(R.id.journal_item_date);
            TextView time = (TextView) itemView.findViewById(R.id.journal_item_time);
            TextView comment = (TextView) itemView.findViewById(R.id.journal_item_comment);

            icon.setBackground(journalList.get(i).getDrawable());
            date.setText(journalList.get(i).getDate());
            time.setText(journalList.get(i).getTime());
            comment.setText(journalList.get(i).getComment());

            if((i % 2) != 0) itemView.setBackgroundColor(getResources().getColor(R.color.lightblue));

            journalListLayout.addView(itemView);
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    private class JournalItem {
        private Drawable drawable;
        private String date;
        private String time;
        private String comment;


        private JournalItem(Drawable drawable, String date, String time, String comment) {
            this.drawable = drawable;
            this.date = date;
            this.time = time;
            this.comment = comment;
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
    public void setSelectedTab(View selectedTab) {
        prescriptionTab.setSelected(false);
        measurementsTab.setSelected(false);
        journalTab.setSelected(false);
        feedbackTab.setSelected(false);
        moreTab.setSelected(false);
        selectedTab.setSelected(true);
        selectedTab.setEnabled(false);
    }
}
