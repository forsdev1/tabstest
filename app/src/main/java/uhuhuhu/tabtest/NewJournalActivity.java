package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class NewJournalActivity extends Activity{
    private Button redButton = null;
    private Button orangeButton = null;
    private Button yellowButton = null;
    private Button greenButton = null;
    private Button skyButton = null;
    private Button blueButton = null;
    private Button pressedButton = null;
    private TextView dateTV = null;
    private TextView timeTV = null;
    private EditText commentBox = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_journalitem_content);

        dateTV = (TextView) findViewById(R.id.new_journal_item_date);
        timeTV = (TextView) findViewById(R.id.new_journal_item_time);
        commentBox = (EditText) findViewById(R.id.new_journal_comment_box);

        Calendar c = Calendar.getInstance();

        dateTV.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)) +
                 "/" + String.valueOf(c.get(Calendar.MONTH)) +
                 "/" + String.valueOf(c.get(Calendar.YEAR)));
        timeTV.setText(String.valueOf(c.get(Calendar.HOUR)) + ":" + String.valueOf(c.get(Calendar.MINUTE)));

        redButton = (Button) findViewById(R.id.red_button_id);
        orangeButton = (Button) findViewById(R.id.orange_button_id);
        yellowButton = (Button) findViewById(R.id.yellow_button_id);
        greenButton = (Button) findViewById(R.id.green_button_id);
        skyButton = (Button) findViewById(R.id.sky_button_id);
        blueButton = (Button) findViewById(R.id.blue_button_id);

        setOnTouchListener(redButton);
        setOnTouchListener(orangeButton);
        setOnTouchListener(yellowButton);
        setOnTouchListener(greenButton);
        setOnTouchListener(skyButton);
        setOnTouchListener(blueButton);

        greenButton.setPressed(true);
        pressedButton = greenButton;
    }

    public void done(View view) {
        Intent intent = new Intent();
        switch(pressedButton.getId()){
            case R.id.red_button_id:
                intent.putExtra("drawable", "red");
                break;

            case R.id.orange_button_id:
                intent.putExtra("drawable", "orange");
                break;

            case R.id.yellow_button_id:
                intent.putExtra("drawable", "yellow");
                break;

            case R.id.green_button_id:
                intent.putExtra("drawable", "green");
                break;

            case R.id.sky_button_id:
                intent.putExtra("drawable", "sky");
                break;

            case R.id.blue_button_id:
                intent.putExtra("drawable", "blue");
                break;

            default:
                intent.putExtra("drawable", "green");
        }

        intent.putExtra("date", dateTV.getText().toString());
        intent.putExtra("time", timeTV.getText().toString());
        intent.putExtra("comment", commentBox.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void setOnTouchListener(final Button button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                depressAll();
                button.setPressed(true);
                pressedButton = button;
                return true;
            }
        });
    }

    private void depressAll() {
        redButton.setPressed(false);
        orangeButton.setPressed(false);
        yellowButton.setPressed(false);
        greenButton.setPressed(false);
        skyButton.setPressed(false);
        blueButton.setPressed(false);
    }
}
