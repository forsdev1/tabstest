package uhuhuhu.tabtest;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class JournalActivity extends Activity {
    private TabHost tabHost = null;
    private LinearLayout journalListLayout = null;
    private ArrayList<JournalItem> journalList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        journalListLayout = (LinearLayout) findViewById(R.id.journal_list_layout);
        loadTestListData();
    }
    public void addJournalItem(final Drawable drawable, final String date, final String time, String comment) {
        journalList.add(new JournalItem(drawable, date, time, comment));
    }
    public void loadTestListData() {
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_blue), "Today", "15:30", "Nice day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_red), "Yesterday", "11:30", "Bad day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_green), "18/14/15", "18:10", "Awesome day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_yellow), "18/14/15", "18:10", "............Magnificent special omnipresent caturday");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_blue), "Today", "15:30", "Nice day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_red), "Yesterday", "11:30", "Bad day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_blue), "Today", "15:30", "Nice day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_green), "18/14/15", "18:10", "Awesome day");
        addJournalItem(getResources().getDrawable(R.drawable.label_rounded_red), "Yesterday", "11:30", "Bad day");

        showJournalItems();
    }

    private void showJournalItems() {
        for(int i = 0; i < journalList.size(); ++i){
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
        tabHost.setCurrentTab(0);
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
}
