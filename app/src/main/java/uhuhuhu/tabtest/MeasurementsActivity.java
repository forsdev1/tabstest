package uhuhuhu.tabtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class MeasurementsActivity extends Activity {
    private TabHost tabHost = null;
    private ArrayList<MeasurementFrame> measurementFrames = new ArrayList<>();
    private LinearLayout measurementList = null;
    private Button listButton = null;
    private Button graphButton = null;
    private class MeasurementFrame {
        private String date;
        private String time;
        private String spec;
        private String height;
        private String weight;
        private String heartRate;
        private String bPressure1;
        private String bPressure2;

        private MeasurementFrame(String date, String time, String spec, String height, String weight,
                                 String heartRate, String bPressure1, String bPressure2) {
            this.date = date;
            this.time = time;
            this.spec = spec;
            this.height = height;
            this.weight = weight;
            this.heartRate = heartRate;
            this.bPressure1 = bPressure1;
            this.bPressure2 = bPressure2;
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

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(String heartRate) {
            this.heartRate = heartRate;
        }

        public String getbPressure1() {
            return bPressure1;
        }

        public void setbPressure1(String bPressure1) {
            this.bPressure1 = bPressure1;
        }

        public String getbPressure2() {
            return bPressure2;
        }

        public void setbPressure2(String bPressure2) {
            this.bPressure2 = bPressure2;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurements_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        listButton = (Button) findViewById(R.id.button_measurements_list_id);
        graphButton = (Button) findViewById(R.id.button_measurements_graphs_id);

        listButton.setPressed(true);

        listButton.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setPressed(true);
                graphButton.setPressed(false);
                return true;
            }
        });
        graphButton.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setPressed(true);
                listButton.setPressed(false);
                return true;
            }
        });

        measurementList = (LinearLayout) findViewById(R.id.measurements_list_id);

        addMeasurement("Today", "18.40", "", "69", "170", "72", "", "");
        addMeasurement("Yesterday", "12.10", "After sauna", "65", "145", "70", "220", "10");
        addMeasurement("18/03/2015", "15.30", "", "63", "160", "67", "320", "90");
        showMeasurementFrames();
    }

    private void showMeasurementFrames() {

        for(int i = 0; i < measurementFrames.size(); ++i) {
            View mFrame = LayoutInflater.from(this).inflate(R.layout.measurement_frame, null);

            TextView dateTV = (TextView) mFrame.findViewById(R.id.measure_date_id);
            TextView timeTV = (TextView) mFrame.findViewById(R.id.measure_time_id);
            TextView specTV = (TextView) mFrame.findViewById(R.id.measure_spec_id);
            LinearLayout mFrameParams = (LinearLayout) mFrame.findViewById(R.id.mFrame_parameters_id);

            String date = measurementFrames.get(i).getDate();
            String time = measurementFrames.get(i).getTime();
            String spec = measurementFrames.get(i).getSpec();
            String weight = measurementFrames.get(i).getWeight();
            String height = measurementFrames.get(i).getHeight();
            String heartRate = measurementFrames.get(i).getHeartRate();
            String bPressure1 = measurementFrames.get(i).getbPressure1();
            String bPressure2 = measurementFrames.get(i).getbPressure2();

            dateTV.setText(date);
            timeTV.setText(time);
            specTV.setText(spec);
            if(!weight.isEmpty()) mFrameParams.addView(addParameter("Weight", weight));
            if(!height.isEmpty()) mFrameParams.addView(addParameter("Height", height));
            if(!heartRate.isEmpty()) mFrameParams.addView(addParameter("Heart Rate", heartRate));
            if(!bPressure1.isEmpty() && !bPressure2.isEmpty())
                mFrameParams.addView(addParameter("Blood pressure", bPressure1 + "\\" + bPressure2));

            measurementList.addView(mFrame);
        }
    }

    private View addParameter(String weight, String value) {
        View pFrame = LayoutInflater.from(this).inflate(R.layout.param_frame, null);
        TextView parameterName = (TextView) pFrame.findViewById(R.id.measure_param_id);
        TextView parameterValue = (TextView) pFrame.findViewById(R.id.measure_paramValue_id);
        parameterName.setText(weight);
        parameterValue.setText(value);
        return pFrame;
    }

    private void addMeasurement(String date, String time, String spec, String weight, String height,
                                String heartRate, String bPressure1, String bPressure2) {
        measurementFrames.add(new MeasurementFrame(date, time, spec, weight, height, heartRate, bPressure1, bPressure2));
    }


    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
