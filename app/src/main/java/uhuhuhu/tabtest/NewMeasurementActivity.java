package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewMeasurementActivity extends Activity {

    private RelativeLayout calendarViewLayout = null;
    private RelativeLayout timepLayout = null;
    private RelativeLayout weightpLayout = null;
    private RelativeLayout heightpLayout = null;
    private RelativeLayout hrpLayout = null;
    private RelativeLayout sppLayout = null;
    private RelativeLayout dppLayout = null;
    private RelativeLayout temppLayout = null;

    private TextView dateTV = null;
    private TextView timeTV = null;
    private TextView weightTV = null;
    private TextView heightTV = null;
    private TextView hrTV = null;
    private TextView spTV = null;
    private TextView dpTV = null;
    private TextView tempTV = null;
    private EditText commentTV = null;

    private TimePicker timePicker = null;

    public Calendar calendar = null;

    public void setupPicker(final NumberPicker picker, final TextView tv) {
        // change divider
        java.lang.reflect.Field[] pickerFields = NumberPicker.class
                .getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    pf.set(picker, getResources().getDrawable(
                            R.drawable.numberpicker_border));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        setNumberPickerTextColor(picker, getResources().getColor(R.color.black));
        picker.setMinValue(0);
        picker.setMaxValue(1000);

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv.setText(String.valueOf(newVal));
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_measurement_content);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weightPicker);
        NumberPicker heightPicker = (NumberPicker) findViewById(R.id.heightPicker);
        NumberPicker hrPicker = (NumberPicker) findViewById(R.id.hrPicker);
        NumberPicker spPicker = (NumberPicker) findViewById(R.id.spPicker);
        NumberPicker dpPicker = (NumberPicker) findViewById(R.id.dpPicker);
        NumberPicker tempPicker = (NumberPicker) findViewById(R.id.tempPicker);

        dateTV = (TextView) findViewById(R.id.new_measure_date_value);
        timeTV = (TextView) findViewById(R.id.new_measure_time_value);
        weightTV = (TextView) findViewById(R.id.new_measure_weight_value);
        heightTV = (TextView) findViewById(R.id.new_measure_height_value);
        hrTV = (TextView) findViewById(R.id.new_measure_hr_value);
        spTV = (TextView) findViewById(R.id.new_measure_sp_value);
        dpTV = (TextView) findViewById(R.id.new_measure_dp_value);
        tempTV = (TextView) findViewById(R.id.new_measure_temp_value);
        commentTV = (EditText) findViewById(R.id.new_measure_comment_value);

        calendar = GregorianCalendar.getInstance();

        dateTV.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.YEAR)));
        timeTV.setText(String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));

        setupPicker(weightPicker, weightTV);
        setupPicker(heightPicker, heightTV);
        setupPicker(hrPicker, hrTV);
        setupPicker(spPicker, spTV);
        setupPicker(dpPicker, dpTV);
        setupPicker(tempPicker,tempTV);

        calendarViewLayout = (RelativeLayout) findViewById(R.id.datep_layout);
        timepLayout = (RelativeLayout) findViewById(R.id.timepicker_layout);
        weightpLayout = (RelativeLayout) findViewById(R.id.weightpicker_layout);
        heightpLayout = (RelativeLayout) findViewById(R.id.heightpicker_layout);
        hrpLayout = (RelativeLayout) findViewById(R.id.hrpicker_layout);
        sppLayout = (RelativeLayout) findViewById(R.id.sppicker_layout);
        dppLayout = (RelativeLayout) findViewById(R.id.dppicker_layout);
        temppLayout = (RelativeLayout) findViewById(R.id.temppicker_layout);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dateTV.setText(String.valueOf(dayOfMonth) +
                        "/" + String.valueOf(month) +
                        "/" + String.valueOf(year));
            }
        });
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                Field selectorWheelPaintField = null;
                try {
                    selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                selectorWheelPaintField.setAccessible(true);
                try {
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ((EditText)child).setTextColor(color);
                numberPicker.invalidate();
                return true;
            }
        }
        return false;
    }


    public void dateClicked(View view) {
        slidePicker(calendarViewLayout);
    }
    public void timeClicked(View view) {
        slidePicker(timepLayout);
    }
    public void weightClicked(View view) {
        slidePicker(weightpLayout);
    }
    public void heightClicked(View view) {
        slidePicker(heightpLayout);
    }
    public void hrClicked(View view) {
        slidePicker(hrpLayout);
    }
    public void spClicked(View view) {
        slidePicker(sppLayout);
    }
    public void dpClicked(View view) {
        slidePicker(dppLayout);
    }
    public void tempClicked(View view) {
        slidePicker(temppLayout);
    }
    public void done(View view) {
        Intent intent = new Intent();

        String tempDateNow = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.YEAR));
        String tempDateYesterday = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) - 1) +
                "/" + String.valueOf(calendar.get(Calendar.MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.YEAR));
        String tempDateTomorrow = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + 1) +
                "/" + String.valueOf(calendar.get(Calendar.MONTH)) +
                "/" + String.valueOf(calendar.get(Calendar.YEAR));
        String selectedDate = dateTV.getText().toString();

        if (selectedDate.equals(tempDateNow)) {
            intent.putExtra("Date", "TODAY");
        } else if (selectedDate.equals(tempDateYesterday)) {
            intent.putExtra("Date", "YESTERDAY");
        } else if (selectedDate.equals(tempDateTomorrow)) {
            intent.putExtra("Date", "TOMORROW");
        } else {
            intent.putExtra("Date", selectedDate);
        }
        intent.putExtra("Time", timeTV.getText().toString());
        intent.putExtra("Weight", weightTV.getText().toString());
        intent.putExtra("Height", heightTV.getText().toString());
        intent.putExtra("Heart rate", hrTV.getText().toString());
        intent.putExtra("Systolic pressure", spTV.getText().toString());
        intent.putExtra("Diastolic pressure", dpTV.getText().toString());
        intent.putExtra("Temperature", tempTV.getText().toString());
        intent.putExtra("Comment", commentTV.getText().toString() );
        setResult(RESULT_OK, intent);
        finish();
    }

    public void slidePicker(RelativeLayout picker){
        commentTV.clearFocus();

        if (picker.getVisibility() == View.GONE) {
            hideAll();
            picker.setVisibility(View.VISIBLE);
            picker.setAlpha(0.0f);
            picker.animate().translationY(0.5f).alpha(1.0f);
        }
        else {
            picker.setVisibility(View.GONE);
            picker.animate().translationY(0.0f).alpha(0.0f);
        }

        //onTimeChanged never hits, so setting time manually
        timeTV.setText(String.format("%02d:%02d", timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
    }

    private void hideAll() {

        calendarViewLayout.setVisibility(View.GONE);
        calendarViewLayout.animate().translationY(0.0f).alpha(0.0f);
        timepLayout.setVisibility(View.GONE);
        timepLayout.animate().translationY(0.0f).alpha(0.0f);
        weightpLayout.setVisibility(View.GONE);
        weightpLayout.animate().translationY(0.0f).alpha(0.0f);
        heightpLayout.setVisibility(View.GONE);
        heightpLayout.animate().translationY(0.0f).alpha(0.0f);
        hrpLayout.setVisibility(View.GONE);
        hrpLayout.animate().translationY(0.0f).alpha(0.0f);
        sppLayout.setVisibility(View.GONE);
        sppLayout.animate().translationY(0.0f).alpha(0.0f);
        dppLayout.setVisibility(View.GONE);
        dppLayout.animate().translationY(0.0f).alpha(0.0f);
        temppLayout.setVisibility(View.GONE);
        temppLayout.animate().translationY(0.0f).alpha(0.0f);
    }
}
