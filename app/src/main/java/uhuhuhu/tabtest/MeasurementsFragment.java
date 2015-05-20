package uhuhuhu.tabtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;

public class MeasurementsFragment extends Fragment {

    private ArrayList<MeasurementFrame> measurementFrames = new ArrayList<>();
    private LinearLayout measurementList = null;
    private LinearLayout measurementGraphLayout = null;
    private LineGraph chartLayout = null;
    private Button showAllButton = null;
    private boolean showWeight = true;
    private boolean showHeight = true;
    private boolean showBloodPressure = true;
    private boolean showHeartRate = true;

    private class PositionXY {
        int x;
        int y;

        public PositionXY(int x, int y) {
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    private ArrayList<PositionXY> weightPointList = new ArrayList<>();
    private ArrayList<PositionXY> heightPointList = new ArrayList<>();
    private ArrayList<PositionXY> heartRatePointList = new ArrayList<>();

    private SwitchButton weightSwitch = null;
    private SwitchButton heightSwitch = null;
    private SwitchButton heartRateSwitch = null;

    public int weightColor = R.color.red;
    public int heightColor = R.color.green;
    public int heartRateColor = R.color.blue;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflateView = inflater.inflate(R.layout.measurements_tab_content, container, false);

        ImageButton selectorButton = (ImageButton) inflateView.findViewById(R.id.button_measurement_selector_id);
        Button measurementsAddButton = (Button) inflateView.findViewById(R.id.measurements_add_button);
        showAllButton = (Button) inflateView.findViewById(R.id.button_show_all);

        measurementList = (LinearLayout) inflateView.findViewById(R.id.measurements_list_layout_id);
        measurementGraphLayout = (LinearLayout) inflateView.findViewById(R.id.measurements_graph_id);
        chartLayout = (LineGraph) inflateView.findViewById(R.id.graph);

        weightSwitch = (SwitchButton) inflateView.findViewById(R.id.switch1);
        heightSwitch = (SwitchButton) inflateView.findViewById(R.id.switch3);
        heartRateSwitch = (SwitchButton) inflateView.findViewById(R.id.switch2);

        weightSwitch.setChecked(true);
        heightSwitch.setChecked(true);
        heartRateSwitch.setChecked(true);

        fillChartData();

        selectorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (measurementList.getVisibility() == View.GONE) {
                    ((ImageButton) view).setImageResource(R.drawable.measurements_tabs_1);

                    measurementList.setVisibility(View.VISIBLE);
                    measurementGraphLayout.setVisibility(View.GONE);
                } else {
                    ((ImageButton) view).setImageResource(R.drawable.measurements_tabs_2);

                    measurementList.setVisibility(View.GONE);
                    showChart(weightColor, heightColor, heartRateColor, inflateView);
                }
            }
        });
        measurementsAddButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startActivityForResult(new Intent(getActivity(), NewMeasurementActivity.class), 1);
                }
                return false;
            }
        });
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MeasurementsSelector.class);
                intent.putExtra("ShowWeight", showWeight);
                intent.putExtra("ShowHeight", showHeight);
                intent.putExtra("ShowBloodPressure", showBloodPressure);
                intent.putExtra("ShowHeartRate", showHeartRate);
                startActivityForResult(intent, 2);
            }
        });

        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setWeightColor(R.color.red);
                } else {
                    setWeightColor(R.color.red_faded);
                }
                showChart(weightColor, heightColor, heartRateColor, inflateView);
            }
        });
        heightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    setHeightColor(R.color.green);
                } else {
                    setHeightColor(R.color.green_faded);
                }
                showChart(weightColor, heightColor, heartRateColor, inflateView);
            }
        });
        heartRateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setHRColor(R.color.blue);
                } else {
                    setHRColor(R.color.blue_faded);
                }
                showChart(weightColor, heightColor, heartRateColor, inflateView);
            }
        });

        addMeasurement("TODAY", "18.40", "", "69", "400", "72", "", "");
        addMeasurement("YESTERDAY", "12.10", "After sauna", "65", "145", "70", "220", "10");
        addMeasurement("18/03/2015", "15.30", "", "63", "160", "67", "320", "90");
        showMeasurementFrames();

        return inflateView;
    }

    public void setWeightColor(int color) {
        weightColor = color;
    }
    public void setHeightColor(int color) {
        heightColor = color;
    }
    public void setHRColor(int color) {
        heartRateColor = color;
    }
    private void showChart(int weightColor, int heightColor, int heartRateColor, View view) {
        chartLayout.removeAllLines();
        int maxY = findMaxY() + 10;
        drawDelimNet(maxY, 5);

        RelativeLayout weightLayout = (RelativeLayout) view.findViewById(R.id.measurement_weight_layout);
        RelativeLayout heightLayout = (RelativeLayout) view.findViewById(R.id.measurement_height_layout);
        RelativeLayout heartRateLayout = (RelativeLayout) view.findViewById(R.id.measurement_heartrate_layout);

        if (showWeight) {
            weightLayout.setVisibility(View.VISIBLE);
            drawWeight(weightColor);
        } else {
            weightLayout.setVisibility(View.GONE);
        }
        if (showHeight) {
            heightLayout.setVisibility(View.VISIBLE);
            drawHeight(heightColor);
        } else {
            heightLayout.setVisibility(View.GONE);
        }
        if (showHeartRate) {
            heartRateLayout.setVisibility(View.VISIBLE);
            drawHeartRate(heartRateColor);
        } else {
            heartRateLayout.setVisibility(View.GONE);
        }
        chartLayout.setRangeY(0, maxY);
        //li.setLineToFill(0);

        measurementGraphLayout.setVisibility(View.VISIBLE);
    }
    private void fillChartData() {

        weightPointList.add(new PositionXY(0, 5));
        weightPointList.add(new PositionXY(1, 6));
        weightPointList.add(new PositionXY(2, 7));
        weightPointList.add(new PositionXY(3, 8));
        weightPointList.add(new PositionXY(8, 9));
        weightPointList.add(new PositionXY(10, 4));
        weightPointList.add(new PositionXY(30, 34));

        heightPointList.add(new PositionXY(0, 2));
        heightPointList.add(new PositionXY(4, 10));
        heightPointList.add(new PositionXY(10, 7));
        heightPointList.add(new PositionXY(15, 15));

        heartRatePointList.add(new PositionXY(0, 2));
        heartRatePointList.add(new PositionXY(6, 1));
        heartRatePointList.add(new PositionXY(9, 9));
        heartRatePointList.add(new PositionXY(15, 15));
    }
    public int findMaxY() {
        int max = 0;
        int y = 0;

        for(PositionXY point : weightPointList) {
            y = point.getY();
            if(y > max) max = y;
        }
        for(PositionXY point : heightPointList) {
            y = point.getY();
            if(y > max) max = y;
        }
        for(PositionXY point : heartRatePointList) {
            y = point.getY();
            if(y > max) max = y;
        }
        return max;
    }
    public void drawWeight(int color) {
        Line l = new Line();
        for(PositionXY point : weightPointList) {
            plotLine(point.getX(), point.getY(), l);
        }
        l.setColor(getResources().getColor(color));
        chartLayout.addLine(l);
    }
    public void drawHeight(int color) {
        Line l = new Line();
        for(PositionXY point : heightPointList) {
            plotLine(point.getX(), point.getY(), l);
        }
        l.setColor(getResources().getColor(color));
        chartLayout.addLine(l);
    }
    public void drawHeartRate(int color) {
        Line l = new Line();
        for(PositionXY point : heartRatePointList) {
            plotLine(point.getX(), point.getY(), l);
        }
        l.setColor(getResources().getColor(color));
        chartLayout.addLine(l);
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
            if (resultCode == -1) {
                addMeasurement(data.getStringExtra("Date"),
                        data.getStringExtra("Time"),
                        data.getStringExtra("Comment"),
                        data.getStringExtra("Weight"),
                        data.getStringExtra("Height"),
                        data.getStringExtra("Heart rate"),
                        data.getStringExtra("Systolic pressure"),
                        data.getStringExtra("Diastolic pressure"));
                showMeasurementFrames();
            }
        } else if (requestCode == 2) {
            if (resultCode == -1) {
                showWeight = data.getBooleanExtra("ShowWeight", false);
                showHeight = data.getBooleanExtra("ShowHeight", false);
                showBloodPressure = data.getBooleanExtra("ShowBloodPressure", false);
                showHeartRate = data.getBooleanExtra("ShowHeartRate", false);

                String buttonText = "Show: ";

                if(showWeight &&
                        showHeight &&
                        showBloodPressure &&
                        showHeartRate)
                    buttonText += "all";
                else if(!(showWeight ||
                        showHeight ||
                        showBloodPressure ||
                        showHeartRate))
                    buttonText += "none";
                else {
                    if(showWeight) {
                        buttonText += "weight";
                    }
                    if(showHeight) {
                        if(buttonText.length() > 7)
                            buttonText += ", height";
                        else
                            buttonText += "height";
                    }
                    if(showBloodPressure) {
                        if(buttonText.length() > 7)
                            buttonText += ", blood pressure";
                        else
                            buttonText += "blood pressure";
                    }
                    if(showHeartRate) {
                        if (buttonText.length() > 7)
                            buttonText += ", heart rate";
                        else
                            buttonText += "heart rate";
                    }
                }

                showAllButton.setText(buttonText);
                showMeasurementFrames();
                showChart(weightColor, heightColor, heartRateColor, getView());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private View addParameter(String weight, String value, int color, int index) {
        View pFrame = LayoutInflater.from(getActivity()).inflate(R.layout.param_frame, null);
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

        measurementList.removeAllViews();
        for(int i = measurementFrames.size() - 1; i >= 0; --i) {
            View mFrame = LayoutInflater.from(getActivity()).inflate(R.layout.measurement_frame, null);

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
            if(spec.isEmpty()) specTV.setVisibility(View.GONE);
            if(!weight.isEmpty() && showWeight) {
                mFrameParams.addView(addParameter("Weight", weight, getColorForCurrentProgress(Integer.parseInt(weight)/5), 0));
                showFlag = true;
            }
            if(!height.isEmpty() && showHeight) {
                mFrameParams.addView(addParameter("Height", height, getColorForCurrentProgress(Integer.parseInt(height)/5), 1));
                showFlag = true;
            }
            if(!heartRate.isEmpty() && showHeartRate) {
                mFrameParams.addView(addParameter("Heart Rate", heartRate, getColorForCurrentProgress(Integer.parseInt(heartRate)/5), 2));
                showFlag = true;
            }
            if(!bPressure1.isEmpty() && !bPressure2.isEmpty() && showBloodPressure)
            {
                mFrameParams.addView(addParameter("Blood pressure", bPressure1 + "\\" + bPressure2, getColorForCurrentProgress(Integer.parseInt(bPressure1)/5), 3));
                showFlag = true;
            }

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

}

