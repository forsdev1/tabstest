package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedbackActivity extends Activity {
    private TabHost tabHost = null;
    private LinearLayout fListLayout = null;
    private ArrayList<QuestionItem> questions = new ArrayList<>();

    private class QuestionItem {
        String question;
        String value;

        public QuestionItem(String question, String value) {
            this.question = question;
            this.value = value;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        fListLayout = (LinearLayout) findViewById(R.id.feedback_list_layout);

        addFeedbackQuestion("How do you feel today in general?", "");
        addFeedbackQuestion("how do u smthng 1?", "4");
        addFeedbackQuestion("how do u smthng 2?", "8");
        addFeedbackQuestion("how u smthng 3?", "");
        showQuestions();
    }

    private void addFeedbackQuestion(String question, String value) {
        questions.add(new QuestionItem(question, value));
    }
    private void showQuestions() {

        for(int i = 0; i < questions.size(); ++i) {
            View fFrame = LayoutInflater.from(this).inflate(R.layout.feedback_item, null);

            TextView questionTV = (TextView) fFrame.findViewById(R.id.feedback_question_id);
            final TextView valueTV = (TextView) fFrame.findViewById(R.id.feedback_value_id);
            SeekBar feedbackFD = (SeekBar) fFrame.findViewById(R.id.feedback_seekBar);
            feedbackFD.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //Toast.makeText(getApplicationContext(), String.valueOf(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    valueTV.setText(String.valueOf(progress));
                    valueTV.getBackground().setColorFilter(getColorForCurrentProgress(progress), PorterDuff.Mode.SRC_OVER);
                }
            });

            String value = questions.get(i).getValue();

            questionTV.setText(questions.get(i).getQuestion());
            valueTV.setText(value);

            if(value.length() > 0) {
                int val = Integer.parseInt(value);
                feedbackFD.setProgress(val);
                //valueTV.setBackgroundResource(R.drawable.label_rounded_feedback);
                //feedbackFD.setBackgroundResource(R.drawable.seekbar_progress);
                Drawable drawable = valueTV.getBackground();
                drawable.setColorFilter(getColorForCurrentProgress(val), PorterDuff.Mode.SRC_OVER);
                //Drawable drawable2 = feedbackFD.getBackground();
                //drawable2.setColorFilter(getColorForCurrentProgress(val), PorterDuff.Mode.SRC_OVER);
            }

            fListLayout.addView(fFrame);
        }
    }

    public int getColorForCurrentProgress(int progressPercentage){
        int rColor = 255;
        int gColor = 0;

        int resultPreColorValue = progressPercentage * 51;

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

    public void showSlider(View view) {
        for(int i = 0; i < ((RelativeLayout)view).getChildCount(); ++i) {
            FrameLayout childLayout = (FrameLayout) ((RelativeLayout)view).getChildAt(i).findViewById(R.id.feedback_slider_layout);
            if(childLayout != null) childLayout.setVisibility(View.GONE);
        }
        FrameLayout frmLayout = (FrameLayout) view.findViewById(R.id.feedback_slider_layout);
        frmLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
