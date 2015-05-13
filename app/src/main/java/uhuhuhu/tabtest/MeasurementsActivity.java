package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import java.util.ArrayList;

public class MeasurementsActivity extends Activity {
    private TabHost tabHost = null;
    private ArrayList<MeasurementFrame> measurementFrames = new ArrayList<>();
    private LinearLayout measurementList = null;
    private ImageButton selectorButton = null;
    private Button measurementsAddButton = null;
    private Button measurementSelectorButton = null;
    //private ImageButton lastPressedButton = null;
    private LinearLayout measurementListLayout = null;
    private LinearLayout measurementGraphLayout = null;
    private LineGraph chartLayout = null;

    private boolean showWeight = true;
    private boolean showHeight = true;
    private boolean showBloodPressure = true;
    private boolean showHeartRate = true;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSpec() {
            return spec;
        }

        public String getHeight() {
            return height;
        }

        public String getWeight() {
            return weight;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public String getbPressure1() {
            return bPressure1;
        }

        public String getbPressure2() {
            return bPressure2;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurements_tab_content);
        tabHost = (TabHost) getParent().findViewById(android.R.id.tabhost);

        selectorButton = (ImageButton) findViewById(R.id.button_measurement_selector_id);
        measurementsAddButton = (Button) findViewById(R.id.measurements_add_button);
        measurementSelectorButton = (Button) findViewById(R.id.button_show_all);

        measurementListLayout = (LinearLayout) findViewById(R.id.measurements_list_id);
        measurementGraphLayout = (LinearLayout) findViewById(R.id.measurements_graph_id);

        selectorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (measurementListLayout.getVisibility() == View.GONE) {
                    ((ImageButton) view).setImageResource(R.drawable.measurements_tabs_1);

                    measurementListLayout.setVisibility(View.VISIBLE);
                    measurementGraphLayout.setVisibility(View.GONE);
                } else {
                    ((ImageButton) view).setImageResource(R.drawable.measurements_tabs_2);

                    measurementListLayout.setVisibility(View.GONE);
                    measurementGraphLayout.setVisibility(View.VISIBLE);

                    chartLayout = (LineGraph) findViewById(R.id.graph);
                    if (chartLayout != null) {
                        chartLayout.setVisibility(View.VISIBLE);

                        drawDelimNet(50, 5);

                        Line l = new Line();
                        plotLine(0, 5, l);
                        plotLine(1, 6, l);
                        plotLine(2, 7, l);
                        plotLine(3, 8, l);
                        plotLine(8, 9, l);
                        plotLine(10, 4, l);
                        plotLine(30, 34, l);
                        l.setColor(Color.parseColor("#1C96FF"));

                        Line l2 = new Line();
                        plotLine(0, 2, l2);
                        plotLine(4, 10, l2);
                        plotLine(10, 7, l2);
                        plotLine(15, 15, l2);
                        l2.setColor(Color.parseColor("#FF0000"));

                        Line l3 = new Line();
                        plotLine(0, 2, l3);
                        plotLine(6, 1, l3);
                        plotLine(9, 9, l3);
                        plotLine(12, 4, l3);
                        l3.setColor(Color.parseColor("#00FF00"));

                        chartLayout.addLine(l);
                        chartLayout.addLine(l2);
                        chartLayout.addLine(l3);
                        chartLayout.setRangeY(0, 50);
                        //li.setLineToFill(0);
                    }
                }
            }
        });
        measurementsAddButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startActivityForResult(new Intent(getBaseContext(), NewMeasurementActivity.class), 1);
                }
                return false;
            }
        });
        measurementSelectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MeasurementsSelector.class);
                intent.putExtra("ShowWeight", showWeight);
                intent.putExtra("ShowHeight", showHeight);
                intent.putExtra("ShowBloodPressure", showBloodPressure);
                intent.putExtra("ShowHeartRate", showHeartRate);
                startActivityForResult(intent, 2);
            }
        });

        measurementList = (LinearLayout) findViewById(R.id.measurements_list_id);

        addMeasurement("TODAY", "18.40", "", "69", "400", "72", "", "");
        addMeasurement("YESTERDAY", "12.10", "After sauna", "65", "145", "70", "220", "10");
        addMeasurement("18/03/2015", "15.30", "", "63", "160", "67", "320", "90");
        showMeasurementFrames();
    }

    private void drawDelimNet(int xRange, int interval) {

        for (int i = 0; i < interval * 10; i += interval) {
            Line l = new Line();
            plotLine(0, i, l);
            plotLine(xRange, i, l);
            l.setColor(Color.parseColor("#cacaca"));
            l.setShowingPoints(false);
            l.setStrokeWidth(1);
            chartLayout.addLine(l);
        }
    }

    public void plotLine(int x, int y, Line line) {
        LinePoint point = new LinePoint();
        point.setX(x);
        point.setY(y);
        line.addPoint(point);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                showWeight = data.getBooleanExtra("ShowWeight", false);
                showHeight = data.getBooleanExtra("ShowHeight", false);
                showBloodPressure = data.getBooleanExtra("ShowBloodPressure", false);
                showHeartRate = data.getBooleanExtra("ShowHeartRate", false);

                measurementList.removeAllViews();
                showMeasurementFrames();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private View addParameter(String weight, String value, int color, int index) {
        View pFrame = LayoutInflater.from(this).inflate(R.layout.param_frame, null);
        TextView parameterName = (TextView) pFrame.findViewById(R.id.measure_param_id);
        TextView parameterValue = (TextView) pFrame.findViewById(R.id.measure_paramValue_id);

        if((index % 2) != 0) pFrame.setBackgroundColor(getResources().getColor(R.color.lightblue));

        parameterValue.setBackgroundResource(R.drawable.colored_style_rounded_all);
        Drawable drawable = parameterValue.getBackground();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_OVER);

        parameterName.setText(weight);
        parameterValue.setText(value);

        return pFrame;
    }

    private void addMeasurement(String date, String time, String spec, String weight, String height,
                                String heartRate, String bPressure1, String bPressure2) {
        measurementFrames.add(new MeasurementFrame(date, time, spec, weight, height, heartRate, bPressure1, bPressure2));
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
            boolean showFlag = false;
            if(!weight.isEmpty() && showWeight) { mFrameParams.addView(addParameter("Weight", weight, getColorForCurrentProgress(Integer.parseInt(weight)/5), 0)); showFlag = true;}
            if(!height.isEmpty() && showHeight) {mFrameParams.addView(addParameter("Height", height, getColorForCurrentProgress(Integer.parseInt(height)/5), 1)); showFlag = true;}
            if(!heartRate.isEmpty() && showHeartRate) {mFrameParams.addView(addParameter("Heart Rate", heartRate, getColorForCurrentProgress(Integer.parseInt(heartRate)/5), 2)); showFlag = true; }
            if(!bPressure1.isEmpty() && !bPressure2.isEmpty() && showBloodPressure)
    { mFrameParams.addView(addParameter("Blood pressure", bPressure1 + "\\" + bPressure2, getColorForCurrentProgress(Integer.parseInt(bPressure1)/5), 3)); showFlag = true;}

            if(showFlag)
                measurementList.addView(mFrame);
        }
    }
    public int getColorForCurrentProgress(int progressPercentage){
        int rColor = 255;
        int gColor = 0;

        Double resultPreColorValueDouble = (progressPercentage * 5.1);
        int resultPreColorValue = resultPreColorValueDouble.intValue();

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

    @Override
    public void onBackPressed()
    {
        tabHost.setCurrentTab(0);
    }
}
