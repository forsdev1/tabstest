package uhuhuhu.tabtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MeasurementsSelector extends Activity {

    private CheckBox showAllCheckBox = null;
    private CheckBox showWeightCheckBox = null;
    private CheckBox showHeightCheckBox = null;
    private CheckBox showBloodPressureCheckBox = null;
    private CheckBox showHeartRateCheckBox = null;

    private boolean showAll = true;
    private boolean showWeight = false;
    private boolean showHeight = false;
    private boolean showBloodPressure = false;
    private boolean showHeartRate = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurements_selector_layout);

        showAllCheckBox = (CheckBox) findViewById(R.id.show_all_cb);
        showWeightCheckBox = (CheckBox) findViewById(R.id.weight_cb);
        showHeightCheckBox = (CheckBox) findViewById(R.id.height_cb);
        showBloodPressureCheckBox = (CheckBox) findViewById(R.id.bp_cb);
        showHeartRateCheckBox = (CheckBox) findViewById(R.id.hr_cb);

        showAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                           showAll = isChecked;
                                                           if (showAll) {
                                                               if (!showWeightCheckBox.isChecked())
                                                                   showWeightCheckBox.setChecked(true);
                                                               if(!showHeightCheckBox.isChecked())
                                                                   showHeightCheckBox.setChecked(true);
                                                               if(!showBloodPressureCheckBox.isChecked())
                                                                   showBloodPressureCheckBox.setChecked(true);
                                                               if(!showHeartRateCheckBox.isChecked())
                                                                   showHeartRateCheckBox.setChecked(true);
                                                           } else {
                                                               if (showHeightCheckBox.isChecked() &&
                                                                       showBloodPressureCheckBox.isChecked() &&
                                                                       showHeartRateCheckBox.isChecked() &&
                                                                       showWeightCheckBox.isChecked()) {
                                                                   showWeightCheckBox.setChecked(false);
                                                                   showHeightCheckBox.setChecked(false);
                                                                   showBloodPressureCheckBox.setChecked(false);
                                                                   showHeartRateCheckBox.setChecked(false);
                                                               }
                                                           }
                                                       }
                                                   }
        );
        showWeightCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                          @Override
                                                          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                              showWeight = isChecked;
                                                              if(isChecked) {
                                                                  if (showHeightCheckBox.isChecked() &&
                                                                          showBloodPressureCheckBox.isChecked() &&
                                                                          showHeartRateCheckBox.isChecked())
                                                                      showAllCheckBox.setChecked(true);
                                                              } else {
                                                                  if(showAll) showAllCheckBox.setChecked(false);
                                                              }
                                                          }
                                                      }
        );
        showHeightCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                          @Override
                                                          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                              showHeight = isChecked;
                                                              if(isChecked) {
                                                                  if (showWeightCheckBox.isChecked() &&
                                                                          showBloodPressureCheckBox.isChecked() &&
                                                                          showHeartRateCheckBox.isChecked())
                                                                      showAllCheckBox.setChecked(true);
                                                              } else {
                                                                  if(showAll) showAllCheckBox.setChecked(false);
                                                              }
                                                          }
                                                      }
        );
        showBloodPressureCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                                 @Override
                                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                                     showBloodPressure = isChecked;
                                                                     if(isChecked) {
                                                                         if (showWeightCheckBox.isChecked() &&
                                                                                 showHeightCheckBox.isChecked() &&
                                                                                 showHeartRateCheckBox.isChecked())
                                                                             showAllCheckBox.setChecked(true);
                                                                     } else {
                                                                         if(showAll) showAllCheckBox.setChecked(false);
                                                                     }
                                                                 }
                                                             }
        );
        showHeartRateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                             @Override
                                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                                 showHeartRate = isChecked;
                                                                 if(isChecked) {
                                                                     if (showHeightCheckBox.isChecked() &&
                                                                             showWeightCheckBox.isChecked() &&
                                                                             showBloodPressureCheckBox.isChecked())
                                                                         showAllCheckBox.setChecked(true);
                                                                 } else {
                                                                     if(showAll) showAllCheckBox.setChecked(false);
                                                                 }
                                                             }
                                                         }
        );

        showWeight = getIntent().getBooleanExtra("ShowWeight", false);
        showHeight = getIntent().getBooleanExtra("ShowHeight", false);
        showBloodPressure = getIntent().getBooleanExtra("ShowBloodPressure", false);
        showHeartRate = getIntent().getBooleanExtra("ShowHeartRate", false);
        showWeightCheckBox.setChecked(showWeight);
        showHeightCheckBox.setChecked(showHeight);
        showBloodPressureCheckBox.setChecked(showBloodPressure);
        showHeartRateCheckBox.setChecked(showHeartRate);
    }

    public void cancelPressed(View view) {
        finish();
    }

    public void okPressed(View view) {
        Intent intent = new Intent();
        intent.putExtra("ShowWeight", showWeight);
        intent.putExtra("ShowHeight", showHeight);
        intent.putExtra("ShowBloodPressure", showBloodPressure);
        intent.putExtra("ShowHeartRate", showHeartRate);
        setResult(RESULT_OK, intent);
        finish();
    }
}
