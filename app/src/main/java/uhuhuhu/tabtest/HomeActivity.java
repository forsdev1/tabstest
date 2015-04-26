package uhuhuhu.tabtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends Activity {

    private TextView dateBox;
    private ListView listView;
    ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter<String> listAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_content);

        dateBox = (TextView)findViewById(R.id.dateTextView_id);
        setDate("APRIL <b>13</b>");

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        addPrescription("data1", 0);
    }

    public void setDate(String dateStr) {
        dateBox.setText(Html.fromHtml(dateStr));
    }

    public void addPrescription(String prescription, int mode) {
        listItems.add(prescription);
        listAdapter.notifyDataSetChanged();
    }
}
