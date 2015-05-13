package uhuhuhu.tabtest;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class NewMeasurementActivity extends Activity {

    private RelativeLayout datepLayout = null;
    private RelativeLayout timepLayout = null;
    private RelativeLayout weightpLayout = null;
    private RelativeLayout heightpLayout = null;
    private RelativeLayout hrpLayout = null;
    private RelativeLayout sppLayout = null;
    private RelativeLayout dppLayout = null;
    private RelativeLayout temppLayout = null;

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

        NumberPicker datePicker = (NumberPicker) findViewById(R.id.numberPicker);
        NumberPicker timePicker = (NumberPicker) findViewById(R.id.timePicker);
        NumberPicker weightPicker = (NumberPicker) findViewById(R.id.weightPicker);
        NumberPicker heightPicker = (NumberPicker) findViewById(R.id.heightPicker);
        NumberPicker hrPicker = (NumberPicker) findViewById(R.id.hrPicker);
        NumberPicker spPicker = (NumberPicker) findViewById(R.id.spPicker);
        NumberPicker dpPicker = (NumberPicker) findViewById(R.id.dpPicker);
        NumberPicker tempPicker = (NumberPicker) findViewById(R.id.tempPicker);

        TextView dateTV = (TextView) findViewById(R.id.new_measure_date_value);
        TextView timeTV = (TextView) findViewById(R.id.new_measure_time_value);
        TextView weightTV = (TextView) findViewById(R.id.new_measure_weight_value);
        TextView heightTV = (TextView) findViewById(R.id.new_measure_height_value);
        TextView hrTV = (TextView) findViewById(R.id.new_measure_hr_value);
        TextView spTV = (TextView) findViewById(R.id.new_measure_sp_value);
        TextView dpTV = (TextView) findViewById(R.id.new_measure_dp_value);
        TextView tempTV = (TextView) findViewById(R.id.new_measure_temp_value);

        setupPicker(datePicker, dateTV);
        setupPicker(timePicker, timeTV);
        setupPicker(weightPicker, weightTV);
        setupPicker(heightPicker, heightTV);
        setupPicker(hrPicker, hrTV);
        setupPicker(spPicker, spTV);
        setupPicker(dpPicker, dpTV);
        setupPicker(tempPicker,tempTV);

        datepLayout = (RelativeLayout) findViewById(R.id.datep_layout);
        timepLayout = (RelativeLayout) findViewById(R.id.timepicker_layout);
        weightpLayout = (RelativeLayout) findViewById(R.id.weightpicker_layout);
        heightpLayout = (RelativeLayout) findViewById(R.id.heightpicker_layout);
        hrpLayout = (RelativeLayout) findViewById(R.id.hrpicker_layout);
        sppLayout = (RelativeLayout) findViewById(R.id.sppicker_layout);
        dppLayout = (RelativeLayout) findViewById(R.id.dppicker_layout);
        temppLayout = (RelativeLayout) findViewById(R.id.temppicker_layout);
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
        slidePicker(datepLayout);
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
        finish();
    }

    public void slidePicker(RelativeLayout picker){
        if (picker.getVisibility() == View.GONE) {
            picker.setVisibility(View.VISIBLE);
            picker.setAlpha(0.0f);
            picker.animate().translationY(0.5f).alpha(1.0f);
        }
        else {
            picker.setVisibility(View.GONE);
            picker.animate().translationY(0.0f).alpha(0.0f);
        }
    }
}
